/**
 * 
 */
package de.mpw.kisdroid.adapter;

import java.util.List;

import de.mpw.kisdroid.Netzwerke;
import de.mpw.kisdroid.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author Markus
 *
 */
public class NetzwerkAdapter extends ArrayAdapter<String[]> {

	private List<String[]> objects;
	private int txtViewResourceId;
	private TextView tv_Networks;
	private TextView tv_strength;
	private TextView tv_mac;
	private TextView tv_server_port;
	private TextView tv_time;
	private TextView tv_encryption;
	private TextView tv_channel;

	public NetzwerkAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
			this.objects = objects;
			this.txtViewResourceId = textViewResourceId;
	}

	/**
	 * 
	 */

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	public String[] getItem(int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	public long getItemId(int position) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			LayoutInflater vi = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = vi.inflate(txtViewResourceId, null);
		}
		tv_Networks = (TextView) view.findViewById(R.id.tv_Networks);
		tv_strength = (TextView) view.findViewById(R.id.tv_strength);
		tv_mac = (TextView) view.findViewById(R.id.tv_mac);
		tv_server_port = (TextView) view.findViewById(R.id.tv_server_port);
		tv_time = (TextView) view.findViewById(R.id.tv_time);
		tv_encryption = (TextView) view.findViewById(R.id.tv_encryption);
		tv_channel = (TextView) view.findViewById(R.id.tv_channel);view.findViewById(R.id.tv_Networks);
		tv_Networks.setText(getItem(position)[0]);
		return view;
	}


}
