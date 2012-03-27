package de.mpw.kisdroid.protocols;

import de.mpw.kisdroid.helper.Ip;
import de.mpw.kisdroid.helper.Signal;

/*
 * bssid,type,llcpackets,datapackets,cryptpackets,manuf,channel,firsttime,lasttime,atype,rangeip,netmaskip,gatewayip,gpsfixed,minlat,minlon,minalt,minspd,maxlat,maxlon,maxalt,maxspd,signal_dbm,noise_dbm,minsignal_dbm,minnoise_dbm,maxsignal_dbm,maxnoise_dbm,signal_rssi,noise_rssi,minsignal_rssi,minnoise_rssi,maxsignal_rssi,maxnoise_rssi,bestlat,bestlon,bestalt,agglat,agglon,aggalt,aggpoints,datasize,turbocellnid,turbocellmode,turbocellsat,carrierset,maxseenrate,encodingset,decrypted,dupeivpackets,bsstimestamp,cdpdevice,cdpport,fragments,retries,newpackets,freqmhz,datacryptset
 */
/*CAPABILITY: BSSID 
 * bssid,type,llcpackets,datapackets,cryptpackets,
 * manuf,channel,
 * firsttime,lasttime,atype,rangeip,
 * netmaskip,gatewayip,
 * gpsfixed,minlat,minlon,minalt,
 * minspd,maxlat,maxlon,maxalt,maxspd,
 * signal_dbm,noise_dbm, 
 * minsignal_dbm,minnoise_dbm,
 * maxsignal_dbm,maxnoise_dbm,
 * signal_rssi,noise_rssi,
 * minsignal_rssi,minnoise_rssi,
 * maxsignal_rssi, maxnoise_rssi,
 * bestlat,bestlon,bestalt,
 * agglat,agglon,aggalt,
 * aggpoints,datasize,turbocellnid,turbocellmode,turbocellsat,
 * carrierset,maxseenrate,encodingset,decrypted,dupeivpackets,bsstimestamp,
 * cdpdevice,cdpport,fragments,retries,newpackets,freqmhz,datacryptset
 * 
 * 
 * *BSSID: 00:1A:2A:32:18:18 ->Bssid
 *  0 -> Type
 *  1234 ->llcpackets
 *  7361 ->datapackets
 *  3136 ->cryptpackets
 *  Arcadyan -> Manufactor
 *  11 ->Channel
 *  1332327886 -> Firsttime
 *  1332433736 ->Lasttime
 *  0 -> Atype ? 
 *  0.0.0.0 -> Range Ip
 *  0.0.0.0 -> Netmask Ip
 *  0.0.0.0 -> Gateway 
 *  0 -> GPS Fixes
 *  90 -> Minlatitude
 *  180 -> MinLong 
 *  -100000 -> Min Altitude
 *  -100000 -> Min Speed 
 *  -90 -> Max Latitude
 *  -180 -> Max Longitude
 *  100000 -> Max Altitude
 *  100000 -> Max Speed
 *  -51 -> Signal DBM
 *  0 -> Noise DBM
 *  -79 -> Minsignal DBM
 *  0 -> Minnoise DBM
 *  -23 ->Max Signal DBM
 *  -256 -> MaxNoise DBM
 *  1024 -> BestLatitude
 *  1024 -> Best Longitude 
 *  1024 -> Best Altitude
 *  1024 -> Average Latutude 
 *  0 -> Average Longitude 
 *  0 0 0 -100000 0 
 *  0 -> Aggpoints 
 *  0 -> Datasize 
 *  0 -> Turbocellnid 
 *  323598 -> Turbocellmode 
 *  0 -> Turbocellsat 
 *  0 -> Carrierset 
 *  0 -> Maxseenrate 
 *  5 -> Encoding Set 
 *  240 -> Decrypted 
 *  3 -> Dupeivpackets 
 *  0 -> Bsstimestamp 
 *  0 -> CDP Device 
 *  0 -> CDP Port    
 *  0 -> Fragments
 *  0 -> Retries
 *  1 -> New Packets
 *  2442:8*2447:3*2452:13*2457:575*2462:7676*2467:286*2472:11*2484:23* - freqmhz
 *  0 -> Data Cryptset
 */
public class Bssid implements Protocols {

