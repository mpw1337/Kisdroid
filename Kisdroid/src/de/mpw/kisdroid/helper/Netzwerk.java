/**
 * 
 */
package de.mpw.kisdroid.helper;

import de.mpw.kisdroid.protocols.Bssid;
import de.mpw.kisdroid.protocols.Ssid;

/**
 * @author Markus Helper
 * @Description Verwaltet die zwei Objekte vom Typ SSID und BSSID, die das selbe
 *              Netzwerk beschreiben.
 */
public class Netzwerk {

	private Ssid mSsid;
	private Bssid mBssid;
	public String mac;

	/**
	 * @param ssid
	 *            SSID Object
	 */
	public Netzwerk(Ssid ssid) {
		this.mSsid = ssid;
		this.mac = mSsid.getMac();
	}

	/**
	 * 
	 * @param bssid
	 *            BSSID Objekt als Konstruktor
	 */
	public Netzwerk(Bssid bssid) {
		this.mBssid = bssid;
		this.mac = mBssid.getMac();
	}

	/**
	 * @param ssid
	 *            Fügt dem Netzwerk ein ssid Objekt hinzu, oder aktualisiert es.
	 *            Die Funktioniert nur, wenn die es die selbe Mac Adresse
	 *            besitzt.
	 * @return Wenn die Mac Adresse des Ssid Objekts zum Netzwerk Objekt gepasst
	 *         hat, wird true zurückgeliefert.
	 */
	public boolean addSsid(Ssid ssid) {
		if (mac.equals(ssid.getMac())) {
			mSsid = ssid;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param bssid
	 *            Fügt dem Netzwerk ein Bssid Objekt hinzu, oder aktualisiert
	 *            es. Die Funktioniert nur, wenn die es die selbe Mac Adresse
	 *            besitzt.
	 * @return Wenn die Mac Adresse des Bssid Objekts zum Netzwerk Objekt
	 *         gepasst hat, wird true zurückgeliefert.
	 */
	public boolean addBssid(Bssid bssid) {
		if (mac.equals(bssid.getMac())) {
			mBssid = bssid;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return Liefert das Ssid Objekt zurück.
	 */
	public Ssid getSsid() {
		return mSsid;
	}

	/**
	 * @return Liefert das Bssid Object zurück.
	 */
	public Bssid getBssid() {
		return mBssid;
	}

}
