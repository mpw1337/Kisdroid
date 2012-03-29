/**
 * 
 */
package de.mpw.kisdroid.BroadcastReceiver;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import de.mpw.kisdroid.map.NetzwerkItemizedOverlay;
import de.mpw.kisdroid.protocols.GPS;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

/**
 * @author Markus
 * 
 */
public class BroadcastReceiverGPSNetzwerke extends BroadcastReceiver {

	/**
	 * 
	 */
	private MapView mv;
	private List<Overlay> mapOverlays;
	private NetzwerkItemizedOverlay itemizedOverlay;
	private int drawableid = android.R.drawable.star_off;
	private Drawable drawable;

	public BroadcastReceiverGPSNetzwerke(MapView tmv, Context ctx) {
		this.mv = tmv;
		mapOverlays = mv.getOverlays();
		drawable = ctx.getResources().getDrawable(drawableid);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.content.BroadcastReceiver#onReceive(android.content.Context,
	 * android.content.Intent)
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extra = intent.getExtras();
		String[] lat = extra.getStringArray(GPS.EXTRA_LAT_ARRAY);
		String[] lon = extra.getStringArray(GPS.EXTRA_LON_ARRAY);
		if ((lat != null) && (lon != null)) {
			if(mapOverlays.size()>0){
				mapOverlays.remove(0);
			}
			itemizedOverlay = new NetzwerkItemizedOverlay(drawable);
			for (int i = 0; i < lat.length; i++) {
				if ((lat[i] != null) && (lon[i] != null)) {
					GeoPoint point = new GeoPoint((int) (Float.parseFloat(lat[i]) * 1E6),
							(int) (Float.parseFloat(lon[i]) * 1E6));
					OverlayItem overlayitem = new OverlayItem(point, "Titel", "Snippet");
					itemizedOverlay.addOverlay(overlayitem);
				}
			}
			mapOverlays.add(0, itemizedOverlay);
		}

	}
}
