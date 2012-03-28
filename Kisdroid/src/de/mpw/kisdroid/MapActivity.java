/**
 * 
 */
package de.mpw.kisdroid;

import com.google.android.maps.MapView;

import android.os.Bundle;

/**
 * @author Markus
 *
 */
public class MapActivity extends com.google.android.maps.MapActivity {

	/**
	 * 
	 */
	private MapView mv;
	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.map);
		mv = (MapView) findViewById(R.id.mv_map);
	}
	/* (non-Javadoc)
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}


}
