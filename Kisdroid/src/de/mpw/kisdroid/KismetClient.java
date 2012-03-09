package de.mpw.kisdroid;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.Handler;
import android.util.Log;

public class KismetClient extends Thread {
	
	String TAG = "KISMETCLIENT";
	String mServer;
	int mPort;
	public Boolean connected;
	Handler msgHandler;
	Socket mSocket;
	BufferedReader in;
	PrintWriter out;
	volatile boolean running = true;
	
	
	public KismetClient(String server, int port){
		this.mPort = port;
		this.mServer = server;
		this.connected = false;
		InetAddress adress;
		try {
			adress = InetAddress.getByName(mServer);
			mSocket = new Socket(adress,mPort);
			out = new PrintWriter(mSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			out.println("!1 ENABLE STATUS *");
			out.println("!2 ENABLE SSID *");
			out.println("!3 ENABLE TIME");
			this.connected = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.d(TAG, e.getMessage());
			this.connected = false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.d(TAG, e.getMessage());
			this.connected = false;
		}
		
	}
	
	public void stopClient(){
		running = false;
	}
	public void start(){
	while(running){
		
		try {
			String fromServer = "";
			while ((fromServer  = in.readLine()) != null /* && connected*/) {
			//fromServer = in.readLine();
				System.out.println(fromServer);
			}
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}
	}

}
