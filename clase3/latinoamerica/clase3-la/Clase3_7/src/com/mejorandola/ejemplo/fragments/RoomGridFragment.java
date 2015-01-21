package com.mejorandola.ejemplo.fragments;

import java.util.ArrayList;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher.OnRefreshListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.mejorandola.ejemplo.MainActivity;
import com.mejorandola.ejemplo.R;
import com.mejorandola.ejemplo.RoomDetailActivity;
import com.mejorandola.ejemplo.data.CustomAdapter;
import com.mejorandola.ejemplo.models.Room;

public class RoomGridFragment extends Fragment
							 implements OnRefreshListener, OnItemClickListener {
	
	private GridView grid;
	private PullToRefreshAttacher pull_to_refresh_attacher;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_room_grid, null);
		grid = (GridView)view.findViewById(R.id.grid_rooms);
		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		pull_to_refresh_attacher = ((MainActivity)getActivity()).getAttacher();
		pull_to_refresh_attacher.addRefreshableView(grid, this);
		ArrayList<Room> rooms = new ArrayList<Room>();
		
		for (String room : getResources().getStringArray(R.array.array_rooms_standard)) {
			Room one_room = new Room(room, Room.STANDARD_ROOM);
	        rooms.add(one_room);							
		}
		
		for (String room : getResources().getStringArray(R.array.array_rooms_luxury)) {
			Room one_room = new Room(room, Room.LUXURY_ROOM);
	        rooms.add(one_room);						
		}
		
		CustomAdapter adapter = new CustomAdapter(getActivity(), rooms, false);
		grid.setAdapter(adapter);
		grid.setOnItemClickListener(this);
	}
	

	@Override
	public void onRefreshStarted(
			View view) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {}
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);
                pull_to_refresh_attacher.setRefreshComplete();
            }
        }.execute();
		
	}


	@Override
	public void onItemClick(
			AdapterView<?> arg0,
			View arg1, int position,
			long arg3) {
		Room clicked_room = (Room) grid.getItemAtPosition(position);
		Intent intent = new Intent (getActivity(), RoomDetailActivity.class);
		intent.putExtra(RoomDetailActivity.ROOM_TYPE, clicked_room.getRoomType());
		intent.putExtra(RoomDetailActivity.ROOM_NUMBER, clicked_room.getRoomNumber());
		startActivity(intent);

		
	}
}
