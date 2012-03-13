package de.mpw.kisdroid;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.mpw.kisdroid.protocols.Ssid;

import android.content.Context;
import android.content.Intent;

public class KismetMsgHandler {
	private Context ctx;
	public static final String ACTION_SSID = "de.mpw.kisdroid.intent.action.SSID";
	private Set<Ssid> ssid = new HashSet<Ssid>();

	public KismetMsgHandler(Context context) {
		this.ctx = context;
		

	}

	public void parse(String msg) {
		if (msg.startsWith(Ssid.getIdentifier())) {
			ssid.add(new Ssid(msg));
			Intent intent = new Intent(ACTION_SSID);
			String[] temp = new String[ssid.size()];
			int i = 0;
			for (Iterator<Ssid> iterator = ssid.iterator(); iterator.hasNext();) {
				
				Ssid type = (Ssid) iterator.next();
				temp[i] = type.getSsid();
				i++;
			}
			intent.putExtra(Ssid.EXTRA, temp);
			
			ctx.sendBroadcast(intent);
		}
	}

	public Set<Ssid> getssid() {
		return ssid;
	}

}
