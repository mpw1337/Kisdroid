package de.mpw.kisdroid.BroadcastReceiver;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import de.mpw.kisdroid.map.NetzwerkItemizedOverlay;
import de.mpw.kisdroid.protocols.GPS;

public class BroadcastReceiverGPS extends BroadcastReceiver {

	private TextView tv_lat;
	private TextView tv_lon;
	private String lat;
	private String lon;
	private MapView mv;
	private List<Overlay> mapOverlays;
	private NetzwerkItemizedOverlay itemizedOverlay;
	private int drawableid = android.R.drawable.star_on;
	private Drawable drawable;

	public BroadcastReceiverGPS(TextView lat, TextView lon) {
		this.tv_lat = lat;
		this.tv_lon = lon;
	}

	public BroadcastReceiverGPS(MapView tmv, Context ctx) {
		this.mv = tmv;
		mapOverlays = mv.getOverlays();
		drawable = ctx.getResources().getDrawable(drawableid);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extra = intent.getExtras();
		lat = extra.getString(GPS.EXTRA_LAT);
		lon = extra.getString(GPS.EXTRA_LON);
		if ((tv_lat != null) && (tv_lon != null)) {
			tv_lat.setText(lat);
			tv_lon.setText(lon);
		}
		if ((mv != null) && (lat != null) && (lon != null)) {
			if (mapOverlays.size() > 1) {
				mapOverlays.remove(1);
			}
			itemizedOverlay = new NetzwerkItemizedOverlay(drawable);
			GeoPoint point = new GeoPoint((int) (Float.parseFloat(lat) * 1E6),
					(int) (Float.parseFloat(lon) * 1E6));
			OverlayItem overlayitem = new OverlayItem(point, "Titel", "Snippet");
			itemizedOverlay.addOverlay(overlayitem);
			if (mapOverlays.size() == 0) {
				mapOverlays.add(itemizedOverlay);
			} else {
				mapOverlays.add(1, itemizedOverlay);
			}
		}
	}
}
