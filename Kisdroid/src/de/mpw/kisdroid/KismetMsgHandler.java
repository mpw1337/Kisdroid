package de.mpw.kisdroid;

import java.util.HashSet;
import java.util.Set;

import de.mpw.kisdroid.protocols.Ssid;

import android.content.Context;

public class KismetMsgHandler {
	private Context ctx;
	
	private Set<Ssid> ssid = new HashSet<Ssid>();
	
	public KismetMsgHandler(Context context){
		this.ctx = context;
		
	}
	
	public void parse(String msg){
		if(msg.startsWith(Ssid.getIdentifier())){
			ssid.add(new Ssid(msg));
		}
	}

}
