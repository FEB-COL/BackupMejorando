package com.mejorandola.ejemplo.data;

import com.mejorandola.ejemplo.fragments.PlacesMapFragment;
import com.mejorandola.ejemplo.fragments.RoomGridFragment;
import com.mejorandola.ejemplo.fragments.RoomListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class CustomPagerAdapter extends FragmentStatePagerAdapter {
	private Fragment[] fragments;
	
	public CustomPagerAdapter(
			FragmentManager fm) {
		super(fm);
		fragments = new Fragment[]{
				new RoomListFragment(),
				new RoomGridFragment(),
				new PlacesMapFragment()
		};
	}

	@Override
	public Fragment getItem(int arg0) {
		return fragments[arg0];
	}

	@Override
	public int getCount() {
		return fragments.length;
	}

}
