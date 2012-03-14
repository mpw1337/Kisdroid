package de.mpw.kisdroid.protocols;

public class Ssid implements Protocols {

	private String raw;
	private String[] mArray;
	private String mSSID;
	private String mMAC;
	private String mFirstTime;
	private String mLastTime;
	private String mMaxRate;
	private static final String identifier = "*SSID";
	public static String CAPABILITY = "!%n ENABLE SSID mac,firsttime,lasttime,maxrate,ssid";
	public static String EXTRA = "SSIDA";

	public Ssid(String string) {
		this.raw = string;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ", 5);
		mMAC = mArray[0];
		mFirstTime = mArray[1].trim();
		mLastTime = mArray[2].trim();
		mMaxRate = mArray[3].trim();
		if (mArray.length < 5) {
			mSSID = "<no ssid>";
		} else {
			mSSID = mArray[4].trim();
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

	public String getLastTime() {
		return mLastTime;
	}

	public String getFirstTime() {
		return mFirstTime;
	}

	public String getMaxRate() {
		return mMaxRate;
	}

	public static String getIdentifier() {
		// TODO Auto-generated method stub
		return identifier;
	}

}
