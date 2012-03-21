package de.mpw.kisdroid.protocols;

public class Battery implements Protocols {
	public static final String IDENTIFIER = "*BATTERY";
	public static String EXTRA_PERCENTAGE = "BATTERY_PERCENTAGE";
	public static String CAPABILITY = "!%n ENABLE BATTERY percentage,charging,ac,remaining";
	private String raw;

	private String percentage;
	private String charging;
	private String ac;
	private String remaining;

	public Battery(String string) {
		this.raw = string;
		String[] mArray;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ");
		this.percentage = mArray[0];
		this.charging = mArray[1];
		this.ac = mArray[2];
		this.remaining = mArray[3];
	}

	public String getRaw() {
		return raw;
	}
	
	public Integer getPercentage(){
		Integer i;
		i = Integer.decode(percentage);
		return i;
	}

	public String toString() {
		return "Percentage: " + percentage + "% Charging:" + charging + "AC:" + ac
				+ " Verbleibend:" + remaining;
	}

}
