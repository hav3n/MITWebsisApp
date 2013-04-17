/**
 * @author Haven <haven.anddev@gmail.com>
 */

package com.hav3n.mitbunk;
/*
 * Adapter for Sem ListView
 */
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SemListAdapter extends BaseAdapter
{
	private ArrayList<HashMap<String, String>> adapterkeylist = new ArrayList<HashMap<String, String>>();
	private Context context;
	private static LayoutInflater inflater = null;
	TextView semSubName, semCCode, semSubCredits, semSubGrade;
	String temp = new String();

	public SemListAdapter(Context c, ArrayList<HashMap<String, String>> jk)
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
			v = inflater.inflate(R.layout.sem_details_listrow, null);

		semSubName = (TextView) v.findViewById(R.id.semSubName);
		semSubName.setSelected(true);
		semCCode = (TextView) v.findViewById(R.id.semCCode);
		semSubCredits = (TextView) v.findViewById(R.id.semSubCredits);
		semSubGrade = (TextView) v.findViewById(R.id.semSubGrade);

		HashMap<String, String> row = new HashMap<String, String>();

		row = adapterkeylist.get(position);

		temp = row.get("sub");
		temp = StringEscapeUtils.unescapeHtml4(temp);

		semSubName.setText(temp);
		semCCode.setText("Subject Code: " + row.get("subcode"));
		semSubCredits.setText("Subject Credits: " + row.get("credits"));
		semSubGrade.setText("Subject Grade: " + row.get("grade"));

		return v;
	}

}
