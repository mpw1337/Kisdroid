package de.mpw.kisdroid.protocols;

public class Status implements Protocols {
	
	private String raw;
	//private String[] mArray;
	public static final String IDENTIFIER = "*INFO";
	public static String EXTRA = "INFO";

	public Status(String string){
		this.raw = string;
	}
	public String getRaw() {
		return raw;
	}	

}
