package de.mpw.kisdroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class KisdroidActivity extends Activity {
	public KismetClient client;
	public String SERVER;
	public int PORT;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void onClick(final View view){
    	switch (view.getId()) {
		case R.id.sf_start:
			SERVER= "192.168.2.11";
			PORT = 2501;
			client = new KismetClient(SERVER,PORT);
			client.start();
			break;
		case R.id.sf_stop:
			client.stopClient();
			break;

		default:
			break;
		}
    }
}