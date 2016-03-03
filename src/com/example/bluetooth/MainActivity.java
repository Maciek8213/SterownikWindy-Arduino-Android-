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
	public static String zmienna;
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
			//Toast.makeText(MainActivity.this, "Próbuje wznowic polczenie",
			//				   Toast.LENGTH_SHORT).show(); 
			nacisnieto1=false;
			wyswietl("Brak polaczenia");
			return false;
			
		}
		else 
		 if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) 
		{
			
		 	nacisnieto1=true;
		 	setZmienna("g");
		 	wyswietl("idzie w gore");
		 	
		 } 
		 else if (event.getAction() == android.view.MotionEvent.ACTION_UP) 
		 {
			 nacisnieto1=false;
			 setZmienna("0");
			 Error=false;
			 wyswietl("");
		 }
		return false;
			}});
			
		dol.setOnTouchListener(new OnTouchListener () {
			public boolean onTouch(View view, MotionEvent event) {
			if(Error)
				 {
		//Toast.makeText(MainActivity.this, "Próbuje wznowic polczenie",
		//					   Toast.LENGTH_SHORT).show(); 
			nacisnieto1=false;
			wyswietl("Brak polaczenia");
			
			return false;
			
		}
		else 
		 if (event.getAction() == android.view.MotionEvent.ACTION_DOWN) 
		{
			
		 	nacisnieto1=true;
		 	setZmienna("1");
		 	wyswietl("idzie w dol");
		 	
		 } 
		 else if (event.getAction() == android.view.MotionEvent.ACTION_UP) 
		 {
			 nacisnieto1=false;
			 setZmienna("0");
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


	public static String getZmienna() {
		return zmienna;
	}

	public static void setZmienna(String string) {
		MainActivity.zmienna = string;
	}
	


	
	
	

}
