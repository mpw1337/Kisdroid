/**
 * 
 */
package de.mpw.kisdroid.map;

import java.util.List;

import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import de.mpw.kisdroid.KismetMsgHandler;
import de.mpw.kisdroid.R;
import de.mpw.kisdroid.R.id;
import de.mpw.kisdroid.R.layout;
import de.mpw.kisdroid.BroadcastReceiver.BroadcastReceiverGPS;

/**
 * @author Markus
 * 
 */
public class MapActivity extends com.google.android.maps.MapActivity {

	/**
	 * 
	 */
	private MapView mv;
	private List<Overlay> mapOverlays; // eine Liste mit allen Overlays des
										// ''MapViews''
	private Drawable drawable; // das ''Drawable'' für unseren Marker
	private NetzwerkItemizedOverlay itemizedOverlay; // unser Overlay
	private BroadcastReceiverGPS mBroadcastReceiverGps;
	final IntentFilter filter = new IntentFilter(KismetMsgHandler.ACTION_GPS);

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		setContentView(R.layout.map);
		mv = (MapView) findViewById(R.id.mv_map);
		mv.setBuiltInZoomControls(true);
		mapOverlays = mv.getOverlays(); // Damit wir später unser Overlay
										// auf die Karte anwenden
										// können,
		mBroadcastReceiverGps = new BroadcastReceiverGPS(mv, getApplicationContext());
		drawable = this.getResources().getDrawable(android.R.drawable.star_on);
		itemizedOverlay = new NetzwerkItemizedOverlay(drawable);
		GeoPoint point = new GeoPoint(52457270, 13526380);
		GeoPoint point1 = new GeoPoint(52457275, 13526300);
		OverlayItem overlayitem2 = new OverlayItem(point1, "", "");
		OverlayItem overlayitem = new OverlayItem(point, "Titel", "Snippet");
		itemizedOverlay.addOverlay(overlayitem);
		itemizedOverlay.addOverlay(overlayitem2);
		mapOverlays.add(0,itemizedOverlay);
		LocationManager mLm = (LocationManager) getSystemService(LOCATION_SERVICE);
	}

	@Override
	protected void onPause() {
		getApplicationContext().unregisterReceiver(mBroadcastReceiverGps);
		super.onPause();
	}

	@Override
	protected void onResume() {
		getApplicationContext().registerReceiver(mBroadcastReceiverGps, filter);
		super.onResume();
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
