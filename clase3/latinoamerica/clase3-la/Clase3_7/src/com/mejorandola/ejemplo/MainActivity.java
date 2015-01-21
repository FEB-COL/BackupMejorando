package com.mejorandola.ejemplo;

import uk.co.senab.actionbarpulltorefresh.library.PullToRefreshAttacher;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.mejorandola.ejemplo.fragments.MainFragment;
import com.mejorandola.ejemplo.fragments.TermsFragment;

public class MainActivity extends FragmentActivity implements 
							      ListView.OnItemClickListener{

		private ListView drawer_list;
		private DrawerLayout drawer_layout;
		private ActionBarDrawerToggle drawer_toggle;
		private PullToRefreshAttacher pull_to_refresh_attacher;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);
			
			pull_to_refresh_attacher = PullToRefreshAttacher.get(this);			
			drawer_list = (ListView) findViewById(R.id.left_drawer);
	        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        
	        ArrayAdapter<String> drawer_adapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, getResources().getStringArray(R.array.array_drawer_options));
	        drawer_list.setAdapter(drawer_adapter);
	        drawer_list.setOnItemClickListener(this);
	        drawer_toggle = new ActionBarDrawerToggle(this, 
	        										     drawer_layout,
	        										     R.drawable.ic_drawer, 
	        										     R.string.drawer_open, 
	        										     R.string.drawer_close) {
								public void onDrawerClosed(View view) {
									invalidateOptionsMenu();
								}

								public void onDrawerOpened(View drawerView) {
									invalidateOptionsMenu();
								}
	        };	        
	        
	        drawer_layout.setDrawerListener(drawer_toggle);
	        
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	        selectItem(0);
		}
		
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        drawer_toggle.syncState();
	    }

	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        drawer_toggle.onConfigurationChanged(newConfig);
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        if (drawer_toggle.onOptionsItemSelected(item)) {
	          return true;
	        }

	        return super.onOptionsItemSelected(item);
	    }		
		
		public void selectItem(int position) {
			Fragment f;
			if (position == 0) {
				f = new MainFragment();
			} else {
				f = new TermsFragment();
			}
			
		    FragmentManager fragmentManager = getSupportFragmentManager();
		    fragmentManager.beginTransaction()
		                   .replace(R.id.main_content, f)
		                   .commit();
		    drawer_list.setItemChecked(position, true);	    
		    setTitle(drawer_list.getItemAtPosition(position).toString());
		    drawer_layout.closeDrawer(drawer_list);
			
		}

		public PullToRefreshAttacher getAttacher() {
			return pull_to_refresh_attacher;
		}


		@Override
		public void onItemClick(
				AdapterView<?> arg0,
				View arg1, int arg2,
				long arg3) {
			selectItem(arg2);
			
		}		
}
