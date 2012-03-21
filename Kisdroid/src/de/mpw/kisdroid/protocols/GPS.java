package de.mpw.kisdroid.protocols;


public class GPS implements Protocols {
	public static final String IDENTIFIER = "*GPS";
	public static String EXTRA = "GPS";
	public static String CAPABILITY = "!%n ENABLE GPS lat,lon,alt,spd,fix,connected,hdop,vdop";
	// GPS lat,lon,alt,spd,heading,fix,satinfo,hdop,vdop,connected
	private String raw;

	private String lat; // Latitude Bsp 34.30
	private String lon; // Longitude Bsp 8.203
	private String altitude; // Hoehe mit . Bsp: 145.4
	private String spd; // Speed
	private String heading; // Ausrichtung in Grad
	private String fix; // 0 = kein Fix 3 = 3D Fix
	// private String satinfo;
	private String hdop; // Horizontale genauigkeit
	private String vdop; // Vertikale Genauigkeit
	private String connected; // 0 = nein 1 = ja

	public GPS(String string) {
		this.raw = string;
		String[] mArray;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ");
		this.lat = mArray[0];
		this.lon = mArray[1];
		this.altitude = mArray[2];
		this.spd = mArray[3];
		this.fix = mArray[4];
		this.connected = mArray[5];
		this.hdop = mArray[6];
		this.vdop = mArray[7];
	}

	public String getRaw() {
		// TODO Auto-generated method stub
		return raw;
	}

	public String getPos() {
		// GeoPoint gp = new GeoPoint(Integer.parseInt(lat),
		// Integer.parseInt(lon));
		// return gp;
		return "Lat:" + lat + "Lon" + lon;
	}

	public String getFix() {
		return fix;

	}

	public String toString() {
		return "Lat:" + lat + "Lon:" + lon + "Höhe:" + altitude + "Geschwindigkeit:" + spd + "Fix:"
				+ fix + "Verbunden:" + connected + "Horizontale Gen:" + hdop + "Vertikale Gen:"
				+ vdop;
	}
}
