package de.mpw.kisdroid.protocols;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeP implements Protocols {

	public static final String IDENTIFIER = "*TIME";
	public static String EXTRA_TIME = "TIME";
	public static String CAPABILITY = "!%n ENABLE BATTERY percentage,charging,ac,remaining";
	public static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
	private String raw;

	private Integer time;

	public TimeP(String string) {
		this.raw = string;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		time = Integer.parseInt(temp[1]);
	}

	public String getTime() {
		Date d = new Date(time.longValue() * 1000);
		return sdf.format(d);
		// return time.toString();
	}

	public String getRaw() {
		return raw;
	}

}
