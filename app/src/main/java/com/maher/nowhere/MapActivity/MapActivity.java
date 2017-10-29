package com.maher.nowhere.MapActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maher.nowhere.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapView mMapView = (MapView) findViewById(R.id.map);
        MapsInitializer.initialize(this);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(this);

    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(36.8558515, 10.1429342));
        googleMap.addMarker(options);

        MarkerOptions opt = new MarkerOptions();
        opt.position(new LatLng(36.8629936, 10.1659368));
        googleMap.addMarker(opt);

        MarkerOptions opt1 = new MarkerOptions();
        opt1.position(new LatLng(36.8629936, 10.1659368));
        googleMap.addMarker(opt1);

        MarkerOptions opt2 = new MarkerOptions();
        opt2.position(new LatLng(36.8629936, 10.1659368));
        googleMap.addMarker(opt2);

    }
}
