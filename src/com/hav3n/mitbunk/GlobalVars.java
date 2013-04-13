package com.hav3n.mitbunk;

import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.widget.ImageView;

//This class is used to store the global variables viz. the JSONObject returned

public class GlobalVars
{
	public static final String ERR_TAG = "Error Tag";
	public static JSONObject downloadedData;
	public static String photoUrl = new String();
	public static ImageView storedImg;
	public static Bitmap photo = null;
	public static JSONObject semJSON = new JSONObject();

	public static ImageView getImg()
	{
		return storedImg;
	}

	public static JSONObject getJSON()
	{
		return downloadedData;
	}

	public static void setImg(ImageView imageview)
	{
		storedImg = imageview;

	}

	public static void setJSON(JSONObject jsonObj)
	{
		downloadedData = jsonObj;
	}

	public static void setPhotoURL(String uri)
	{

		photoUrl = new String(uri);

	}

	@SuppressWarnings("finally")
	public static URL getPhotoURL()
	{
		JSONObject temp = downloadedData;
		URL retUrl = null;
		try
		{
			retUrl = new URL(temp.getString("photolink"));
		} catch (JSONException e)
		{
			e.printStackTrace();

		} finally
		{
			return retUrl;
		}
	}

	public static void setBitmap(Bitmap bm)
	{
		photo = bm;
	}

}
