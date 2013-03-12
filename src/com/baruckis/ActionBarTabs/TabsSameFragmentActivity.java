package com.baruckis.ActionBarTabs;

import com.baruckis.ActionBarTabs.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class TabsSameFragmentActivity extends Activity implements
		ActionBar.TabListener {
	private ConcreteListFragment concreteListFragment;
	private TabManager tabManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dynamic_list_fragment_activity);

		concreteListFragment = new ConcreteListFragment();
		
		// We replace fragment in order to avoid items of ListView in ListFragment to overlap over themselves.
		getFragmentManager().beginTransaction()
				.replace(R.id.fragment_container, concreteListFragment)
				.commit();

		// We have our own reusable tab manager, which adds tab navigation to our application.
		tabManager = new TabManager(getActionBar());
		tabManager.addTab(getString(R.string.same_fragment_first_tab_title),
				Constants.FIRST_TAB_TAG, this);
		tabManager.addTab(getString(R.string.same_fragment_second_tab_title),
				Constants.SECOND_TAB_TAG, this);

		// Every time activity is recreated, like on device screen rotation,
		// we select a tab which was chosen by the user before.
		if (savedInstanceState != null) {
			tabManager.setActiveTab(savedInstanceState.getInt(
					Constants.TAB_STATE_KEY, 0));
		}

		Bundle bundle = new Bundle();
		bundle.putInt(Constants.INIT_CONCRETE_LIST_FRAGMENT_TAG,
				getResourceId());
		concreteListFragment.setArguments(bundle);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(Constants.TAB_STATE_KEY, tabManager.getActiveTab());
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		if (concreteListFragment.isResumed()) {
			// When tab is pressed, we update our fragment with new data.
			concreteListFragment.updateListFragment(getResourceId());
		}
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	public int getResourceId() {
		// In order to decide which resource we need to pass,
		// we check by a tag of selected tab.
		if (getActionBar().getSelectedTab().getTag()
				.equals(Constants.FIRST_TAB_TAG)) {
			return R.array.popular_brands;
		}
		if (getActionBar().getSelectedTab().getTag()
				.equals(Constants.SECOND_TAB_TAG)) {
			return R.array.all_brands;
		}
		return 0;
	}
}
