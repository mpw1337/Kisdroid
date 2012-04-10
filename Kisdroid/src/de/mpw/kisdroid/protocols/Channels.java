package de.mpw.kisdroid.protocols;

import de.mpw.kisdroid.helper.Signal;

/**
 * Klasse für das Channel Protokoll Capabilitys:
 * channel,time_on,packets,packetsdelta
 * ,usecused,bytes,bytesdelta,networks,maxsignal_dbm
 * ,maxsignal_rssi,maxnoise_dbm,maxnoise_rssi,activenetworks
 * Data:
 *  *CHANNEL: 1 200000 6534 2 0 9190 0 8 -81 0 0 0 1
 * @author Markus
 * 
 */
public class Channels implements Protocols {
	
	public static final String IDENTIFIER = "*CHANNEL";
	private String raw;
	private Integer channel;
	private Integer time_on;
	private Integer packets;
	private Integer packetsdelta;
	private Integer unscused;
	private Integer bytes;
	private Integer bytesdelta;
	private Integer networks;
	private Signal maxsignal_dbm;
	private Signal maxsignal_rssi;
	private Integer activenetworks;

	public Channels(String string) {
		this.raw = string;
		String[] mArray;
		String[] temp = raw.split(":", 2);
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ");
		try {
			this.channel = Integer.decode(mArray[0]);
			this.time_on = Integer.decode(mArray[1]);
			this.packets = Integer.decode(mArray[2]);
			this.packetsdelta = Integer.decode(mArray[3]);
			this.unscused = Integer.decode(mArray[4]);
			this.bytes = Integer.decode(mArray[5]);
			this.bytesdelta = Integer.decode(mArray[6]);
			this.networks = Integer.decode(mArray[7]);
			this.maxsignal_dbm = new Signal(mArray[8], mArray[10]);
			this.maxsignal_rssi = new Signal(mArray[9],mArray[11]);
			this.activenetworks = Integer.decode(mArray[12]);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getRaw() {
		// TODO Auto-generated method stub
		return null;
	}

}
