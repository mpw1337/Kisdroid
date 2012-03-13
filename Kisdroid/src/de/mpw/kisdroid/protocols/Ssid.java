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
		mArray = raw.split(":");
		mMAC = mArray[0];
		mSSID = mArray[1];
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
