package il.ac.shenkar.cadan;



import il.ac.shenkar.cadan.PrefsFragment.OnPreferenceSelectedListener;
import il.ac.shenkar.common.CampusInConstant;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CameraPositionCreator;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;
import com.parse.ParseFacebookUtils;

import android.os.Bundle;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.*;

import android.view.Menu;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

public class Main extends Activity  implements OnPreferenceSelectedListener {
	FlyOutContainer root;
	CameraPosition lastPos=new CameraPosition(new LatLng(0, 0),2,2,2);
	GoogleMap map=null;
	  static final LatLng HAMBURG = new LatLng(20,25);
	  static final LatLng KIEL = new LatLng(15, 10);
	  FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Parse.initialize(this, "3kRz2kNhNu5XxVs3mI4o3LfT1ySuQDhKM4I6EblE",
				"UmGc3flrvIervInFbzoqGxVKapErnd9PKnXy4uMC");
		ParseFacebookUtils.initialize("635010643194002");
		this.root = (FlyOutContainer) this.getLayoutInflater().inflate(R.layout.main, null);
		
		this.setContentView(root);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
		        .getMap();
		map.setMapType(map.MAP_TYPE_NONE);
		
		    Marker hamburg = map.addMarker(new MarkerOptions().position(HAMBURG)
		        .title("Jacob Amsalem"));
		    Marker kiel = map.addMarker(new MarkerOptions()
		        .position(KIEL)
		        .title("Cadan Ojalvo")
		        .snippet("My status at facebook"));
		    
		 
		    BitmapDescriptor image = BitmapDescriptorFactory.fromResource(R.drawable.shenkarmap_1); // get an image.
		    LatLngBounds bounds = new LatLngBounds(new LatLng(0, 0),new LatLng(40, 50)); // get a bounds
		    // Adds a ground overlay with 50% transparency.
		    GroundOverlay groundOverlay = map.addGroundOverlay(new GroundOverlayOptions()
		        .image(image)
		        .positionFromBounds(bounds)
		        .transparency((float) 0.1));
		    UiSettings us=map.getUiSettings();
		    //us.setScrollGesturesEnabled(false);
		    // Move the camera instantly to hamburg with a zoom of 15.
		    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(20, 25), 15));
		    // Zoom in, animating the camera.
		    map.animateCamera(CameraUpdateFactory.zoomTo(2), 2000, null);
		    map.setOnCameraChangeListener(new OnCameraChangeListener() {	
				@Override
				public void onCameraChange(CameraPosition position) {
					if(position.target.latitude<0||position.target.latitude>40||position.target.longitude<0||position.target.longitude>50)
					{
						map.moveCamera(CameraUpdateFactory.newCameraPosition(lastPos));
					}
					else
					{
						lastPos=position;
					}
					// TODO Auto-generated method stub
					
				}
			});
		    
		    // Get the SearchView and set the searchable configuration
		    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		    SearchView searchView = (SearchView) findViewById(R.id.searchView1);
		    SearchableInfo searchInfo = searchManager.getSearchableInfo(getComponentName());
		    if(searchInfo==null)
		    {
		    	Toast.makeText(getApplicationContext(), "Search info is null",500).show();
		    }
		    searchView.setSearchableInfo(searchInfo);
		    searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
			map.setOnMarkerClickListener(new OnMarkerClickListener() {
				
				@Override
				public boolean onMarkerClick(Marker marker) {
					// TODO Auto-generated method stub
					
					return false;
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void toggleMenu(View v){
		this.root.toggleMenu();
	}

	@Override
	public void onPreferenceSelected(String selected) 
	{
		if(selected.equals(CampusInConstant.SETTINGS_EVENTS))
		{
			startActivity(new Intent(this,EventsActivity.class));
			return;
		}
		
		
	}
}
