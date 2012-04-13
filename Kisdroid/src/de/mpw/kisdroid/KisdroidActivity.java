package de.mpw.kisdroid;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import de.mpw.kisdroid.BroadcastReceiver.BroadcastReceiverSsid;
import de.mpw.kisdroid.BroadcastReceiver.BroadcastReceiverTime;
import de.mpw.kisdroid.map.MapActivity;

public class KisdroidActivity extends Activity {

	public KismetClient client;
	public String SERVER;
	public int PORT;

	private SharedPreferences mPref;
	private TextView tv_Networks;
	private TextView tv_strength;
	private TextView tv_mac;
	private TextView tv_server_port;
	private TextView tv_time;
	private TextView tv_encryption;
	private TextView tv_channel;

	private BroadcastReceiverTime mBroadcastReceiverTime;
	private BroadcastReceiverSsid mBroadcastReceiverSsid;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// Text View für die SSID's zuweisen
		tv_Networks = (TextView) findViewById(R.id.tv_Networks);
		tv_strength = (TextView) findViewById(R.id.tv_strength);
		tv_mac = (TextView) findViewById(R.id.tv_mac);
		tv_server_port = (TextView) findViewById(R.id.tv_server_port);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_encryption = (TextView) findViewById(R.id.tv_encryption);
		tv_channel = (TextView) findViewById(R.id.tv_channel);
		mPref = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
		// Broadcast Receiver für Time initialisieren
		mBroadcastReceiverTime = new BroadcastReceiverTime(tv_time);
		// Broadcast Receiver für die Netzwerke initialisieren
		mBroadcastReceiverSsid = new BroadcastReceiverSsid(tv_Networks, tv_strength, tv_mac,
				tv_encryption, tv_channel, this.getApplicationContext());
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Intent Filter für die SSID's erstellen
		IntentFilter filter = new IntentFilter(KismetMsgHandler.ACTION_SSID);
		// Receiver für die SSID Braodcasts registrieren
		getApplicationContext().registerReceiver(mBroadcastReceiverSsid, filter);

		// Receiver für die Time Broadcasts registrieren
		filter = new IntentFilter(KismetMsgHandler.ACTION_TIME);
		getApplicationContext().registerReceiver(mBroadcastReceiverTime, filter);
		String tx_server_port = getResources().getString(R.string.tx_server_port);
		tx_server_port = tx_server_port.replace("SERVER",
				mPref.getString(Einstellungen.KEY_HOST, "127.0.0.1")).replace("PORT",
				mPref.getString(Einstellungen.KEY_PORT, "2501"));
		tv_server_port.setText(tx_server_port);
		Einstellungenlesen(Einstellungen.KEY_SSID, tv_Networks);
		Einstellungenlesen(Einstellungen.KEY_MAC, tv_mac);
		Einstellungenlesen(Einstellungen.KEY_STRENGTH, tv_strength);
		Einstellungenlesen(Einstellungen.KEY_CHANNEL, tv_channel);
	}

	private void Einstellungenlesen(String key, TextView textview) {
		if (mPref.getBoolean(key, true)) {
			textview.setVisibility(View.VISIBLE);
		} else {
			textview.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onPause() {
		// Receiver für die SSID Bradcasts abmelden
		getApplicationContext().unregisterReceiver(mBroadcastReceiverSsid);
		getApplicationContext().unregisterReceiver(mBroadcastReceiverTime);
		super.onPause();
	}

	/** Click Handler für die Schaltflächen */
	// public void onClick(final View view) {
	//
	// switch (view.getId()) {
	// default:
	// break;
	// }
	// }

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
			break;
		case R.id.opt_battery:
			startActivity(new Intent(this, Netzwerke.class));
			break;
		case R.id.opt_map:
			startActivity(new Intent(this, MapActivity.class));
			break;
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