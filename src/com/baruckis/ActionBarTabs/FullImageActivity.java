package com.baruckis.ActionBarTabs;

import com.baruckis.ActionBarTabs.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class FullImageActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.full_image);

		Intent intent = getIntent();
		int imageId = intent.getExtras().getInt(Constants.FULL_IMAGE_KEY);

		ImageView imageView = (ImageView) findViewById(R.id.image_view);
		imageView.setImageResource(imageId);
	}
}