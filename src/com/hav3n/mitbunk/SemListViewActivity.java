package com.hav3n.mitbunk;
/*
 * The Activity Launched Within GPA Fragment, Displays ListView of Prev Sems
 */
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

public class SemListViewActivity extends Activity
{
	JSONObject recdJson = new JSONObject();
	JSONArray ar;
	ArrayList<HashMap<String, String>> keyslist;
	TextView semTCredits, semSession, semGPA;
	ListView semList;
	SemListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sem_details_listview_layout);

		ar = new JSONArray();
		keyslist = new ArrayList<HashMap<String, String>>();
		try
		{
			recdJson = GlobalVars.semJSON;

			ar = recdJson.getJSONArray("seminfo");

			for (int i = 0; i < ar.length(); i++)
			{
				JSONObject jObject = ar.getJSONObject(i);
				HashMap<String, String> map = new HashMap<String, String>();

				map.put("subcode", jObject.getString("subcode"));
				map.put("sub", jObject.getString("sub"));
				map.put("credits", jObject.getString("credits"));
				map.put("grade", jObject.getString("grade"));

				keyslist.add(map);
			}

			semTCredits = (TextView) findViewById(R.id.semTCredits);
			semSession = (TextView) findViewById(R.id.semSession);
			semGPA = (TextView) findViewById(R.id.semGPA);

			semTCredits.setText("Total Credits: " + recdJson.getString("credits"));
			semSession.setText("Session: " + recdJson.getString("session"));
			semGPA.setText("Semester GPA: " + recdJson.getString("gpa"));

			semList = (ListView) findViewById(R.id.semlist);

			adapter = new SemListAdapter(this, keyslist);

			semList.setAdapter(adapter);

			// setContentView(R.layout.sem_details_listview_layout);

		} catch (JSONException e)
		{
			e.printStackTrace();

		}

	}

}
