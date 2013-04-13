package com.hav3n.mitbunk;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GPADetailsTabFragment extends Fragment
{
	GPAListAdapter adapter;
	JSONArray ar;
	JSONObject jobj;
	ArrayList<HashMap<String, String>> keyslist;
	ProgressDialog listProgress;
	public static String semUri = LoginActivity.dataUri + "&link=";
	String prevSemUri[];
	String temp;
	JSONParser listParser;
	JSONObject listJson;
	boolean successFlag = true;
	TextView CGPA, totCredits;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		listParser = new JSONParser();
		listJson = new JSONObject();

		if (container == null)
		{
			return null;
		}

		View view = inflater.inflate(R.layout.gpa_listview_layout, container, false);

		keyslist = new ArrayList<HashMap<String, String>>();

		totCredits = (TextView) view.findViewById(R.id.totCredits);
		CGPA = (TextView) view.findViewById(R.id.CGPA);

		try
		{

			totCredits.setText("Total Credits: " + GlobalVars.getJSON().getString("total_credits"));
			CGPA.setText(" CGPA: " + GlobalVars.getJSON().getString("cgpa"));

			ar = new JSONArray();
			ar = GlobalVars.getJSON().getJSONArray("sems");
			prevSemUri = new String[ar.length()];

			for (int i = 0; i < ar.length(); i++)
			{
				HashMap<String, String> map = new HashMap<String, String>();

				JSONObject jObject = ar.getJSONObject(i);

				map.put("semTitle", jObject.getString("sem"));
				map.put("semGPA", jObject.getString("gpa"));

				keyslist.add(map);
			}

			for (int i = 0; i < ar.length(); i++)
			{
				// Create each link of the sems and store in an array
				JSONObject jObject = ar.getJSONObject(i);
				temp = jObject.getString("link");
				temp = URLEncoder.encode(temp, "utf-8");
				prevSemUri[i] = new String(semUri + temp);

			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{

			e.printStackTrace();
		}

		ListView list = (ListView) view.findViewById(R.id.gpalist);

		adapter = new GPAListAdapter(getActivity(), keyslist);
		list.setAdapter(adapter);

		list.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{

				new DownloadSemDetails().execute(prevSemUri[position]);

			}

		});

		Toast.makeText(getActivity(), "For More Information Tap on a Row", Toast.LENGTH_SHORT).show();

		return view;
	}

	private class DownloadSemDetails extends AsyncTask<String, Void, Void>
	{

		@Override
		protected void onPreExecute()
		{
			listProgress = new ProgressDialog(getActivity());
			listProgress.setCancelable(true);
			listProgress.setMessage("Loading");
			listProgress.setTitle("");
			listProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			listProgress.show();
			// super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... posUri)
		{
			try
			{
				listJson = listParser.getJSONFromUrl(posUri[0]);

			} catch (Exception e)
			{
				e.getStackTrace();
				successFlag = false;

			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			listProgress.dismiss();
			Intent intent;
			if (successFlag)
			{

				intent = new Intent(getActivity(), SemListViewActivity.class);
				GlobalVars.semJSON = listJson;
				startActivity(intent);
			} else
			{
				Toast.makeText(getActivity(), "Error Connecting to Internet,Try Again Later", Toast.LENGTH_SHORT).show();
				successFlag = true;
			}

		}

	}

}
