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
 * @Description Spezieller BroadcastReceiver f�r GPSNetzwerke.
 * @intent de.mpw.kisdroid.intent.action.GPS_NETWORK
 */
public class BroadcastReceiverGPSNetzwerke extends BroadcastReceiver {

	/**
	 * 
	 */
	private MapView mv;
	private List<Overlay> mapOverlays; // Liste der Overlays
	private NetzwerkItemizedOverlay itemizedOverlay; // Overlay f�r die
														// Netzwerke
	private int drawableid = android.R.drawable.star_off; // Aktuell noch
															// Platzhalter f�r
															// ein Finales
															// Drawable
	private Drawable drawable; // Die eigentliche Drawable

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
		// Extra Bundle aus dem Intent holen
		Bundle extra = intent.getExtras();
		// Arrays f�r die Geografischen Daten aus den Extras holen
		String[] lat = extra.getStringArray(GPS.EXTRA_LAT_ARRAY);
		String[] lon = extra.getStringArray(GPS.EXTRA_LON_ARRAY);
		// Wenn die beiden Objekte nicht leer sind und die Liste der Map
		// Overlays gr��er als 0 ist, wird das mapOverlay an der Ersten (mit der
		// id 0) Stelle gel�scht. An dieser Stelle befindet sich das Overlay mit
		// den Netzwerken.
		if ((lat != null) && (lon != null)) {
			if (mapOverlays.size() > 0) {
				mapOverlays.remove(0);
			}
			// Neues Overlay mit dem Standard drawable erstellen
			itemizedOverlay = new NetzwerkItemizedOverlay(drawable);
			// mit einer Schleife durch das gesamte Array von Geografischen
			// Daten laufen.
			for (int i = 0; i < lat.length; i++) {
				// Null check, damit es keine Fehler beim erstellen des
				// GeoPoints gibt.
				if ((lat[i] != null) && (lon[i] != null)) {
					GeoPoint point = new GeoPoint((int) (Float.parseFloat(lat[i]) * 1E6),
							(int) (Float.parseFloat(lon[i]) * 1E6));
					OverlayItem overlayitem = new OverlayItem(point, "Titel", "Snippet");
					itemizedOverlay.addOverlay(overlayitem);
				}
			}
			// Overlay zur Karte hinzuf�gen.
			mapOverlays.add(0, itemizedOverlay);
		}

	}
}
