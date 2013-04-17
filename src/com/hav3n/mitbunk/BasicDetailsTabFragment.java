/**
 * @author Haven <haven.anddev@gmail.com>
 */

package com.hav3n.mitbunk;
/*
 * Fragment for Basic Details
 * TableLayout is Created Dynamically since it makes more sense that way
 * as we don't have an entire column
 * 
 */
import java.util.Locale;

import org.apache.commons.lang3.text.WordUtils;
import org.json.JSONException;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class BasicDetailsTabFragment extends Fragment
{
	int numvals = 5;
	public static final int NAME = 0;
	public static final int BRANCH = 1;
	public static final int SECTION = 2;
	public static final int JOINYEAR = 3;
	public static final int EMAIL = 4;
	public static final int ADMISSION = 5;
	public static final int ROLLNO = 6;

	public static final String ERR_TAG = "Error Tag";
	public static final String INFO_TAG = "Info Tag";

	String[] details = new String[7];
	String[] detailVals = new String[7];
	String tempEmail;// Get the values from JSON here

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		if (container == null)
		{
			return null;
		}

		details[NAME] = "Name";
		details[BRANCH] = "Branch";
		details[SECTION] = "Section";
		details[JOINYEAR] = "Year of Joining ";
		details[EMAIL] = "Email";
		details[ADMISSION] = "Admission No.";
		details[ROLLNO] = "Roll No. ";

		try
		{
			tempEmail = GlobalVars.getJSON().getString("manipalEmail");
			tempEmail = tempEmail.replace("&#64;", "@");
			String temp = GlobalVars.getJSON().getString("name");
			temp = temp.toLowerCase(Locale.US);
			temp = WordUtils.capitalize(temp);
			detailVals[NAME] = temp;
			detailVals[BRANCH] = GlobalVars.getJSON().getString("course");
			detailVals[SECTION] = GlobalVars.getJSON().getString("section");
			detailVals[JOINYEAR] = GlobalVars.getJSON().getString("joinyear");
			detailVals[EMAIL] = tempEmail;
			detailVals[ADMISSION] = GlobalVars.getJSON().getString("AdmissionNo");
			detailVals[ROLLNO] = GlobalVars.getJSON().getString("RollNo");

		} catch (JSONException e)
		{
			e.printStackTrace();

		}

		int bottomMargin, topMargin;

		View view = inflater.inflate(R.layout.basic_details_layout, container, false);

		TableLayout tl = (TableLayout) view.findViewById(R.id.detailsTable);

		ImageView iv = (ImageView) view.findViewById(R.id.studentPhoto);

		Bitmap resizedPhoto = Bitmap.createScaledBitmap(GlobalVars.photo, 300, 350, false);
		iv.setImageBitmap(resizedPhoto);

		for (int i = 0; i < 7; i++)
		{
			// Make TR
			TableRow tr = new TableRow(getActivity());
			tr.setId(100 + i);
			tr.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

			// Make TV to hold the details
			TextView detailstv = new TextView(getActivity());
			detailstv.setId(200 + i);
			detailstv.setText(details[i]);
			detailstv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
			detailstv.setPaintFlags(detailstv.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
			detailstv.setTypeface(Typeface.DEFAULT_BOLD);

			tr.addView(detailstv);

			// Make TV to hold the detailvals

			TextView valstv = new TextView(getActivity());
			valstv.setId(300 + i);
			valstv.setText(detailVals[i]);
			valstv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17.5f);
			tr.addView(valstv);

			topMargin = getResources().getDimensionPixelSize(R.dimen.top_bottom_margins);
			bottomMargin = getResources().getDimensionPixelSize(R.dimen.top_bottom_margins);

			TableLayout.LayoutParams tableRowParams = new TableLayout.LayoutParams();
			tableRowParams.setMargins(0, topMargin, 0, bottomMargin);
			tr.setLayoutParams(tableRowParams);
			view.requestLayout();

			tl.addView(tr, new TableLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		}

		return view;
		

	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}
}
