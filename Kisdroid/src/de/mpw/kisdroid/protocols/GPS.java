package de.mpw.kisdroid.protocols;

public class GPS implements Protocols {
	public static final String IDENTIFIER = "*GPS";
	public static String EXTRA = "GPS";
	private String raw;

	private String lat;
	private String lon;
	private String alt;
	private String spd;
	private String heading;
	private String fix;
	private String satinfo;
	// private String hdop;
	// private String vdop;
	private String connected;

	public GPS(String string) {
		this.raw = string;
		String[] mArray;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ");
		this.lat = mArray[0];
		this.lon = mArray[1];
	}

	public String getRaw() {
		// TODO Auto-generated method stub
		return raw;
	}

}
