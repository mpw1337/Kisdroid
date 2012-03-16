package de.mpw.kisdroid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import de.mpw.kisdroid.protocols.Info;
import de.mpw.kisdroid.protocols.Ssid;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

public class KismetClient extends Thread {

	private static String TAG = "KISMETCLIENT";
	public static final String ACTION_SSID = "de.mpw.kisdroid.intent.action.SSID";

	// private static final String CAPABILITY_SSID =
	// "!2 ENABLE SSID mac,firsttime,lasttime,maxrate,ssid";

	private static final String CAPABILITY_STATUS = "!1 ENABLE STATUS *";
	// private static final String SENTENCE_SSID = "*SSID";
	// private static final String EXTRA_SSID = "SSID";
	private String mServer;

	private Context ctx;
	private KismetMsgHandler kmh;

	private InetAddress adress;

	int mPort;
	public Boolean connected;

	Handler msgHandler;
	Socket mSocket;
	BufferedReader in;
	PrintWriter out;
	public String Fehler = "";
	volatile boolean running = true;

	// Creator der KismetClient Klasse
	public KismetClient(String server, int port, Context context) {
		this.mPort = port;
		this.mServer = server;
		this.connected = false;
		this.ctx = context;
		kmh = new KismetMsgHandler(ctx);
	}

	public void stopClient() {
		// Wenn der Client verbunden war wird running und connected auf false
		// gesetzt
		// dann wird versucht den Socket zu schlieﬂen
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

	// Klient starten
	public void start() {
		// Fehler auf leer setzten
		this.Fehler = "";
		try {
			// Adresse des Servers setzen
			adress = InetAddress.getByName(mServer);
			// neuen Socket mit dem Port und der Adresse erstellen
			mSocket = new Socket(adress, mPort);
			// out und in Stream registrieren
			out = new PrintWriter(mSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			// Status Aktivieren
			out.println(CAPABILITY_STATUS);
			// SSID ausgabe aktivieren
			// out.println(CAPABILITY_SSID);
			out.println(Ssid.CAPABILITY.replace("%n", "2"));
			// Info aktivieren
			out.println(Info.CAPABILITY.replace("%n", "3"));

			// out.println("!3 ENABLE TIME");
			// Verbund und running auf true setzten
			this.connected = true;
			this.running = true;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Log.d(TAG, e.getMessage());
			this.connected = false;
			// this.Fehler = e.getMessage();
			this.Fehler = e.getLocalizedMessage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			Log.d(TAG, e.getMessage());
			this.Fehler = e.getMessage();
			this.connected = false;
		}
		if (connected && running) {
			super.start();
		}

	}

	@Override
	public void run() {
		super.run();
		// Solange der Client l‰uft soll vom Server gelesen werden
		while (running) {

			try {
				String fromServer = "";
				while ((fromServer = in.readLine()) != null && running) {
					// fromServer = in.readLine();
					System.out.println(fromServer);
					kmh.parse(fromServer);

					// Wenn ein *SSID Sentece kommt, soll ein Broadcast gesendet
					// werden
					/*
					 * if (fromServer.startsWith(SENTENCE_SSID)) { Intent intent
					 * = new Intent(ACTION_SSID); intent.putExtra(EXTRA_SSID,
					 * fromServer); ctx.sendBroadcast(intent); }
					 */
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
