package com.example.bluetooth;

import java.io.IOException;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button b;
	Button b2;
	Button b3;
	Button b4;
	Button b5;
	TextView t1;
	TextView t2;
	EditText et1;
	BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();		
	BluetoothDevice serwer = ba.getRemoteDevice("30:14:12:08:08:17");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		b=(Button) findViewById(R.id.button1);
		b2=(Button) findViewById(R.id.button2);
		b3=(Button) findViewById(R.id.button3);
		b4=(Button) findViewById(R.id.button4);
		b5=(Button) findViewById(R.id.button5);
		t1=(TextView) findViewById(R.id.textView1);
		t2=(TextView) findViewById(R.id.textView2);
		et1=(EditText) findViewById(R.id.editText1);
		
		Log.d("INFO","Uruchomiono program");
		
		b.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				try {
					dajSieWykryc();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});
		b2.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				wykryjInne();				
			}
		});
		
		b3.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				pokazSparowane();				
			}
		});
		b4.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {		
				t2.setText("Jo sem serwer!");
				new SerwerBluetooth().start();
			}
		});
		b5.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				t2.setText("tworze clienta!");
				BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();		
				BluetoothDevice serwer = ba.getRemoteDevice("30:14:12:08:08:17");      
				ClientBluetooth m=new ClientBluetooth(serwer,4);
				m.start();
				
				t2.setText("utworzony!");
				m=null;
			}
		});
		
		
		
		BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
		t1.setText("Twój mac: "+ba.getAddress());
		Log.d("INFO","Twój adres urządzenia: "+ba.getAddress());
		if(!ba.isEnabled()){
			Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			startActivityForResult(i, 1);
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent i){
		if(resultCode==Activity.RESULT_OK){
			Log.d("INFO","Mamy zgodę!");
			BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();					
		}
	}
	
	
	private final BroadcastReceiver odbiorca= new BroadcastReceiver() {		
		@Override
		public void onReceive(Context context, Intent i) {
			String akcja = 	i.getAction();
			if(BluetoothDevice.ACTION_FOUND.equals(akcja)){		
	              BluetoothDevice device = i.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	              String status="";	              
	              if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
	            	  status="nie sparowane";
	              }else{
	            	  status="sparowane";
	              }
	            	  Log.d("INFO","znaleziono urządzenie: "+device.getName()+" - "+device.getAddress()+" - "+status);	         
	      }			
		}
	};
	
	public void dajSieWykryc() throws IOException{
		if(ba.isDiscovering())
		{
			ba.cancelDiscovery();
		}
		ba.startDiscovery();
		Log.d("INFO","Daję się wykryć!");	
		t2.setText("tworze clienta!");
//		BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();		
//		BluetoothDevice serwer = ba.getRemoteDevice("30:14:12:08:08:17");      
		ClientBluetooth m=new ClientBluetooth(serwer,1);
		m.start();
		
		//ba.ACTION_DISCOVERY_FINISHED;
		ba.cancelDiscovery();
		t2.setText("utworzony!");
		m=null;
		
		
		
//		Intent pokazSie = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//		pokazSie.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
//		startActivity(pokazSie);
	}
	
	
	public void wykryjInne(){
		new ClientBluetooth(serwer , 2).start();
	
	}
	
	public void pokazSparowane(){
		/*
		 * Wyświetlanie listy sparowanych urządzeń, niezależnie od tego czy są 
		 * właśnie podłączone czy w ogóle włączone.
		 * */
		Log.d("INFO","Sparowane dla tego urządzenia");
		BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();		
		if (pairedDevices.size() > 0) {	
			for (BluetoothDevice device : pairedDevices) {				
				Log.d("INFO",device.getName()+" - "+device.getAddress());			
			}
		}
	}
	
	
	

}
