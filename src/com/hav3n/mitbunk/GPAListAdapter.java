package com.hav3n.mitbunk;
/*
 * Custom Adapter for The GPA ListView
 */
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GPAListAdapter extends BaseAdapter
{
	private ArrayList<HashMap<String, String>> adapterkeylist = new ArrayList<HashMap<String, String>>();
	private Context context;
	private static LayoutInflater inflater = null;
	TextView semTitle, semGPA;

	public GPAListAdapter(Context c, ArrayList<HashMap<String, String>> jk)
	{
		context = c;
		adapterkeylist = jk;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount()
	{
		return adapterkeylist.size();
	}

	@Override
	public Object getItem(int position)
	{
		return adapterkeylist.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;

		if (convertView == null)
			v = inflater.inflate(R.layout.gpa_listrow, null);

		semTitle = (TextView) v.findViewById(R.id.semName);
		semTitle.setSelected(true);
		semGPA = (TextView) v.findViewById(R.id.semGPA);

		semTitle.setFocusable(false);
		semTitle.setClickable(false);

		semGPA.setFocusable(false);
		semTitle.setClickable(false);

		HashMap<String, String> row = new HashMap<String, String>();

		row = adapterkeylist.get(position);

		semTitle.setText(row.get("semTitle"));
		semGPA.setText("GPA: " + row.get("semGPA"));

		if (row.get("semGPA").equalsIgnoreCase("N/A"))
		{
			semTitle.setFocusable(true);
			semTitle.setClickable(true);

			semGPA.setFocusable(true);
			semTitle.setClickable(true);
		}

		return v;
	}

}
