package com.hav3n.mitbunk;

import java.io.InputStream;

import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity
{

	Button getBunks;
	CheckBox detailscheck;
	EditText regText, bdayText;
	String storedReg, storedBDay;
	public static final String PREFS_NAME = "MyPrefsFile";
	String prephotoURL;
	public static String dataUri = "http://ankit.im/websisAPI/parser.php?id=%s&bday=%s";
	ProgressDialog progressDialog;
	JSONObject temp = null;
	Bitmap tmpPhoto;
	public static String jsonURL;

	public static final String ERR_TAG = "Error Tag";
	public static final String INFO_TAG = "Info Tag";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getBunks = (Button) findViewById(R.id.getBunkButton);
		detailscheck = (CheckBox) findViewById(R.id.rememberOpts);
		regText = (EditText) findViewById(R.id.regNo);
		bdayText = (EditText) findViewById(R.id.bday);
		detailscheck.setChecked(true); // To avoid Nag

		// Check if login details are already stored

		SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		storedReg = prefs.getString("regno", null);
		storedBDay = prefs.getString("bday", null);

		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		final SharedPreferences.Editor editor = settings.edit();

		// Handle the button click
		getBunks.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v)
			{
				if (detailscheck.isChecked())
				{
					editor.putString("regno", regText.getText().toString());
					editor.putString("bday", bdayText.getText().toString());
					editor.commit();
					Toast.makeText(getApplicationContext(), "Storing Details", Toast.LENGTH_LONG).show();
				}

				String arg1 = regText.getText().toString();
				String arg2 = bdayText.getText().toString();

				// Format the url with the obtained number and bday
				dataUri = String.format(dataUri, arg1, arg2);

				new DownloadStudentData().execute();

			}
		});

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		getMenuInflater().inflate(R.menu.login_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case R.id.login_about_menuitem :
				startActivity(new Intent(this, About.class));
		}
		return true;
		// return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStart()
	{

		super.onStart();
		if (storedReg != null && storedBDay != null)
		{
			regText.setText(storedReg);
			bdayText.setText(storedBDay);
		}

	}

	private class DownloadStudentData extends AsyncTask<Void, Void, Void>
	{
		// ProgressDialog progressDialog;
		final JSONParser jsp = new JSONParser();

		@Override
		protected void onPreExecute()
		{
			// super.onPreExecute();
			progressDialog = new ProgressDialog(LoginActivity.this);
			progressDialog.setCancelable(true);
			progressDialog.setTitle(" Downloading Data ");
			progressDialog.setMessage("Please Wait, Loading...");
			progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			// progressDialog.setProgress(0);
			progressDialog.show();

		}

		@Override
		protected Void doInBackground(Void... voids)
		{

			temp = new JSONObject();
			temp = jsp.getJSONFromUrl(dataUri); // get the data

			try
			{
				prephotoURL = temp.getString("photolink");
				prephotoURL = "http://218.248.47.9" + prephotoURL;// Construct
																	// photoUrl
				InputStream in = new java.net.URL(prephotoURL).openStream();
				tmpPhoto = BitmapFactory.decodeStream(in);
			} catch (Exception e)
			{

				e.printStackTrace();

			}

			return null;
		}

		protected void onPostExecute(Void v)
		{

			/*
			 * try {
			 * 
			 * GlobalVars.setPhotoURL(temp.getString("photolink")); } catch
			 * (JSONException e) { e.printStackTrace(); }
			 */

			if (!temp.isNull("name"))
			{
				GlobalVars.setJSON(temp);
				GlobalVars.setPhotoURL(prephotoURL);
				GlobalVars.setBitmap(tmpPhoto);

				// Move to Next Intent
				Intent actChange = new Intent(getApplicationContext(), DetailsTabViewActivity.class);
				startActivity(actChange);
				finish();
			} else
			{
				Toast.makeText(getApplicationContext(), "Error in Downloading, Please Check Details or Try Again", Toast.LENGTH_LONG).show();

			}

			progressDialog.dismiss();
		}
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		if (progressDialog != null)
			progressDialog.dismiss();
	}

}
