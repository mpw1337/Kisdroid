package de.mpw.kisdroid.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TextView;
import de.mpw.kisdroid.R;
import de.mpw.kisdroid.protocols.Bssid;
import de.mpw.kisdroid.protocols.Ssid;

public class BroadcastReceiverSsid extends BroadcastReceiver {

	private TextView tv_Networks;
	private TextView tv_strength;
	private TextView tv_encryption;
	private TextView tv_mac;
	private int count;
	private Context ctx;
	private Resources res;

	/*
	 * Im Construktor werden die TextViews für den Namen und Mac Adresse
	 * übergeben
	 */
	public BroadcastReceiverSsid(TextView network, TextView strength, TextView mac, TextView encry,Context context) {
		this.tv_Networks = network;
		this.tv_mac = mac;
		this.tv_strength = strength;
		this.tv_encryption = encry;
		this.ctx = context;
		this.res = ctx.getResources();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent) Angepasste Version um die Netzwerke anzuzeigen
	 */

	@Override
	public void onReceive(Context contenxt, Intent intent) {
		tv_Networks.setText("");
		String[] networks = intent.getExtras().getStringArray(Ssid.EXTRA);
		count = networks.length;
		tv_Networks.setText(res.getString(R.string.tx_ssid) + count);
		for (String ssid : networks) {
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
		tv_encryption.setText("");
		String[] encryption = intent.getStringArrayExtra(Bssid.EXTRA_ENCRYPTION);
		for (String string : encryption) {
			tv_encryption.append("\n" + string);
		}

	}

}
