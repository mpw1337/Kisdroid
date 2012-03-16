package de.mpw.kisdroid;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.mpw.kisdroid.protocols.Ssid;
import de.mpw.kisdroid.protocols.Info;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class KismetMsgHandler {
	private Context ctx;
	public static final String ACTION_SSID = "de.mpw.kisdroid.intent.action.SSID";
	private Set<Ssid> ssid = new HashSet<Ssid>();
	private Set<Info> status = new HashSet<Info>();

	public KismetMsgHandler(Context context) {
		this.ctx = context;
	}

	public void parse(String msg) {
		/*
		 * Wenn die Nachricht vom Typ SSID war wird sie zur Liste der Netzwerke
		 * hinzugefügt
		 */
		if (msg.startsWith(Ssid.getIdentifier())) {
			//Temporäres Ssid Object erzeugen
			Ssid tssid = new Ssid(msg);
			//doppelt auf false initialisieren
			boolean doppelt = false;
			//Durch alle schon im Array befindlichen Netzwerke gehen und vergleichen
			for (Iterator<Ssid> iterator = ssid.iterator(); iterator.hasNext();) {
				Ssid type = (Ssid) iterator.next();
				//Falls die Mac Adresse, oder die SSID gleich sind als doppelt markieren
				if ((type.getMac().equals(tssid.getMac()))
						| (type.getSsid().equals(tssid.getSsid()))) {
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
			//Intent vorbereiten um die Netzwerke zu übermitteln
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
	}

	public Set<Ssid> getssid() {
		return ssid;
	}

}
