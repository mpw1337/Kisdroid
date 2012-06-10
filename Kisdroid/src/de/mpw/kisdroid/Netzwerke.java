/**
 * 
 */
package de.mpw.kisdroid;

import android.app.ListActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import de.mpw.kisdroid.BroadcastReceiver.BroadcastReceiverNetzwerke;
import de.mpw.kisdroid.adapter.NetzwerkAdapter;

/**
 * @author Markus
 * 
 */
public class Netzwerke extends ListActivity {

	private String[] netzwerke;
	private BroadcastReceiverNetzwerke mBroadcastReceiverNetzwerke;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.netzwerke);
		getListView();
		NetzwerkAdapter adapter = new NetzwerkAdapter(this,
				R.layout.net_list);
		setListAdapter(adapter);
		mBroadcastReceiverNetzwerke = new BroadcastReceiverNetzwerke(adapter);
		String[] temp = new String[2];
		temp[0] = "test";
		temp[1] = "Mac adresse";
		adapter.add(temp);
	}


	@Override
	protected void onPause() {
		getApplicationContext().unregisterReceiver(mBroadcastReceiverNetzwerke);
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Intent Filter für die SSID's erstellen
		IntentFilter filter = new IntentFilter(KismetMsgHandler.ACTION_SSID);
		// Receiver für die SSID Braodcasts registrieren
		getApplicationContext().registerReceiver(mBroadcastReceiverNetzwerke, filter);
	}

}
