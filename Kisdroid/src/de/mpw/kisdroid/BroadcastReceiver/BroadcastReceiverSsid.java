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
	private TextView tv_channel;
	private int count;
	private Context ctx;
	private Resources res;

	/*
	 * Im Construktor werden die TextViews f�r den Namen und Mac Adresse
	 * �bergeben
	 */
	/**
	 * 
	 * @param network TextView f�r die Namen
	 * @param strength TextView f�r die St�rke in dbm
	 * @param mac TextView f�r die MAC Adresse
	 * @param encry TextView f�r die Verschl�sselung
	 * @param channel TextView f�r den Channel
	 * @param context Context der Application f�r die Resourcen
	 */
	public BroadcastReceiverSsid(TextView network, TextView strength, TextView mac, TextView encry,
			TextView channel, Context context) {
		this.tv_Networks = network;
		this.tv_mac = mac;
		this.tv_strength = strength;
		this.tv_encryption = encry;
		this.tv_channel = channel;
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
		tv_strength.setText(res.getString(R.string.tx_rxl));
		String[] maxstrength = intent.getStringArrayExtra(Ssid.EXTRA_MAXSTRENGTH);
		for (String strength : maxstrength) {
			tv_strength.append("\n" + strength);
		}
		tv_mac.setText(res.getString(R.string.tx_mac));
		String[] mac = intent.getStringArrayExtra(Ssid.EXTRA_MAC);
		for (String string : mac) {
			tv_mac.append("\n" + string);
		}
		tv_encryption.setText(res.getString(R.string.tx_encryption));
		String[] encryption = intent.getStringArrayExtra(Bssid.EXTRA_ENCRYPTION);
		for (String string : encryption) {
			tv_encryption.append("\n" + string);
		}
		tv_channel.setText(res.getString(R.string.tx_channel));
		String[] channel = intent.getStringArrayExtra(Bssid.EXTRA_CHANNEL);
		for (String string : channel) {
			tv_channel.append("\n" + string);
		}

	}

}
