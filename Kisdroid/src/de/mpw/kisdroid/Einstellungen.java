package de.mpw.kisdroid;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;
import android.text.InputType;

public class Einstellungen extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	public static final String KEY_NETZWERKAKTUALISIERUNGSRATE = "netzwerk_aktualisierungs_rate";
	public static final String KEY_HOST = "host";
	public static final String KEY_PORT = "port";
	public static final String KEY_SSID = "SSID";
	public static final String KEY_MAC = "mac";
	public static final String KEY_ENCRYPTION = "encryption";
	public static final String KEY_STRENGTH = "strength";
	public static final String KEY_CHANNEL = "channel";

	EditTextPreference mServer;
	EditTextPreference mPort;
	EditTextPreference mNetzwerkAktualisierungsRate;
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
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.einstellungen);
		mPref = getSharedPreferences(getPackageName() + "_preferences", MODE_PRIVATE);
		mServer = (EditTextPreference) getPreferenceScreen().findPreference(KEY_HOST);
		mNetzwerkAktualisierungsRate = (EditTextPreference) getPreferenceScreen().findPreference(
				KEY_NETZWERKAKTUALISIERUNGSRATE);
		mPort = (EditTextPreference) getPreferenceScreen().findPreference(KEY_PORT);
		mPort.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		mNetzwerkAktualisierungsRate.getEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
		mNetzwerkAktualisierungsRate.setSummary(mPref.getString(KEY_NETZWERKAKTUALISIERUNGSRATE,
				"2000"));
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
		if (key.equals(KEY_NETZWERKAKTUALISIERUNGSRATE)) {
			mNetzwerkAktualisierungsRate.setSummary(mPref.getString(
					KEY_NETZWERKAKTUALISIERUNGSRATE, "2000"));
		}

	}

}
