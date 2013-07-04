package com.arthur.ngaclient.activity;

import java.util.List;
import java.util.Locale;

import com.arthur.ngaclient.NGAClientApplication;
import com.arthur.ngaclient.R;
import com.arthur.ngaclient.bean.Board;
import com.arthur.ngaclient.bean.Plate;
import com.arthur.ngaclient.widget.CustomGridView;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {

	private static final String TAG = "MainActivity";
	public static final String ARG_SECTION_NUMBER = "section_number";

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		mViewPager.setCurrentItem(1);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;

			Log.d(TAG, "getItem ================" + position);
			switch (position) {
			case 0:
			case 1:
				fragment = new DummySectionFragment();
				Bundle args = new Bundle();
				args.putInt(ARG_SECTION_NUMBER, position);
				fragment.setArguments(args);
				break;
			case 2:
				fragment = new AllBoardsFragment();
				args = new Bundle();
				args.putInt(ARG_SECTION_NUMBER, position);
				fragment.setArguments(args);
				break;
			}
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {

		private static final String TAG = "DummySectionFragment";

		public DummySectionFragment() {
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Log.d(TAG, "onCreate ============== ");
			int i = getArguments().getInt(ARG_SECTION_NUMBER);
			Log.d(TAG, "onCreate i ================ " + i);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Log.d(TAG, "onCreateView ================ ");
			int i = getArguments().getInt(ARG_SECTION_NUMBER);
			Log.d(TAG, "onCreateView i ================ " + i);
			View rootView = inflater.inflate(R.layout.fragment_main_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	public static class AllBoardsFragment extends Fragment {

		private static final String TAG = "AllBoardsFragment";

		public AllBoardsFragment() {
		}

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			Log.d(TAG, "onCreate ============== ");
			int i = getArguments().getInt(ARG_SECTION_NUMBER);
			Log.d(TAG, "onCreate i ================ " + i);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			Log.d(TAG, "onCreateView ================ ");
			int i = getArguments().getInt(ARG_SECTION_NUMBER);
			Log.d(TAG, "onCreateView i ================ " + i);
			View rootView = inflater.inflate(R.layout.fragment_main_all_board,
					container, false);
			ListView lvAllBoard = (ListView) rootView
					.findViewById(R.id.main_allboard_listview);
			List<Plate> plates = ((NGAClientApplication) getActivity()
					.getApplication()).loadDefaultBoard();
			AllBoardListAdapter allBoardListAdapter = new AllBoardListAdapter(
					getActivity(), plates);
			lvAllBoard.setAdapter(allBoardListAdapter);
			return rootView;
		}
	}

	public static class AllBoardListAdapter extends BaseAdapter {

		private List<Plate> mPlatesData = null;
		private LayoutInflater mInflater = null;
		private Context mContext = null;

		public AllBoardListAdapter(Context context, List<Plate> platesData) {
			mContext = context;
			mInflater = LayoutInflater.from(context);
			mPlatesData = platesData;
		}

		@Override
		public int getCount() {
			return mPlatesData.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_main_plate, null);
			}
			Plate plate = mPlatesData.get(position);
			TextView tvPlate = (TextView) convertView
					.findViewById(R.id.plateName);
			tvPlate.setText(plate.getName());
			CustomGridView gvBoardList = (CustomGridView) convertView
					.findViewById(R.id.main_board_gridview);
			BoardGridViewAdapter boardAdapter = new BoardGridViewAdapter(mContext, plate);
			gvBoardList.setAdapter(boardAdapter);
			
			return convertView;
		}

	}

	public static class BoardGridViewAdapter extends BaseAdapter {
		
		private LayoutInflater mInflater = null;
		private Plate mPlate = null;
		private List<Board> mBoardList = null;

		public BoardGridViewAdapter(Context context, Plate plate) {
			mInflater = LayoutInflater.from(context);
			mPlate = plate;
			mBoardList = plate.getBoardList();
		}

		@Override
		public int getCount() {
			return mPlate.getCount();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.item_main_board, null);
			}
			ImageView ivBoardIcon = (ImageView) convertView.findViewById(R.id.main_board_ic);
			TextView tvBoardName = (TextView) convertView.findViewById(R.id.main_board_name);
			ivBoardIcon.setImageResource(mBoardList.get(position).getIcon());
			tvBoardName.setText(mBoardList.get(position).getName());
			return convertView;
		}

	}

}
