package com.baruckis.ActionBarTabs;

import android.app.ActionBar;
import android.app.ActionBar.Tab;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class TabManager {
	private ActionBar actionBar;

	/**
	 * This constructor initialises TabManager by preparing tab navigation mode.
	 * 
	 * @param actionBar
	 *            Give a reference to this activity's ActionBar.
	 */
	public TabManager(ActionBar actionBar) {
		this.actionBar = actionBar;
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	/**
	 * This method adds tab to the ActionBar. Sets it's title, tag and listener.
	 * 
	 * @param title
	 *            Set the text displayed on this tab.
	 * @param tag
	 *            Give this Tab an arbitrary string to hold for later use.
	 * @param listener
	 *            Give listener that will handle switching to and from the tabs.
	 */
	public void addTab(String title, String tag, ActionBar.TabListener listener) {
		Tab concreteTab = actionBar.newTab();
		concreteTab.setText(title);
		concreteTab.setTag(tag);
		concreteTab.setTabListener(listener);
		actionBar.addTab(concreteTab);
	}

	/**
	 * Sets active tab in tabbed navigation mode.
	 * 
	 * @param which
	 *            Position of the tab to select to be active.
	 */
	public void setActiveTab(int which) {
		actionBar.setSelectedNavigationItem(which);
	}

	/**
	 * Gets the position of the currently active tab.
	 * 
	 * @return position of the selected tab which is active.
	 */
	public int getActiveTab() {
		return actionBar.getSelectedNavigationIndex();
	}
}
