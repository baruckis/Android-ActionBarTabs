package com.baruckis.ActionBarTabs;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class TabsDifferentFragmentsActivity extends Activity {
	private TabManager tabManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs_different_fragments_activity);
		
		// For each tab first we create two different listeners.
		// We use our own custom listener and pass arguments to instantiate new different fragments.
		CustomTabListener<ConcreteListFragment> firstListener = new CustomTabListener<ConcreteListFragment>(
				this, Constants.INIT_CONCRETE_LIST_FRAGMENT_TAG,
				R.array.popular_brands, ConcreteListFragment.class);

		CustomTabListener<ConcreteGridFragment> secondListener = new CustomTabListener<ConcreteGridFragment>(
				this, Constants.INIT_CONCRETE_GRID_FRAGMENT_TAG,
				R.array.logos_of_brands, ConcreteGridFragment.class);

		// We have our own reusable tab manager, which adds tab navigation to our application.
		tabManager = new TabManager(getActionBar());
		tabManager.addTab(
				getString(R.string.different_fragments_first_tab_title),
				Constants.FIRST_TAB_TAG, firstListener);
		tabManager.addTab(
				getString(R.string.different_fragments_second_tab_title),
				Constants.SECOND_TAB_TAG, secondListener);

		// Every time activity is recreated, like on device screen rotation,
		// we select a tab which was chosen by the user before.
		if (savedInstanceState != null) {
			tabManager.setActiveTab(savedInstanceState.getInt(
					Constants.TAB_STATE_KEY, 0));
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(Constants.TAB_STATE_KEY, tabManager.getActiveTab());
	}
}
