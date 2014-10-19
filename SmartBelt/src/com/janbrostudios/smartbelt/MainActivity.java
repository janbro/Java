package com.janbrostudios.smartbelt;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import com.janbrostudios.smartbelt.MainActivity.NLService.NotificationReceiver;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	NLService nlServ;
    private static TextView txtView;
    private NotificationReceiver nReceiver;
    private BluetoothAdapter BA;
    private final static int REQUEST_ENABLE_BT = 1;
    private static Context context;
    private static OutputStream dos;
    private InputStream dis;
    private BluetoothAdapter mBluetoothAdapter;
	private String deviceName = "HC-06";
	private int pending = 0;
	private char lastMess;
	private char currentMess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nlServ = new NLService();
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        	Toast.makeText(context, "Bluetooth not supported!", Toast.LENGTH_SHORT).show();
        	this.finish();
        }
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        BA = BluetoothAdapter.getDefaultAdapter();
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.textView);
        nReceiver = nlServ.new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
        registerReceiver(nReceiver,filter);
        context = this;
        if(savedInstanceState!=null)
        	return;
        ConnectedThread ct = new ConnectedThread(BTConnect(deviceName));
        ct.start();
        NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder ncomp = new NotificationCompat.Builder(this);
        ncomp.setContentTitle("Smart Belt");
        ncomp.setContentText("Smart Notification is Running");
        ncomp.setTicker("Smart Notification is Running");
        ncomp.setSmallIcon(R.drawable.ic_launcher);
        ncomp.setAutoCancel(false);
        ncomp.setOngoing(true);
        ncomp.setPriority(99);
        Intent resultIntent = new Intent(this, MainActivity.class);
        resultIntent.setAction(Intent.ACTION_MAIN);
        resultIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                resultIntent, 0);

        ncomp.setContentIntent(pendingIntent);
        nManager.notify((int)System.currentTimeMillis(),ncomp.build());
    }    
    
    public BluetoothSocket BTConnect(String deviceName){
    	BluetoothSocket socket = null;
    	BluetoothDevice device = null;
    	BluetoothDevice[] mAllBondedDevices = (BluetoothDevice[]) mBluetoothAdapter.getBondedDevices().toArray(new BluetoothDevice[0]);
    	for (BluetoothDevice d : mAllBondedDevices) {
    	  if (deviceName.equals(d.getName())) {
    	    device = d;
    	    break;
    	  }
    	}
    	UUID uuid = device.getUuids()[0].getUuid();
    	//FAILED: socket = device.createRfcommSocketToServiceRecord(uuid);
    	// Succeeds: Warning, INSECURE!
    	try {
			socket = device.createInsecureRfcommSocketToServiceRecord(uuid);
			socket.connect();
			 dis = socket.getInputStream();
			 dos = socket.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return socket;
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == RESULT_CANCELED) {
            	Toast.makeText(context, "Bluetooth not enabled!", Toast.LENGTH_SHORT).show();
            	this.finish();
            }else if(resultCode == RESULT_OK){
            	
            }
        }
    }
    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }

    private void sendMessage(byte[] mess){
    	if(dos==null)
    		return;
    	try {
			dos.write(mess);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void notif(){
    	if(dos==null)
    		return;
    	try {
			dos.write("a".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void buttonClicked(View v){

        if(v.getId() == R.id.btnCreateNotify){
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder ncomp = new NotificationCompat.Builder(this);
            ncomp.setContentTitle("My Notification");
            ncomp.setContentText("Notification Listener Service Example");
            ncomp.setTicker("Notification Listener Service Example");
            ncomp.setSmallIcon(R.drawable.ic_launcher);
            ncomp.setAutoCancel(true);
            nManager.notify((int)System.currentTimeMillis(),ncomp.build());
        }
        else if(v.getId() == R.id.btnClearNotify){
            Intent i = new Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
            i.putExtra("command","clearall");
            sendBroadcast(i);
            Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
         // If there are paired devices
         if (pairedDevices.size() > 0) {
             // Loop through paired devices
             for (BluetoothDevice device : pairedDevices) {
                 // Add the name and address to an array adapter to show in a ListView
                 txtView.append(device.getName() + "\n" + device.getAddress());
             }
         }
        }
        else if(v.getId() == R.id.btnListNotify){
            Intent i = new Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
            i.putExtra("command","list");
            sendBroadcast(i);
        }else if(v.getId() == R.id.reconnect){
        	BTConnect(deviceName);
        	notif();
        }else if(v.getId() == R.id.readStream){
        	try {
        		int charr = dis.read();
        		dis.skip(2);
				txtView.setText("\n\""+(char)charr+"\" ASIIVal: "+charr+"\n"+txtView.getText());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }


    }

    class NLService extends NotificationListenerService {

        private String TAG = this.getClass().getSimpleName();
        private NotificationReceiver nlservicereciver;
        @Override
        public void onCreate() {
            super.onCreate();
            nlservicereciver = new NotificationReceiver();
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.kpbird.nlsexample.NOTIFICATION_LISTENER_SERVICE_EXAMPLE");
            registerReceiver(nlservicereciver,filter);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            unregisterReceiver(nlservicereciver);
        }

        @Override
        public void onNotificationPosted(StatusBarNotification sbn) {

            Log.i(TAG,"**********  onNotificationPosted");
            Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
            Intent i = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
            i.putExtra("notification_event","onNotificationPosted :" + sbn.getPackageName() + "\n");
            i.putExtra("notification_type", "posted");
            sendBroadcast(i);
        }

        @Override
        public void onNotificationRemoved(StatusBarNotification sbn) {
            Log.i(TAG,"********** onNotificationRemoved");
            Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
            Intent i = new  Intent("com.kpbird.nlsexample.NOTIFICATION_LISTENER_EXAMPLE");
            i.putExtra("notification_event","onNotificationRemoved :" + sbn.getPackageName() + "\n");
            i.putExtra("notification_type", "removed");
            sendBroadcast(i);
        }

        class NotificationReceiver extends BroadcastReceiver{

            @Override
            public void onReceive(Context context, Intent intent) {
	            String temp = intent.getStringExtra("notification_event") + "\n" + txtView.getText();
                txtView.setText(temp+"\n --->"+intent.getStringExtra("notification_type"));

                String[] notifKeyWords = {"com.google.android.gm","com.android.mms","com.janbrostudios.smartbelt","com.facebook.orca","com.snapchat.android","com.google.android.talk" };
                String[] falseKeys = {"net.dinglisch.android.taskerm"};
                
                if(checkList(intent.getStringExtra("notification_event"),notifKeyWords,falseKeys)&&intent.toURI().contains("Posted")){
                		notif();
                	pending++;
                }else if(checkList(intent.getStringExtra("notification_event"),notifKeyWords,falseKeys)&&intent.toURI().contains("Removed")){
                    if(pending>0)
                    	pending--;
                }
                if(pending>0)
                	sendMessage("pending".getBytes());
                else
                	sendMessage("cleared".getBytes());
            }
            
            public boolean checkList(String in, String[] words){
            	boolean good = false;
            	for(String regex:words){
            		if(in.contains(regex))
            			good = true;
            	}return good;
            }
            public boolean checkList(String in, String[] words,String[] bad){
            	boolean good = false;
            	for(String regex:bad){
            		if(in.contains(regex))
            			return false;
            	}
            	for(String regex:words){
            		if(in.contains(regex))
            			good = true;
            	}return good;
            }
        }

    }
    
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
     
        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;
     
            // Get the input and output streams, using temp objects because
            // member streams are final
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
     
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }
     
        public void run() {
            byte[] buffer = new byte[1024];  // buffer store for the stream
            int bytes; // bytes returned from read()
     
            // Keep listening to the InputStream until an exception occurs
            while (true) {
                try {
                    // Read from the InputStream
                    bytes = mmInStream.read(buffer);
                	Toast.makeText(context, bytes, Toast.LENGTH_SHORT).show();
                    // Send the obtained bytes to the UI activity
                    //mHandler.obtainMessage(MESSAGE_READ, bytes, -1, buffer)
                    //        .sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }
     
        /* Call this from the main activity to send data to the remote device */
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
            } catch (IOException e) { }
        }
     
        /* Call this from the main activity to shutdown the connection */
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) { }
        }
    }

}