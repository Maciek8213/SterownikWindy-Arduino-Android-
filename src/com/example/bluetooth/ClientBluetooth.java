package com.example.bluetooth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.UUID;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

public class ClientBluetooth implements Runnable {
    public final BluetoothSocket mmSocket;
    private final BluetoothDevice mmDevice;
    private int p;
    public ClientBluetooth(BluetoothDevice device, int i)  {   
    	p=i;
        BluetoothSocket tmp = null;
        BluetoothSocket tmp2 = null;
        mmDevice = device;
        try {            
        	UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            tmp = device.createRfcommSocketToServiceRecord(uuid);
            tmp2 =(BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(device,1);
        } catch (Exception e) { }
        mmSocket = tmp2;
    }
 
    @SuppressLint("NewApi")
	public void run() {
         try {     
        	Log.d("INFO","Próba połączenia....");
        	
        	mmSocket.connect();
        	PrintWriter out = new PrintWriter(mmSocket.getOutputStream(),true);
        	while(mmSocket.isConnected()){
        	
        	Log.d("INFO","Połączono z serwerem!");
   //         BufferedReader in = new BufferedReader(new InputStreamReader(mmSocket.getInputStream()));
           
            out.println(p);
           
            Log.d("INFO","Wys�ano!");
//            String input = in.readLine();  
//            Log.d("INFO","Serwer mówi: "+input);
        	} 
            mmSocket.close();
           
            Log.d("INFO","Połączono zakonczone!");
        } catch (Exception ce) {
        	Log.d("INFO", "wysypal sie bt " + ce.getMessage());
            try {
                mmSocket.close();
            } catch (Exception cle) { }            
        }
 
    } 
}
