/**
 *
 */
package de.mpw.kisdroid;

import de.mpw.kisdroid.map.MapActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * @author Markus
 * 
 */
public class TabActivity extends android.app.TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maintab);
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, KisdroidActivity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("main")
				.setIndicator("Main", res.getDrawable(android.R.drawable.ic_dialog_info))
				.setContent(intent);
		tabHost.addTab(spec);
		// Do the same for the other tabs
		intent = new Intent().setClass(this, BatteryActivity.class);
		spec = tabHost.newTabSpec("battery")
				.setIndicator("Battery", res.getDrawable(android.R.drawable.ic_lock_idle_charging))
				.setContent(intent);
		tabHost.addTab(spec);
		intent = new Intent().setClass(this, MapActivity.class);
		spec = tabHost.newTabSpec("map")
				.setIndicator("Map", res.getDrawable(android.R.drawable.ic_media_play))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);

	}

	/**
*
*/

}