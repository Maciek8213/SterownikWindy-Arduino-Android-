package com.example.bluetooth;

import java.util.UUID;
import java.util.concurrent.Callable;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

public class Watek_polacz implements Callable<BluetoothSocket>{
	 public BluetoothSocket mmSocket=null;
	 private  BluetoothDevice mmDevice=null;
	 BluetoothAdapter adapter= BluetoothAdapter.getDefaultAdapter();
	 BluetoothDevice device = adapter.getRemoteDevice("30:14:12:08:08:17");
	 
	@Override
	public	BluetoothSocket call() {
        BluetoothSocket tmp2 = null;
    //  BluetoothDevice device;
	mmDevice = device;
        try {            
        	UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            tmp2 =(BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[] {int.class}).invoke(device,1);
            mmSocket = tmp2;
        } catch (Exception e) {
        	
        	mmSocket=null;
        }
        
        if(mmSocket == null)
        {
        	call();
        }
        return mmSocket;
	}

}
