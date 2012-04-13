/**
 * 
 */
package de.mpw.kisdroid;

import android.app.ListActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import de.mpw.kisdroid.BroadcastReceiver.BroadcastReceiverNetzwerke;

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
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
		mBroadcastReceiverNetzwerke = new BroadcastReceiverNetzwerke(adapter);
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
