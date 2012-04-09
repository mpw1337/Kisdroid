package de.mpw.kisdroid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import de.mpw.kisdroid.helper.Netzwerk;
import de.mpw.kisdroid.protocols.Battery;
import de.mpw.kisdroid.protocols.Bssid;
import de.mpw.kisdroid.protocols.GPS;
import de.mpw.kisdroid.protocols.Info;
import de.mpw.kisdroid.protocols.Ssid;
import de.mpw.kisdroid.protocols.TimeP;

public class KismetMsgHandler {
	private Context ctx;
	public static final String ACTION_GPS = "de.mpw.kisdroid.intent.action.GPS";
	public static final String ACTION_GPS_NETWORK = "de.mpw.kisdroid.intent.action.GPS_NETWORK";
	public static final String ACTION_SSID = "de.mpw.kisdroid.intent.action.SSID";
	public static final String ACTION_BSSID = "de.mpw.kisdroid.intent.action.BSSID";
	public static final String ACTION_BATTERY = "de.mpw.kisdroid.intent.action.BATTERY";
	public static final String ACTION_TIME = "de.mpw.kisdroid.intent.action.TIME";

	private Set<Info> status = new HashSet<Info>();
	private Hashtable<String, Netzwerk> netzwerke = new Hashtable<String, Netzwerk>();
	private GPS gps;
	private Battery battery;
	private TimeP time;
	private long mPeriod = 4000L;
	private Timer mTimer;
	private TimerTask mTimerTask = new TimerTask() {

		@Override
		public void run() {
			sendNetzwerkBroadcast();
		}

	};
	private SharedPreferences mPref;

	public KismetMsgHandler(Context context) {
		this.ctx = context;
		mPref = ctx.getSharedPreferences(ctx.getPackageName() + "_preferences",
				Context.MODE_PRIVATE);
		mPeriod = Integer.decode(mPref.getString(Einstellungen.KEY_NETZWERKAKTUALISIERUNGSRATE,
				"2000"));
		mTimer = new Timer();
		mTimer.scheduleAtFixedRate(mTimerTask, 0, mPeriod);
	}

	public void onDestroy() {
		mTimer.cancel();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * @author Markus
	 * @param msg Nachricht vom KismetClient, die geparst werden soll
	 */

	public void parse(String msg) {
		/*
		 * Wenn die Nachricht vom Typ BSSID war wird sie zur Liste der Netzwerke
		 * hinzugefügt und wenn schon vorhanden überschrieben
		 */

		if (msg.startsWith(Bssid.IDENTIFIER)) {
			Bssid tbssid = new Bssid(msg);
			if (netzwerke.containsKey(tbssid.getMac())) {
				Netzwerk tn = netzwerke.get(tbssid.getMac());
				tn.addBssid(tbssid);
				netzwerke.put(tn.mac, tn);
			} else {
				netzwerke.put(tbssid.getMac(), new Netzwerk(tbssid));
			}
			Log.d("BSSID", String.valueOf(netzwerke.size()));

		}
		/*
		 * Wenn die Nachricht vom Typ SSID war wird sie zur Liste der Netzwerke
		 * hinzugefügt und wenn schon vorhanden überschrieben.
		 */
		if (msg.startsWith(Ssid.getIdentifier())) {
			Ssid tssid = new Ssid(msg);
			if (netzwerke.containsKey(tssid.getMac())) {
				Netzwerk tn = netzwerke.get(tssid.getMac());
				tn.addSsid(tssid);
				netzwerke.put(tn.mac, tn);
			} else {
				netzwerke.put(tssid.getMac(), new Netzwerk(tssid));
			}
			Log.d("SSID", String.valueOf(netzwerke.size()));

		}
		/*
		 * Wenn die Nachricht vom Typ Info ist, wird sie den Informationen
		 * hinzugefügt
		 */
		if (msg.startsWith(Info.IDENTIFIER)) {
			status.add(new Info(msg));
			Log.d("info", msg);
		}
		/*
		 * Wenn die Nachricht vom Typ GPS ist, wird der GPS status aktualisiert
		 */
		if (msg.startsWith(GPS.IDENTIFIER)) {
			gps = new GPS(msg);
			Intent gps_intent = new Intent(ACTION_GPS);
			gps_intent.putExtra(GPS.EXTRA_LAT, gps.getLat());
			gps_intent.putExtra(GPS.EXTRA_LON, gps.getLon());
			ctx.sendBroadcast(gps_intent);
			Log.d("GPS", gps.toString());
		}
		/*
		 * Wenn die Nachricht vom Typ Battery ist, wird der Battery Status
		 * aktualisiert
		 */
		if (msg.startsWith(Battery.IDENTIFIER)) {
			battery = new Battery(msg);
			Log.d("BATTERY", battery.toString());
			Intent bat_intent = new Intent(ACTION_BATTERY);
			bat_intent.putExtra(Battery.EXTRA_PERCENTAGE, battery.getPercentage());
			ctx.sendBroadcast(bat_intent);

		}
		/*
		 * Wenn die Nachricht vom Typ Time ist, wird die Server Zeit
		 * aktualisiert
		 */
		if (msg.startsWith(TimeP.IDENTIFIER)) {
			time = new TimeP(msg);
			Log.d(TimeP.IDENTIFIER, time.getTime());
			Intent time_intent = new Intent(ACTION_TIME);
			time_intent.putExtra(TimeP.EXTRA_TIME, time.getTime());
			ctx.sendBroadcast(time_intent);
		}

	}
	
	/**
	 * @Description Methode, die alle Informationen zu den Netzwerken versendet
	 */

	private void sendNetzwerkBroadcast() {

		Intent intent = new Intent(ACTION_SSID);
		Intent intentgps = new Intent(ACTION_GPS_NETWORK);
		String[] lat = new String[netzwerke.size()];
		String[] lon = new String[netzwerke.size()];
		String[] ssid = new String[netzwerke.size()];
		String[] strength = new String[netzwerke.size()];
		String[] mac = new String[netzwerke.size()];
		String[] encryption = new String[netzwerke.size()];
		String[] channel = new String[netzwerke.size()];
		Bssid tbssid;

		int i = 0;
		Collection<Netzwerk> daten = netzwerke.values();
		for (Iterator<Netzwerk> iterator = daten.iterator(); iterator.hasNext();) {
			Netzwerk netzwerk = (Netzwerk) iterator.next();
			if ((netzwerk.getSsid() != null) && (netzwerk.getBssid() != null)) {
				tbssid = netzwerk.getBssid();
				ssid[i] = netzwerk.getSsid().getSsid();
				mac[i] = netzwerk.mac;
				lat[i] = tbssid.getBestGps().getLat();
				lon[i] = tbssid.getBestGps().getLon();
				strength[i] = tbssid.getSignalDbm().getSignal();
				encryption[i] = tbssid.getEncryption();
				channel[i] = tbssid.getChannel();
			}
			i++;

		}
		intentgps.putExtra(GPS.EXTRA_LAT_ARRAY, lat);
		intentgps.putExtra(GPS.EXTRA_LON_ARRAY, lon);
		intent.putExtra(Ssid.EXTRA, ssid);
		intent.putExtra(Ssid.EXTRA_MAXSTRENGTH, strength);
		intent.putExtra(Ssid.EXTRA_MAC, mac);
		intent.putExtra(Bssid.EXTRA_ENCRYPTION, encryption);
		intent.putExtra(Bssid.EXTRA_CHANNEL, channel);
		// intent.putExtra("OBJECT", object);
		ctx.sendBroadcast(intentgps);
		ctx.sendBroadcast(intent);

	}

}
