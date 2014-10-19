/*
 * Copyright (C) 2013 Keisuke SUZUKI
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * Distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.physicaloid.lib.usb;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import android.util.SparseArray;


/*
 * USB Hierarchy Accessor
 *  Enum singleton pattern
 */
public enum UsbAccessor {
    INSTANCE;   // enum singleton


    private static final String TAG = UsbAccessor.class.getSimpleName();

    private UsbManager mManager = null;
    private PendingIntent mPermissionIntent = null;

    private SparseArray<UsbDeviceConnection> mConnection;

    private UsbAccessor() {
        mConnection = new SparseArray<UsbDeviceConnection>();
    }

    /**
     * Initializes USB Manager
     * @param context MainActivity's context
     */
    @SuppressWarnings("static-access")
    public void init(Context context) {
        if(mManager == null) {
            mManager = (UsbManager) context.getSystemService(context.USB_SERVICE);
        }

        if(mPermissionIntent == null) {
            mPermissionIntent = PendingIntent.getBroadcast(context, 0, new Intent("USB_PERMISSION"), 0);
        }
    }

    public UsbManager manager() {
        return mManager;
    }

    /**
     * Gets UsbDevice by a hierarchy device number
     * @param devNum hierarchy device number
     * @return UsbDevice or null
     */
    public UsbDevice device(int devNum) {
        if(mManager == null || mPermissionIntent == null) return null;
        if(mManager.getDeviceList().size() <= devNum) return null;

        int count=0;
        for (UsbDevice device : mManager.getDeviceList().values()) {

            if(count == devNum) {
                getPermission(device);
                if (!mManager.hasPermission(device)) {
                    return null;
                } else {
                    return device;
                }
            }
            count++;
        }
        return null;
    }

    /**
     * Gets UsbInterface by a hierarchy device and interface number
     * @param devNum hierarchy device number
     * @param intfNum hierarchy interface number
     * @return UsbInterface or null
     */
    public UsbInterface intface(int devNum, int intfNum) {
        UsbDevice dev = device(devNum);
        if(dev == null) { return null; }

        int devCount = dev.getInterfaceCount();
        for(int i=0; i<devCount; i++) {
            if(i==intfNum) {
                return dev.getInterface(i);
            }
        }
        return null;
    }

    /**
     * Gets UsbEndpoint by a hierarchy device, interface and endpoint number
     * @param devNum hierarchy device number
     * @param intfNum hierarchy interface number
     * @param epNum hierarchy endpoint number
     * @return UsbEndpoint or null
     */
    public UsbEndpoint endpoint(int devNum, int intfNum, int epNum) {
        UsbInterface intf = intface(devNum, intfNum);
        if(intf == null) {return null;}

        int epCount = intf.getEndpointCount();
        for(int i=0; i<epCount; i++) {
            if(i==epNum) {
                return intf.getEndpoint(i);
            }
        }
        return null;
    }

    /**
     * Gets UsbDeviceConnection by a hierarchy device number
     * @param devNum hierarchy device number
     * @return UsbDeviceConnection or null
     */
    public UsbDeviceConnection connection(int ch) {
        if(ch >= mConnection.size()) { return null; }
        return mConnection.get(ch);
    }

    /**
     * Check whether a device is connected or not
     * @param devNum hierarchy device number
     * @return true:connected, false:not connected
     */
    public boolean deviceIsConnected(int devNum) {
        if(connection(devNum) == null) return false;
        return true;
    }

    /**
     * Connect a USB device
     * @return UsbDeviceConnection or null
     */
    public boolean openDevice() {
        return openDevice(0, 0, 0);
    }

    /**
     * Connect a USB device by a hierarchy device and interface number
     * @param devNum hierarchy device number
     * @return UsbDeviceConnection or null
     */
    public boolean openDevice(int devNum) {
        return openDevice(devNum, 0, 0);
    }

    /**
     * Connect a USB device by a hierarchy device and interface number
     * @param devNum hierarchy device number
     * @param intfNum hierarchy interface number
     * @param ch channel number
     * @return UsbDeviceConnection or null
     */
    public boolean openDevice(int devNum, int intfNum, int ch) {
        UsbDevice dev = device(devNum);
        if(dev == null) { return false; }
        UsbDeviceConnection con = mManager.openDevice(dev);
        if(con == null) { return false; }
        if(con.claimInterface(intface(devNum, intfNum), true)) {
            mConnection.put(ch,con);
            return true;
        } else {
            mConnection.remove(devNum);
            return false;
        }
    }

    public boolean close(int devNum) {
        UsbDeviceConnection con = connection(devNum);
        if(con == null) return false;
        mConnection.remove(devNum);
        con.close();
        return true;
    }

    public boolean closeAll() {
        boolean ret = false;
        UsbDeviceConnection con;
        for(int i=0; i < mConnection.size(); i++) {
            con = mConnection.valueAt(i);
            if(con == null) continue;
            con.close();
            ret = true;
        }
        mConnection.clear();
        return ret;
    }

    /**
     * Gets devNum device's VID
     * @param devNum
     * @return
     */
    public int getVid(int devNum) {
        UsbDevice dev = device(devNum);
        if(dev == null) return 0;
        return dev.getVendorId();
    }

    /**
     * Gets devNum device's PID
     * @param devNum
     * @return
     */
    public int getPid(int devNum) {
        UsbDevice dev = device(devNum);
        if(dev == null) return 0;
        return dev.getProductId();
    }

    /**
     * Gets devNum device's SerialID
     * @param devNum
     * @return
     */
    public String getSerial(int devNum) {
        if(connection(devNum) == null) return "";
        return connection(devNum).getSerial();
    }

    /**
     * Gets an USB permission if no permission
     * 
     * @param device
     */
    public void getPermission(UsbDevice device) {
        if(mManager == null) return;
        if (device != null && mPermissionIntent != null) {
            if (!mManager.hasPermission(device)) {
                 mManager.requestPermission(device, mPermissionIntent);
            }
        }
    }
}
