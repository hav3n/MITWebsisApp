package com.hav3n.mitbunk;
/*
 * JSON Parser, to get JSON from URL and return JSONObject
 * 
 * Taken From:http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONParser
{

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";

	// constructor
	public JSONParser()
	{

	}

	public JSONObject getJSONFromUrl(String url)
	{

		// Making HTTP request
		try
		{
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);

			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();

		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8); // NOTE:
																									// utf-8
																									// encoding
																									// might
																									// be
																									// needed,check
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

		// try parse the string to a JSON object
		try
		{
			jObj = new JSONObject(json);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		// return JSON String
		return jObj;

	}
}
