package de.mpw.kisdroid;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class Einstellungen extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.einstellungen);
	}

}
