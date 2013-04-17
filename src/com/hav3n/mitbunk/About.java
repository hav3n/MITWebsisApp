/**
 * @author Haven <haven.anddev@gmail.com>
 */


package com.hav3n.mitbunk;
/*
 * About Menu Activity
 */
import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

public class About extends Activity
{

	TextView writtenBy, email, creditsTo, source, libs;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_login);

		writtenBy = (TextView) findViewById(R.id.writtenby);
		email = (TextView) findViewById(R.id.email);
		creditsTo = (TextView) findViewById(R.id.credits);
		source = (TextView) findViewById(R.id.Source);
		libs = (TextView) findViewById(R.id.libs);

		String wtext = "Written By: <a href=\"http://www.facebook.com/dragonsrule121\">Nikhil Peter Raj</a>";
		String emailText = "Email: hav3n.anddev@gmail.com";
		String creditsText = "Credits To:<br/><a href=\"http://www.facebook.com/www.ankit.im\">Ankit Agarwal(aciid)</a> for the API";
		String sourceText = "Source Code: <a href=\"https://github.com/hav3n/Manipal_Bunks\">here</a>";
		String libsText = "\nThis App Uses The Following Code/Libs:<br/><br/><a href=\"https://github.com/koush/UrlImageViewHelper\">URLImageViewHelper</a><br/><br/><a href=\"http://commons.apache.org/proper/commons-lang/\">Apache Commons Lang Library</a><br/><br/><a href=\"http://blog.gorges.us/2010/06/android-two-dimensional-scrollview/\">TwoDScrollView</a>";

		writtenBy.setMovementMethod(LinkMovementMethod.getInstance());
		email.setMovementMethod(LinkMovementMethod.getInstance());
		creditsTo.setMovementMethod(LinkMovementMethod.getInstance());
		source.setMovementMethod(LinkMovementMethod.getInstance());
		libs.setMovementMethod(LinkMovementMethod.getInstance());

		writtenBy.setText(Html.fromHtml(wtext));
		email.setText(emailText);
		creditsTo.setText(Html.fromHtml(creditsText));
		source.setText(Html.fromHtml(sourceText));
		libs.setText(Html.fromHtml(libsText));

	}

}
