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
	private TextView tx_battery_status;


	/**
	 * Creator Methode
	 * Bekommt eine ProgressBar um die Batterie Ladung visuell darzustellen
	 * Auf tx_bp wird der Ladezustand als Zahl ausgegeben
	 * @param tx_bs Text Feld für den Status des Akkus
	 * @param pb_bs ProgressBar die den Ladezustand der Battery grafisch darstellt
	 * @param tx_bp Text Feld das den Ladezustand der Battery als Zahl in Prozent angibt
	 */
	public BroadcastReceiverBattery(ProgressBar pb_bs, TextView tx_bp, TextView tx_bs) {
		this.pb_battery_status = pb_bs;
		this.tx_bat_percentage = tx_bp;
		this.tx_battery_status = tx_bs;
	}

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		pb_battery_status.setProgress(intent.getIntExtra(Battery.EXTRA_PERCENTAGE, 100));
		tx_bat_percentage.setText(String.valueOf(intent.getIntExtra(Battery.EXTRA_PERCENTAGE,100)) + "%");
		tx_battery_status.setText(intent.getStringExtra(Battery.EXTRA_STATUS));

	}

}
