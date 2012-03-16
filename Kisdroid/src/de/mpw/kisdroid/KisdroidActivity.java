package de.mpw.kisdroid;

import de.mpw.kisdroid.protocols.Ssid;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class KisdroidActivity extends Activity {

	public KismetClient client;
	public String SERVER;
	public int PORT;

	private TextView tv_Networks;
	private TextView tv_strength;
	private TextView tv_mac;

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			/*
			 * tv_Networks.setText(intent.getExtras().getString("SSID"));
			 */
			tv_Networks.setText("");
			String[] networks = intent.getExtras().getStringArray(Ssid.EXTRA);
			for (String ssid : networks) {
				// Log.d(getLocalClassName(), ssid);
				tv_Networks.append("\n " + ssid);
			}
			tv_strength.setText("");
			String[] maxstrength = intent.getStringArrayExtra(Ssid.EXTRA_MAXSTRENGTH);
			for (String strength : maxstrength) {
				tv_strength.append("\n" + strength);
			}
			tv_mac.setText("");
			String[] mac = intent.getStringArrayExtra(Ssid.EXTRA_MAC);
			for (String string : mac) {
				tv_mac.append("\n" + string);
			}

		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Text View für die SSID's zuweisen
		tv_Networks = (TextView) findViewById(R.id.tv_Networks);
		tv_strength = (TextView) findViewById(R.id.tv_strength);
		tv_mac = (TextView) findViewById(R.id.tv_mac);

	}

	@Override
	protected void onResume() {
		super.onResume();
		// Intent Filter für die SSID's erstellen
		final IntentFilter filter = new IntentFilter(KismetClient.ACTION_SSID);
		final IntentFilter ssida = new IntentFilter(Ssid.EXTRA);
		// Receiver für die SSID Braodcasts registrieren
		getApplicationContext().registerReceiver(mBroadcastReceiver, filter);
		getApplicationContext().registerReceiver(mBroadcastReceiver, ssida);
	}

	@Override
	protected void onPause() {
		// Receiver für die SSID Bradcasts abmelden
		getApplicationContext().unregisterReceiver(mBroadcastReceiver);
		super.onPause();
	}

	/** Click Handler für die Schaltflächen */
	public void onClick(final View view) {

		switch (view.getId()) {
		case R.id.sf_start:
			SERVER = "192.168.2.11";
			PORT = 2501;
			// client = new KismetClient(SERVER,PORT);
			// client.start();
			startService(new Intent(this, KismetService.class));
			break;
		case R.id.sf_stop:
			// client.stopClient();
			stopService(new Intent(this, KismetService.class));
			break;
		default:
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.hauptmenue, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.opt_einstellungen:
			startActivity(new Intent(this, Einstellungen.class));
			break;
		case R.id.opt_start_stop:
			if (isMyServiceRunning()) {
				stopService(new Intent(this, KismetService.class));
			} else {
				startService(new Intent(this, KismetService.class));
			}
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private boolean isMyServiceRunning() {
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if (KismetService.class.getName().equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}