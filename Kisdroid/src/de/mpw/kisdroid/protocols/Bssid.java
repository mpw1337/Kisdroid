package de.mpw.kisdroid.protocols;

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

	public static final String CAPABILITY = "BSSID bssid,type,llcpackets,datapackets,cryptpackets,manuf,channel,"
			+ "firsttime,lasttime,atype,rangeip,netmaskip,gatewayip,"
			+ "gpsfixed,minlat,minlon,minalt,minspd,"
			+ "maxlat,maxlon,maxalt,maxspd,"
			+ "signal_dbm,noise_dbm,minsignal_dbm,minnoise_dbm,"
			+ "maxsignal_dbm,maxnoise_dbm,signal_rssi,noise_rssi,"
			+ "minsignal_rssi,minnoise_rssi,maxsignal_rssi, maxnoise_rssi,"
			+ "bestlat,bestlon,bestalt,agglat,agglon,aggalt,aggpoints,"
			+ "datasize,turbocellnid,turbocellmode,turbocellsat,carrierset,"
			+ "maxseenrate,encodingset,decrypted,dupeivpackets,bsstimestamp,"			
			+ "cdpdevice,cdpport,fragments,retries,newpackets,freqmhz,datacryptset";

	private GPS mMinGps;
	private GPS mMaxGps;
	private GPS mBestGps;
	private String raw;

	public String getRaw() {
		return raw;
	}

}
