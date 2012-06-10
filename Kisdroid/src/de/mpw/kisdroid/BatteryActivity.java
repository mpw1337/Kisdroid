package de.mpw.kisdroid;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import de.mpw.kisdroid.BroadcastReceiver.BroadcastReceiverBattery;

public class BatteryActivity extends Activity {

	private ProgressBar pb_battery_status;
	private TextView tx_bat_percentage;
	private TextView tx_battery_status;

	private BroadcastReceiverBattery mBroadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.battery);
		pb_battery_status = (ProgressBar) findViewById(R.id.pb_battery_status);
		tx_bat_percentage = (TextView) findViewById(R.id.tx_bat_percent);
		tx_battery_status = (TextView) findViewById(R.id.tx_bat_status);
		mBroadcast = new BroadcastReceiverBattery(pb_battery_status, tx_bat_percentage, tx_battery_status);

	}

	@Override
	protected void onPause() {
		getApplicationContext().unregisterReceiver(mBroadcast);
		super.onPause();
	}

	@Override
	protected void onResume() {
		final IntentFilter filter = new IntentFilter(KismetMsgHandler.ACTION_BATTERY);
		getApplicationContext().registerReceiver(mBroadcast, filter);
		super.onResume();
	}

}
