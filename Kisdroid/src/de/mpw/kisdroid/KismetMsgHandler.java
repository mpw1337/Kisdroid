package de.mpw.kisdroid;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.mpw.kisdroid.protocols.Battery;
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
	public static final String ACTION_BATTERY = "de.mpw.kisdroid.intent.action.BATTERY";
	private Set<Ssid> ssid = new HashSet<Ssid>();
	private Set<Info> status = new HashSet<Info>();
	private GPS gps;
	private Battery battery;
	private TimeP time;

	public KismetMsgHandler(Context context) {
		this.ctx = context;
	}

	public void parse(String msg) {
		/*
		 * Wenn die Nachricht vom Typ SSID war wird sie zur Liste der Netzwerke
		 * hinzugefügt
		 */
		if (msg.startsWith(Ssid.getIdentifier())) {
			// Temporäres Ssid Object erzeugen
			Ssid tssid = new Ssid(msg);
			// doppelt auf false initialisieren
			boolean doppelt = false;
			// Durch alle schon im Array befindlichen Netzwerke gehen und
			// vergleichen
			for (Iterator<Ssid> iterator = ssid.iterator(); iterator.hasNext();) {
				Ssid type = (Ssid) iterator.next();
				// Falls die Mac Adresse, oder die SSID gleich sind als doppelt
				// markieren
				if ((type.getMac().equals(tssid.getMac()))) {
					doppelt = true;
					// Log.d("KISDROID_DOPPELTE", type.getMac() + tssid.getMac()
					// + " SSID: " + type.getSsid() + " und " +
					// tssid.getSsid());
					break;
				}

			}
			if (!doppelt) {
				ssid.add(tssid);
			}
			// Intent vorbereiten um die Netzwerke zu übermitteln
			Intent intent = new Intent(ACTION_SSID);
			String[] temp = new String[ssid.size()];
			String[] strength = new String[ssid.size()];
			String[] mac = new String[ssid.size()];

			int i = 0;
			for (Iterator<Ssid> iterator = ssid.iterator(); iterator.hasNext();) {
				Ssid type = (Ssid) iterator.next();
				temp[i] = type.getSsid();
				strength[i] = type.getMaxRate();
				mac[i] = type.getMac();
				i++;
			}
			intent.putExtra(Ssid.EXTRA, temp);
			intent.putExtra(Ssid.EXTRA_MAXSTRENGTH, strength);
			intent.putExtra(Ssid.EXTRA_MAC, mac);
			// intent.putExtra("OBJECT", object);

			ctx.sendBroadcast(intent);
		}
		if (msg.startsWith(Info.IDENTIFIER)) {
			status.add(new Info(msg));
			Log.d("info", msg);
		}
		if (msg.startsWith(GPS.IDENTIFIER)) {
			gps = new GPS(msg);
			Log.d("GPS", gps.toString());
		}

		if (msg.startsWith(Battery.IDENTIFIER)) {
			battery = new Battery(msg);
			Log.d("BATTERY", battery.toString());
			Intent bat_intent = new Intent(ACTION_BATTERY);
			bat_intent.putExtra(Battery.EXTRA_PERCENTAGE, battery.getPercentage());
			ctx.sendBroadcast(bat_intent);

		}
		if (msg.startsWith(TimeP.IDENTIFIER)) {
			time = new TimeP(msg);
			Log.d(TimeP.IDENTIFIER, time.getTime());
		}
	}

	public Set<Ssid> getssid() {
		return ssid;
	}

}
