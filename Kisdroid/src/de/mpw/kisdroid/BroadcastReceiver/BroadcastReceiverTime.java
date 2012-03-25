package de.mpw.kisdroid.BroadcastReceiver;

import de.mpw.kisdroid.protocols.TimeP;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class BroadcastReceiverTime extends BroadcastReceiver {
	
	private TextView tv_time;

	public BroadcastReceiverTime(TextView tv) {
		this.tv_time = tv;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		tv_time.setText(intent.getExtras().getString(TimeP.EXTRA_TIME));
		
	}

}
