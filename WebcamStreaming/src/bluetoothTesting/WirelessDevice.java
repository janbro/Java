package bluetoothTesting;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Vector;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class WirelessDevice {

// main method of the application
public static void main(String[] args) throws IOException {
    // display local device address and name
    LocalDevice localDevice = LocalDevice.getLocalDevice();

    System.out.println("Address: " + localDevice.getBluetoothAddress());
    System.out.println("Name: " + localDevice.getFriendlyName());

    UUID uuids[] = new UUID[1];
    uuids[0] = new UUID("00001000800000805f9b34f", false);
    localDevice.setDiscoverable(DiscoveryAgent.GIAC); // Advertising the service
    
    String deviceName="HC-06";

    String url = "btspp://localhost:" + uuids[0] + ";name="+deviceName;
    StreamConnectionNotifier server = (StreamConnectionNotifier) Connector.open(url);

    StreamConnection connection = server.acceptAndOpen(); // Wait until client connects
    System.out.println("Connected!");
    //=== At this point, two devices should be connected ===//
    DataInputStream dis = connection.openDataInputStream();
    
    OutputStream dos = connection.openOutputStream();
    
    dos.write(1);

    char c;
    while (true) {
        c = dis.readChar();
        if (c == 'x')
            break;
    }

    connection.close();

}// end main

}// end class