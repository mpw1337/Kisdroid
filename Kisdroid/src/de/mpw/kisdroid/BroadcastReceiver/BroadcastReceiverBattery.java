/**
 * 
 */
package de.mpw.kisdroid.BroadcastReceiver;

import de.mpw.kisdroid.protocols.Battery;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author Markus
 *
 */
public class BroadcastReceiverBattery extends BroadcastReceiver {

	private ProgressBar pb_battery_status;
	private TextView tx_bat_percentage;


	/**
	 * Creator Methode
	 * Bekommt eine ProgressBar um die Batterie Ladung visuell darzustellen
	 * Auf tx_bp wird der Ladezustand als Zahl ausgegeben
	 */
	public BroadcastReceiverBattery(ProgressBar pb_bs, TextView tx_bp) {
		this.pb_battery_status = pb_bs;
		this.tx_bat_percentage = tx_bp;
	}

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		pb_battery_status.setProgress(intent.getIntExtra(Battery.EXTRA_PERCENTAGE, 100));
		tx_bat_percentage.setText(String.valueOf(intent.getIntExtra(Battery.EXTRA_PERCENTAGE,100)));

	}

}
