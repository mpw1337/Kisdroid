/**
 * 
 */
package de.mpw.kisdroid.map;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import de.mpw.kisdroid.R;
import de.mpw.kisdroid.R.id;
import de.mpw.kisdroid.R.layout;

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
		drawable = this.getResources().getDrawable(android.R.drawable.star_on);
		itemizedOverlay = new NetzwerkItemizedOverlay(drawable);
		GeoPoint point = new GeoPoint(52457270,13526380);
		OverlayItem overlayitem = new OverlayItem(point, "", "");
		itemizedOverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedOverlay);
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
