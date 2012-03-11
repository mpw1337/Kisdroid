package de.mpw.kisdroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class KismetService extends Service {
	
	private KismetBinder mBinder;
	private String TAG = "KISMET Service";
	private KismetClient client;
	private String SERVER = "192.168.2.11";
	private int PORT = 2501;
	
	private NotificationManager nManager;
	private String NTitel = "Kisdroid Service";
	private String NDetail = "Kisdroid Service läuft";
	int icon = R.drawable.ic_launcher;
	private Notification nBenachrichtigung;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "OnCreate() aufgerufgen");
		client = new KismetClient(SERVER, PORT,this.getApplicationContext());
		
		nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		nBenachrichtigung = new Notification(icon, NDetail, System.currentTimeMillis());
		Context context = getApplicationContext();
		Intent intent = new Intent(context, KisdroidActivity.class);
		PendingIntent Pendingintent = PendingIntent.getActivity(context, 0, intent, 0);
		nBenachrichtigung.setLatestEventInfo(context, NTitel, NDetail, Pendingintent);
		nManager.notify(1, nBenachrichtigung);
	}
	@Override
	public void onDestroy() {
		Log.d(TAG, "OnDestroy() aufgerufgen");
		client.stopClient();
		nManager.cancel(1);
		super.onDestroy();
	}
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		client.start();
		if(client.Fehler !=""){
			Toast fehler = Toast.makeText(this,getResources().getString(R.string.toast_fehler)+client.Fehler, Toast.LENGTH_SHORT);
			fehler.show();
		}			
		Log.d(TAG, "OnStart() aufgerufgen" + intent.getPackage() + "Mit der StartID:"+startId);
	}

}
