/**
 * 
 */
package de.mpw.kisdroid;

import android.os.Bundle;

import com.google.android.maps.MapView;

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
		mv.setBuiltInZoomControls(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.MapActivity#isRouteDisplayed()
	 */
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
