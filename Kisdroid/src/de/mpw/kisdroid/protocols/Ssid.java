package de.mpw.kisdroid.protocols;


public class Ssid implements Protocols {

	private String raw;
	private String[] mArray;
	private String mSSID;
	private String mMAC;
	private static final String identifier = "*SSID";
	public static String EXTRA = "SSIDA";

	public Ssid(String string) {
		this.raw = string;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ", 3);
		mMAC = mArray[0];
		if (mArray.length < 2) {
			mSSID = "<no ssid>";
		} else {
			mSSID = mArray[1].trim();
		}
	}

	public String getRaw() {
		// TODO Auto-generated method stub
		return raw;
	}

	public String getSsid() {
		return mSSID;
	}

	public String getMac() {
		return mMAC;
	}

	public static String getIdentifier() {
		// TODO Auto-generated method stub
		return identifier;
	}

}
