package de.mpw.kisdroid.protocols;

public class Info implements Protocols {
	
	private String raw;
	//private String[] mArray;
	public static final String IDENTIFIER = "*INFO";
	public static String EXTRA = "INFO";
	public static String CAPABILITY = "!%n ENABLE INFO *";

	public Info(String string){
		this.raw = string;
	}
	public String getRaw() {
		return raw;
	}	

}
