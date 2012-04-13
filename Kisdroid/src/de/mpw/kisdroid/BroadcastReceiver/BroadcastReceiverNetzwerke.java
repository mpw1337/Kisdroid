/**
 * 
 */
package de.mpw.kisdroid.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
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
	private ArrayAdapter<String> mAdapter;
	public BroadcastReceiverNetzwerke(ArrayAdapter<String> adapter) {
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
			mAdapter.add(networks[i] + mac[i]);
		}
	}

}
