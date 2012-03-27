package de.mpw.kisdroid.helper;

/**
 * @author Markus
 * 
 */
public class Ip {

	Integer[] mAdresse = new Integer[4];

	/**
	 * Dem Construktor wird ein String der Form "144.123.0.3" übergeben.
	 */
	public Ip(String adresse) {
		String[] mTemp = adresse.split(".");
		// String in die einzelnen Teile zerlegen
		for (int i = 0; i < mTemp.length; i++) {
			try {
				mAdresse[i] = Integer.valueOf(mTemp[i]);
			} catch (NumberFormatException e) {
				mAdresse[i] = 0;
			}
		}
	}

	public Integer[] getIP() {
		return mAdresse;
	}

	public String toString() {
		String mTemp = "";
		for (Integer teil : mAdresse) {
			mTemp = mTemp + ":" + teil.toString();
		}
		return mTemp.substring(1);
	}
}
