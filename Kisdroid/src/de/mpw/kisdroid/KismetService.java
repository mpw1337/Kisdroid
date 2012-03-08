package de.mpw.kisdroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class KismetService extends Service {
	
	private KismetBinder mBinder;
	private String TAG = "KISMET Service";
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "OnCreate() aufgerufgen");
	}
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "OnDestroy() aufgerufgen");
	}
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Log.d(TAG, "OnStart() aufgerufgen" + intent.getPackage() + "Mit der StartID:"+startId);
	}

}
