package de.mpw.kisdroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KisdroidActivity extends Activity {
	
	public KismetClient client;
	public String SERVER;
	public int PORT;
	
	private TextView  tv_Networks;
	
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			tv_Networks.setText(intent.getExtras().getString("SSID"));
			
		}
	};
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        // Text View für die SSID's zuweisen
        tv_Networks = (TextView) findViewById(R.id.tv_Networks);
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		//Intent Filter für die SSID's erstellen
		final IntentFilter filter = new IntentFilter(KismetClient.ACTION_SSID);
		//Receiver für die SSID Braodcasts registrieren
		getApplicationContext().registerReceiver(mBroadcastReceiver, filter);
	}

	@Override
	protected void onPause() {
		//Receiver für die SSID Bradcasts abmelden
		getApplicationContext().unregisterReceiver(mBroadcastReceiver);
		super.onPause();
	}
	/** Click Handler für die Schaltflächen */
	public void onClick(final View view){
		
    	switch (view.getId()) {
		case R.id.sf_start:
			SERVER= "192.168.2.11";
			PORT = 2501;
			//client = new KismetClient(SERVER,PORT);
			//client.start();
			startService(new Intent(this, KismetService.class));
			break;
		case R.id.sf_stop:
			//client.stopClient();
			stopService(new Intent(this, KismetService.class));
			break;

		default:
			break;
		}
    }
}