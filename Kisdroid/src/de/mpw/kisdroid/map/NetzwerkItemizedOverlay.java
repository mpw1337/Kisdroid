/**
 * 
 */
package de.mpw.kisdroid.map;

import java.util.ArrayList;

import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

/**
 * @author Markus
 * Angepasste version eines ItemizedOverlays
 * 
 *  {@link http://de.wikibooks.org/wiki/Googles_Android/_MapActivity}
 */
public class NetzwerkItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();

	/**
	 * @param drawable Standard Drawable für Marker
	 */
	public NetzwerkItemizedOverlay(Drawable drawable) {
		super(boundCenterBottom(drawable));
	}
	/**
	 * 
	 * @param overlay Overlay, was hinzugefügt werden soll.
	 */
	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}
	/**
	 * 
	 * @param overlay Overlay, was hinzugefügt werden soll.
	 * @param drawable Spezielles Drawable für den Marker.
	 */
	
	public void addOverlay(OverlayItem overlay,Drawable drawable){
		overlay.setMarker(drawable);
		mOverlays.add(overlay);
		populate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.ItemizedOverlay#createItem(int)
	 */
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.android.maps.ItemizedOverlay#size()
	 */
	@Override
	public int size() {
		return mOverlays.size();
	}

}
