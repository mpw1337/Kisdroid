/**
 * 
 */
package de.mpw.kisdroid.helper;

import de.mpw.kisdroid.protocols.Bssid;
import de.mpw.kisdroid.protocols.Ssid;

/**
 * @author Markus Helper 
 * @Klasse um die Netzwerke zu verwalten.
 */
public class Netzwerk {

	private Ssid mSsid;
	private Bssid mBssid;
	public String mac;

	/**
	 * 
	 */
	public Netzwerk(Ssid ssid) {
		this.mSsid = ssid;
		this.mac = mSsid.getMac();
	}

	public Netzwerk(Bssid bssid) {
		this.mBssid = bssid;
		this.mac = mBssid.getMac();
	}

}