	public static final String IDENTIFIER = "*BSSID";
	public static final String CAPABILITY = "!%n ENABLE BSSID bssid,type,llcpackets,datapackets,cryptpackets,manuf,channel,"
			+ "firsttime,lasttime,atype,rangeip,netmaskip,gatewayip,"
			+ "gpsfixed,minlat,minlon,minalt,minspd,"
			+ "maxlat,maxlon,maxalt,maxspd,"
			+ "signal_dbm,noise_dbm,minsignal_dbm,minnoise_dbm,"
			+ "maxsignal_dbm,maxnoise_dbm,signal_rssi,noise_rssi,"
			+ "minsignal_rssi,minnoise_rssi,maxsignal_rssi,maxnoise_rssi,"
			+ "bestlat,bestlon,bestalt,agglat,agglon,aggalt,aggpoints,"
			+ "datasize,"
			+ "maxseenrate,encodingset,decrypted,bsstimestamp,"
			+ "fragments,retries,newpackets,freqmhz,datacryptset";
	// public static final String CAPABILITY =
	// "!%n ENABLE BSSID bssid,type,llcpackets,datapackets,cryptpackets,manuf,channel,"
	// + "firsttime,lasttime,atype,rangeip,netmaskip,gatewayip,"
	// + "gpsfixed,minlat,minlon,minalt,minspd,"
	// + "maxlat,maxlon,maxalt,maxspd,"
	// + "signal_dbm,noise_dbm,minsignal_dbm,minnoise_dbm,"
	// + "maxsignal_dbm,maxnoise_dbm,signal_rssi,noise_rssi,"
	// + "minsignal_rssi,minnoise_rssi,maxsignal_rssi,maxnoise_rssi,"
	// + "bestlat,bestlon,bestalt,agglat,agglon,aggalt,aggpoints,"
	// + "datasize,turbocellnid,turbocellmode,turbocellsat,carrierset,"
	// + "maxseenrate,encodingset,decrypted,dupeivpackets,bsstimestamp,"
	// + "cdpdevice,cdpport,fragments,retries,newpackets,freqmhz,datacryptset";

	private GPS mMinGps;
	private GPS mMaxGps;
	private GPS mBestGps;
	private Signal mSignalDbm;
	private String raw;
	private String mMac;
	private String encryption;
	private Integer type;
	private Integer llcpackets;
	private Integer datapackets;
	private Integer cryptpackets;
	private String manufactor;
	private Integer channel;
	private Integer firsttime;
	private Integer lasttime;
	private Integer atype;
	private Ip rangeip;
	private Ip netmaskip;
	private Ip gatewayip;
	private Integer gpsfixed;
	private int datasize;
	private Integer bsstimestamp;

	public Bssid(String string) {
		this.raw = string;
		String[] mArray;
		String[] temp = raw.split(":", 2);
		// mArray = temp[1].indexOf("\x01", start)
		temp[1] = temp[1].trim();
		mArray = temp[1].split(" ");
		this.mMac = mArray[0];
		this.type = Integer.parseInt(mArray[1]);
		this.manufactor = mArray[5];
		this.channel = Integer.decode(mArray[6]);
		this.rangeip = new Ip(mArray[10]);
		this.netmaskip = new Ip(mArray[11]);
		this.gatewayip = new Ip(mArray[12]);
		this.gpsfixed = Integer.decode(mArray[13]);
		this.mMinGps = new GPS(mArray[14], mArray[15], mArray[16], mArray[17], mArray[13]);
		this.mMaxGps = new GPS(mArray[18], mArray[19], mArray[20], mArray[21], mArray[13]);
		this.mBestGps = new GPS(mArray[34], mArray[35], mArray[36]);
		this.mSignalDbm = new Signal(mArray[22], mArray[23]);
		setEncodingset(mArray[43]);

	}

	private void setEncodingset(String string) {
		if (string.equals("3")) {
			this.encryption = "WPA2";
		} else {
			if (string.equals("2")) {
				this.encryption = "WPA";
			} else {
				this.encryption = "none/WEP";
			}
		}
	}

	public String getRaw() {
		return raw;
	}

	public String getMac() {
		return mMac;
	}

	public String getManufactor() {
		return manufactor;
	}

	public String getSignalDbm() {
		return mSignalDbm.getSignal();
	}

	public String getEncryption() {
		return encryption;
	}

	public String toString() {
		return "Mac: " + mMac + " Typ: " + type + "Channel: " + channel + "Min GPS:"
				+ mMinGps.toString() + "RangeIP: " + rangeip.toString() + " Netmask: "
				+ netmaskip.toString() + "Gateway IP: " + gatewayip.toString();
	}

}
// !0 ENABLE BSSID
// bssid,type,llcpackets,datapackets,cryptpackets,manuf,channel,firsttime,lasttime,atype,rangeip,netmaskip,gatewayip,gpsfixed,minlat,minlon,minalt,minspd,maxlat,maxlon,maxalt,maxspd,signal_dbm,noise_dbm,minsignal_dbm,minnoise_dbm,maxsignal_dbm,maxnoise_dbm,signal_rssi,noise_rssi,minsignal_rssi,minnoise_rssi,maxsignal_rssi,
// maxnoise_rssi,bestlat,bestlon,bestalt,agglat,agglon,aggalt,aggpoints,datasize,turbocellnid,turbocellmode,turbocellsat,carrierset,maxseenrate,encodingset,decrypted,dupeivpackets,bsstimestamp,cdpdevice,cdpport,fragments,retries,newpackets,freqmhz,datacryptset

