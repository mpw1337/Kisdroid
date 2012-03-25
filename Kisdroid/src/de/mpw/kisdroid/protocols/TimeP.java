package de.mpw.kisdroid.protocols;


public class TimeP implements Protocols {
	
	public static final String IDENTIFIER = "*TIME";
	public static String EXTRA_TIME = "TIME";
	public static String CAPABILITY = "!%n ENABLE BATTERY percentage,charging,ac,remaining";
	private String raw;
	
	private Integer time;


	public TimeP(String string){
		this.raw = string;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		time = Integer.parseInt(temp[1]);
	}
	
	public String getTime(){
		return time.toString();
	}
	public String getRaw() {
		return raw;
	}

}
