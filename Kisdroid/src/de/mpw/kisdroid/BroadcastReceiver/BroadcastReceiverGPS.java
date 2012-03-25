package de.mpw.kisdroid.BroadcastReceiver;

import de.mpw.kisdroid.protocols.GPS;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BroadcastReceiverGPS extends BroadcastReceiver {

	private TextView tv_lat;
	private TextView tv_lon;

	public BroadcastReceiverGPS(TextView lat, TextView lon) {
		this.tv_lat = lat;
		this.tv_lon = lon;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extra = intent.getExtras();
		tv_lat.setText(extra.getString(GPS.EXTRA_LAT));
		tv_lon.setText(extra.getString(GPS.EXTRA_LON));

	}

}
