package com.example.bluetooth;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Watek_dzialania extends MainActivity implements Runnable{
	boolean polacz=false;
	int zmienna=super.getZmienna();
	ClientBluetooth client;
	BluetoothAdapter adapter= BluetoothAdapter.getDefaultAdapter();		
	BluetoothDevice serwer = adapter.getRemoteDevice("30:14:12:08:08:17");
	static BluetoothSocket socket=null;
	static PrintWriter out;
	ExecutorService wateczek = Executors.newFixedThreadPool(1);
	
	
	
	@Override
	public void run() {
		while(true){
		if(socket == null)
		{
			polacz();
		}
		if(polacz)
		{
			while(getkliknieto())
			{
				wyslij();
			}
		}else
		{
			
		Log.d("INFO", "wchodze do rozlacz");
			rozlacz();
			
		}
		spij(1);
		
	}
		
	}
	
	private void rozlacz() {
		try {
			socket.close();
		} catch (Exception e) {
		//	utworz_socket();
				polacz();
				
			//e.printStackTrace();
		}
		
	}
	boolean Error=false;
	private void wyslij() {
		try{
			Log.d("INFO", "wysylam " + String.valueOf(getZmienna()));
			out.println(getZmienna());	
			if(out.checkError() && Error == false)
			{
				Error=true;
				super.Error = true;
				socket.close();
				polacz();
				
			}
		}catch (Exception e)
		{
			Log.d("INFO", "przycisk nie mogl zapodac cos sie sypie");
			
			polacz();
		}
	}
	private boolean getkliknieto() {
		
		return super.nacisnieto1;
	}
	private void polacz()   {
		
		Future<BluetoothSocket> fut = wateczek.submit(new Watek_polacz());
	
			try {
				if(fut.get() != null)
				{
					socket=fut.get();
					polacz_i_ustaw_przesyl();
					Log.d("INFO", "Mam socket");
					polacz=true;
				}
				else{
					Thread.sleep(1);
					polacz();
				}
				super.Error=false;
				Error=false;
			} catch (Exception e) {
				spij(100);
				polacz();
				Log.d("INFO" ,"b³ad1 :" + e.getMessage());
			}
		
	}
	private void polacz_i_ustaw_przesyl() throws IOException {
		socket.connect();
		out = new PrintWriter(socket.getOutputStream(),true);
		
	}
	private void spij(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	

}
