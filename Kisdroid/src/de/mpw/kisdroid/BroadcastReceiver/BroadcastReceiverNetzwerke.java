/**
 * 
 */
package de.mpw.kisdroid.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import de.mpw.kisdroid.adapter.NetzwerkAdapter;
import de.mpw.kisdroid.protocols.Bssid;
import de.mpw.kisdroid.protocols.Ssid;

/**
 * @author Markus
 *
 */
public class BroadcastReceiverNetzwerke extends BroadcastReceiver {

	/**
	 * 
	 */
	private NetzwerkAdapter mAdapter;
	public BroadcastReceiverNetzwerke(NetzwerkAdapter adapter) {
		this.mAdapter = adapter;
	}

	/* (non-Javadoc)
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context, android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		mAdapter.clear();
		String[] networks = intent.getExtras().getStringArray(Ssid.EXTRA);
		String[] maxstrength = intent.getStringArrayExtra(Ssid.EXTRA_MAXSTRENGTH);
		String[] mac = intent.getStringArrayExtra(Ssid.EXTRA_MAC);
		String[] encryption = intent.getStringArrayExtra(Bssid.EXTRA_ENCRYPTION);
		String[] channel = intent.getStringArrayExtra(Bssid.EXTRA_CHANNEL);
		for (int i = 0; i < networks.length; i++) {
			String[] temp = new String[2];
			temp[0] = networks[i];
			temp[1] = mac[i];
			mAdapter.add(temp);
		}
	}

}
