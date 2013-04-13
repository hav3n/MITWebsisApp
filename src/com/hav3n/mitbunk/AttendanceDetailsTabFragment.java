package com.hav3n.mitbunk;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class AttendanceDetailsTabFragment extends Fragment
{
	AttendanceListAdapter adapter;
	JSONArray ar;
	JSONObject jobj;
	ArrayList<HashMap<String, String>> keyslist;
	

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		View view = inflater.inflate(R.layout.attendance_listview_layout, container, false);

		keyslist = new ArrayList<HashMap<String, String>>();
		String temp = new String();

		try
		{
			ar = new JSONArray();
			ar = GlobalVars.getJSON().getJSONArray("attendanceData");

			for (int i = 0; i < ar.length(); i++)
			{
				HashMap<String, String> map = new HashMap<String, String>();

				JSONObject jObject = ar.getJSONObject(i);

				temp = jObject.getString("cname");
				temp = StringEscapeUtils.unescapeHtml4(temp);

				map.put("ccode", jObject.getString("ccode"));
				map.put("cname", temp);
				map.put("cclass", jObject.getString("cclass"));
				map.put("bunked", jObject.getString("bunked"));
				map.put("percent", jObject.getString("percent"));
				map.put("updated", jObject.getString("updated"));

				keyslist.add(map);

			}

		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		ListView list = (ListView) view.findViewById(R.id.list);

		// adapter.notifyDataSetChanged();
		adapter = new AttendanceListAdapter(getActivity(), keyslist);
		list.setAdapter(adapter);

		if (container == null)
		{
			return null;
		}

		return view;
	}

}
