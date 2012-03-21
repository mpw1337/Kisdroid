package de.mpw.kisdroid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

public class Einstellungen extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	private static final CharSequence SERVER_KEY = "host";
	private static final CharSequence SERVER_PORT = "port";
	private static final String KEY_HOST = "host";
	private static final String KEY_PORT = "port";
	EditTextPreference mServer;
	EditTextPreference mPort;
	SharedPreferences mPref;

	@Override
	protected void onResume() {
		super.onResume();
		mPref.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		mPref.unregisterOnSharedPreferenceChangeListener(this);
		super.onPause();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.einstellungen);
		mPref = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
		mServer = (EditTextPreference) getPreferenceScreen().findPreference(SERVER_KEY);
		mPort = (EditTextPreference) getPreferenceScreen().findPreference(SERVER_PORT);
		mPort.setSummary(mPref.getString(KEY_PORT, "2501"));
		mServer.setSummary(mPref.getString(KEY_HOST, "127.0.0.1"));

	}

	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (key.equals(KEY_PORT)) {
			mPort.setSummary(sharedPreferences.getString(KEY_PORT, "2501"));
		}
		if (key.equals(KEY_HOST)) {
			mServer.setSummary(mPref.getString(KEY_HOST, "127.0.0.1"));
		}

	}

}
