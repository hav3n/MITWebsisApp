package com.hav3n.mitbunk;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AttendanceListAdapter extends BaseAdapter
{
	private ArrayList<HashMap<String, String>> adapterkeylist = new ArrayList<HashMap<String, String>>();
	private Context context;
	private static LayoutInflater inflater = null;
	TextView subject, code, attended, absent, percent, updated;

	public AttendanceListAdapter(Context c, ArrayList<HashMap<String, String>> jk)
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

		// return s[s.length/6][s.length%6];
		return adapterkeylist.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		View v = convertView;

		if (convertView == null)
			v = inflater.inflate(R.layout.attendance_listrow, null);

		subject = (TextView) v.findViewById(R.id.subName);
		subject.setSelected(true);
		code = (TextView) v.findViewById(R.id.ccode);
		attended = (TextView) v.findViewById(R.id.subAttended);
		absent = (TextView) v.findViewById(R.id.subAbsent);
		percent = (TextView) v.findViewById(R.id.subPercent);
		updated = (TextView) v.findViewById(R.id.subUpdated);

		HashMap<String, String> row = new HashMap<String, String>();

		row = adapterkeylist.get(position);

		subject.setText(row.get("cname"));
		code.setText("Subject Code: " + row.get("ccode"));
		attended.setText("Classes Attended: " + row.get("cclass"));
		absent.setText("Classes Absent: " + row.get("bunked"));
		percent.setText("Attendance Percentage: " + row.get("percent"));
		updated.setText("Last Updated: " + row.get("updated"));

		return v;

	}

}
