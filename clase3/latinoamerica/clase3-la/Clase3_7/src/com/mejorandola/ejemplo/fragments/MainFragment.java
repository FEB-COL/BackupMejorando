package com.mejorandola.ejemplo.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mejorandola.ejemplo.R;
import com.mejorandola.ejemplo.data.CustomPagerAdapter;

public class MainFragment extends Fragment 
						 implements ViewPager.OnPageChangeListener, ActionBar.TabListener {
	private ViewPager view_pager;
	private CustomPagerAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, null);
        view_pager = (ViewPager) view.findViewById(R.id.pager);
		return view;
	}
	
	@Override
	public void onActivityCreated(
			Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		adapter = new CustomPagerAdapter(getActivity().getSupportFragmentManager());
		view_pager.setAdapter(adapter);
		view_pager.setOnPageChangeListener(this);
		
		ActionBar bar = getActivity().getActionBar();
		bar.removeAllTabs();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab().setText("Lista").setTabListener(this));
		bar.addTab(bar.newTab().setText("Grid").setTabListener(this));
		bar.addTab(bar.newTab().setText("Mapa").setTabListener(this));
	}

	@Override
	public void onPageScrollStateChanged(
			int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(
			int arg0, float arg1,
			int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		getActivity().getActionBar().setSelectedNavigationItem(arg0);
	}

	@Override
	public void onTabReselected(
			Tab tab,
			FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab,
			FragmentTransaction ft) {
		view_pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(
			Tab tab,
			FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

}
