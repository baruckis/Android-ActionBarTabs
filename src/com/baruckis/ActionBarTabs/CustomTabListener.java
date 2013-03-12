package com.baruckis.ActionBarTabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
class CustomTabListener<Type extends Fragment> implements ActionBar.TabListener {

	private Fragment fragment;
	private final Activity activity;
	private final String tag;
	private final int resourceId;
	private final Class<Type> myClass;

	/**
	 * Constructor used each time a new tab is created.
	 * 
	 * @param activity
	 *            The host Activity, used to instantiate the fragment
	 * @param tag
	 *            The identifier tag for the fragment
	 * @param resourceId
	 *            The resource id from which data is taken
	 * @param Class
	 *            <Type> The fragment's Class, used to instantiate the fragment
	 */
	public CustomTabListener(Activity activity, String tag, int resourceId,
			Class<Type> myClass) {
		this.activity = activity;
		this.tag = tag;
		this.resourceId = resourceId;
		this.myClass = myClass;

		// Check to see if we already have a fragment for this tab, probably
		// from a previously saved state. If so, deactivate it, because our
		// initial state is that a tab isn't shown.
		fragment = activity.getFragmentManager().findFragmentByTag(tag);
		if (fragment != null && !fragment.isDetached()) {
			FragmentTransaction ft = activity.getFragmentManager()
					.beginTransaction();
			ft.detach(fragment);
			ft.commit();
		}
	}

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Check if the fragment is already initialised
		if (fragment == null) {
			// If not, instantiate and add it to the activity
			fragment = Fragment.instantiate(activity, myClass.getName());

			Bundle bundle = new Bundle();
			bundle.putInt(tag, resourceId);
			fragment.setArguments(bundle);

			ft.add(android.R.id.content, fragment, tag);
		} else {
			// If it exists, simply attach it in order to show it
			ft.attach(fragment);
		}
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (fragment != null) {
			// Detach the fragment, because another one is being attached
			ft.detach(fragment);
		}
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}
}
