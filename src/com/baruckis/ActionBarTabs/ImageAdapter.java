package com.baruckis.ActionBarTabs;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class ImageAdapter extends BaseAdapter {
	private Context context;
	private TypedArray imagesArray;

	public ImageAdapter(Context context, int resourceId) {
		this.context = context;
		imagesArray = context.getResources().obtainTypedArray(resourceId);
	}

	@Override
	public int getCount() {
		return imagesArray.length();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return imagesArray.getResourceId(position, -1);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		if (convertView == null) {
			imageView = new ImageView(context);
			imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
			imageView.setAdjustViewBounds(false);
			imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		} else {
			imageView = (ImageView) convertView;
		}
		imageView.setImageResource(imagesArray.getResourceId(position, -1));
		return imageView;
	}
	
	public void recycleImagesArray() {
		// we need to recycle because it is TypedArray.
		imagesArray.recycle();
	}
}
