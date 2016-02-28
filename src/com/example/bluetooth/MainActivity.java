package com.example.bluetooth;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Handler hand ;	
	ImageButton gora;
	ImageButton dol;
	public static boolean nacisnieto1=false;
	public static boolean Error=false;
	TextView t1 ;
	public static int zmienna;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		t1= (TextView) findViewById(R.id.textView);
		gora=(ImageButton) findViewById(R.id.gora);
		dol=(ImageButton) findViewById(R.id.dol);
		wyswietl("Windziocha :D");
		
		new Thread(new Watek_dzialania()).start(); // rozpoczyna watek dzialania
		

		gora.setOnTouchListener(new OnTouchListener () {
			public boolean onTouch(View view, MotionEvent event) {
			if(Error)
				 {
			Toast.makeText(MainActivity.this, "Pr�buje wznowic polczenie",
							   Toast.LENGTH_SHORT).show(); 
			nacisnieto1=false;
			wyswietl("Brak polaczenia");
			return false;
			
		}
		else 
		 if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) 
		{
			
		 	nacisnieto1=true;
		 	setZmienna(1);
		 	wyswietl("idzie w gore");
		 	
		 } 
		 else if (event.getAction() == android.view.MotionEvent.ACTION_UP) 
		 {
			 nacisnieto1=false;
			 setZmienna(0);
			 Error=false;
			 wyswietl("");
		 }
		return false;
			}});
			
		dol.setOnTouchListener(new OnTouchListener () {
			public boolean onTouch(View view, MotionEvent event) {
			if(Error)
				 {
		//Toast.makeText(MainActivity.this, "Pr�buje wznowic polczenie",
		//					   Toast.LENGTH_SHORT).show(); 
			nacisnieto1=false;
			wyswietl("Brak polaczenia");
			
			return false;
			
		}
		else 
		 if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) 
		{
			
		 	nacisnieto1=true;
		 	setZmienna(3);
		 	wyswietl("idzie w dol");
		 	
		 } 
		 else if (event.getAction() == android.view.MotionEvent.ACTION_UP) 
		 {
			 nacisnieto1=false;
			 setZmienna(0);
			 Error=false;
			 wyswietl("");
		 }
		return false;
	}});	
		
		
		
	
		
		
	
	}
	
	public void wyswietl(String string) 
	{
		t1.setText(string);
		
	}
	
//	private void inicjalizuj_socket() {
//		
//		Future<BluetoothSocket> fut = wateczek.submit(new Watek_polacz());
//		try {
//			if(fut.get() != null)
//			{
//				socket=fut.get();
//				Log.d("INFO", "socket nie pusty");
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			
//			//e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}


//	private void czy_jestem_polaczony() {
//	
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while(true)
//				{
//				BluetoothSocket socket1=socket;
//				if(socket1.isConnected() &&  socket !=null)
//				{
//					try {
//						Thread.sleep(1000);
//						//inicjalizuj_socket();
//						//Log.d("INOF", "spie xd");
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						Log.d("INFO", "nie chce spac");
//					}
//				}else
//				{
//					try {
//						Log.d("INFO", "probuje wznowic polaczenie ");
//						inicjalizuj_socket();
//						socket.connect();
//						out = new PrintWriter(socket.getOutputStream(),true);
//						
//					} catch (IOException e) {
//						Log.d("INFO", "nie moge nawiazac polaczenia: "+e.getMessage());
//						
//						e.printStackTrace();
//					}
//					
//				}
//				}
//			}
//		}).start();
//		
//	
//	}

//	@Override
//	protected void onActivityResult(int requestCode,int resultCode,Intent i){
//		if(resultCode==Activity.RESULT_OK){
//			Log.d("INFO","Mamy zgodę!");
//			adapter = BluetoothAdapter.getDefaultAdapter();					
//		}
//	}
	
	
	
	
	public void idz_do_gory() throws IOException{
//		if(adapter.isDiscovering())
//		{
//			adapter.cancelDiscovery();
//		}
//		adapter.startDiscovery();
		Log.d("INFO","Daję się wykryć!");	
		t1.setText("tworze clienta!");
 
		//ClientBluetooth m=new ClientBluetooth(serwer,1);
		
		

	//	adapter.cancelDiscovery();
		t1.setText("utworzony!");
		//m=null;
		
		
		
//		Intent pokazSie = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//		pokazSie.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//		startActivity(pokazSie);
	}
	
	public boolean nacisnieto()
	{
		return nacisnieto1;
	}

	public static int getZmienna() {
		return zmienna;
	}

	public static void setZmienna(int zmienna) {
		MainActivity.zmienna = zmienna;
	}
	


	
	
	

}
