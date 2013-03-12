package com.baruckis.ActionBarTabs;

import com.baruckis.ActionBarTabs.R;

import android.app.DialogFragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author Andrius Baruckis http://www.baruckis.com
 * 
 */
public class ConcreteListFragment extends ListFragment {
	private TextView myTextView;
	private ListView myListView;
	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.concrete_list_fragment,
				container, false);

		myTextView = (TextView) view.findViewById(R.id.textview_label);
		myTextView.setText(R.string.textview_label);

		myListView = (ListView) view.findViewById(android.R.id.list);
		myListView.setEmptyView(null);
		myListView.refreshDrawableState();
		myListView.setAdapter(null);

		context = view.getContext();

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Bundle arguments = getArguments();

		if (arguments != null) {
			updateListFragment(arguments
					.getInt(Constants.INIT_CONCRETE_LIST_FRAGMENT_TAG));
		}
	}

	public void updateListFragment(int resourceId) {
		// We are creating an array adapter to store the list of brands.
		ArrayAdapter<CharSequence> adapter = null;
		adapter = ArrayAdapter.createFromResource(context, resourceId,
				android.R.layout.simple_list_item_1);
		// We are setting the list adapter for the ListFragment.
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);

		String item = (String) list.getItemAtPosition(position);
		String message = getString(R.string.dialog_list_item_click_message_part1)
				+ item
				+ Html.fromHtml(getString(R.string.dialog_list_item_click_message_part2));
		// When list item is clicked, we show a alert message for the user.
		DialogFragment dialogFragment = AlertDialogFragment.newInstance(
				R.drawable.ic_dialog_alert,
				getString(R.string.dialog_list_item_click_title), message,
				getString(R.string.dialog_list_item_click_button_text));
		dialogFragment
				.show(getFragmentManager(), Constants.DIALOG_FRAGMENT_TAG);
	}
}
