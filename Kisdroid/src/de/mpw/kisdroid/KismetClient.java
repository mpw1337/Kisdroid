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
	
	private static String TAG = "KISMETCLIENT";
	private String mServer;
	
	private InetAddress adress;
	
	int mPort;
	public Boolean connected;
	Handler msgHandler;
	Socket mSocket;
	BufferedReader in;
	PrintWriter out;
	public String Fehler = "";
	volatile boolean running = true;
	
	
	public KismetClient(String server, int port){
		this.mPort = port;
		this.mServer = server;
		this.connected = false;				
	}
	
	public void stopClient(){
		if (connected) {
			running = false;
			connected = false;
			try {
				mSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}

	public void start(){
		
		this.Fehler = "";
		try {
			adress = InetAddress.getByName(mServer);
			mSocket = new Socket(adress,mPort);
			out = new PrintWriter(mSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			out.println("!1 ENABLE STATUS *");
			out.println("!2 ENABLE SSID *");
			out.println("!3 ENABLE TIME");
			this.connected = true;
			this.running = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.d(TAG, e.getMessage());
			this.connected = false;
			this.Fehler = e.getMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			Log.d(TAG, e.getMessage());
			this.Fehler = e.getMessage();
			this.connected = false;
		}
		super.start();
	
	}

	@Override
	public void run() {
		super.run();
		while(running){
			
			try {
				String fromServer = "";
				while ((fromServer  = in.readLine()) != null && running) {
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
