package de.mpw.kisdroid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;

import de.mpw.kisdroid.helper.Netzwerk;
import de.mpw.kisdroid.protocols.Battery;
import de.mpw.kisdroid.protocols.Bssid;
import de.mpw.kisdroid.protocols.GPS;
import de.mpw.kisdroid.protocols.Ssid;
import de.mpw.kisdroid.protocols.Info;
import de.mpw.kisdroid.protocols.TimeP;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KismetMsgHandler {
	private Context ctx;
	public static final String ACTION_SSID = "de.mpw.kisdroid.intent.action.SSID";
	public static final String ACTION_BSSID = "de.mpw.kisdroid.intent.action.BSSID";
	public static final String ACTION_BATTERY = "de.mpw.kisdroid.intent.action.BATTERY";
	public static final String ACTION_TIME = "de.mpw.kisdroid.intent.action.TIME";
	private Set<Info> status = new HashSet<Info>();
	private Hashtable<String, Netzwerk> netzwerke = new Hashtable<String, Netzwerk>();
	private GPS gps;
	private Battery battery;
	private TimeP time;

	public KismetMsgHandler(Context context) {
		this.ctx = context;
	}

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
		
		sendNetzwerkBroadcast();
	}

	private void sendNetzwerkBroadcast() {

		Intent intent = new Intent(ACTION_SSID);
		String[] temp = new String[netzwerke.size()];
		String[] strength = new String[netzwerke.size()];
		String[] mac = new String[netzwerke.size()];
		String[] encryption = new String[netzwerke.size()];

		int i = 0;
		Collection<Netzwerk> daten = netzwerke.values();
		for (Iterator<Netzwerk> iterator = daten.iterator(); iterator.hasNext();) {
			Netzwerk netzwerk = (Netzwerk) iterator.next();
			if ((netzwerk.getSsid() != null) && (netzwerk.getBssid() != null)) {
				temp[i] = netzwerk.getSsid().getSsid();
				mac[i] = netzwerk.mac;
				strength[i] = netzwerk.getBssid().getSignalDbm();
				encryption[i] = netzwerk.getBssid().getEncryption();
			}
			i++;

		}
		intent.putExtra(Ssid.EXTRA, temp);
		intent.putExtra(Ssid.EXTRA_MAXSTRENGTH, strength);
		intent.putExtra(Ssid.EXTRA_MAC, mac);
		intent.putExtra(Bssid.EXTRA_ENCRYPTION, encryption);
		// intent.putExtra("OBJECT", object);

		ctx.sendBroadcast(intent);

	}

}
