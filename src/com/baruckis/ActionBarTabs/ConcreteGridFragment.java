package com.baruckis.ActionBarTabs;

import com.baruckis.ActionBarTabs.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class ConcreteGridFragment extends Fragment {
	private GridView gridView;
	private ImageAdapter imageAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.grid, container, false);
		gridView = (GridView) view.findViewById(R.id.grid_view);

		// We are showing a full image when user clicks on the thumbnail.
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(view.getContext()
						.getApplicationContext(), FullImageActivity.class);
				int value = (int) imageAdapter.getItemId(position);
				intent.putExtra(Constants.FULL_IMAGE_KEY, value);
				startActivity(intent);
			}
		});

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle arguments = getArguments();
		if (arguments != null) {
			updateFragment(arguments
					.getInt(Constants.INIT_CONCRETE_GRID_FRAGMENT_TAG));
		}
	}
	
	@Override
	public void onDestroy() {
		imageAdapter.recycleImagesArray();
		super.onDestroy();
	}

	public void updateFragment(int resourceId) {
		// We are creating an array adapter to store the list of brands.
		imageAdapter = new ImageAdapter(this.getActivity(), resourceId);
		
		// We are setting the list adapter for the ListFragment.
		gridView.setAdapter(imageAdapter);
	}
}