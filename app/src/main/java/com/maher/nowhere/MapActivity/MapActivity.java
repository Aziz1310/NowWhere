package com.maher.nowhere.MapActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maher.nowhere.R;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        setUpToolbar();

        MapView mMapView = (MapView) findViewById(R.id.map);
        MapsInitializer.initialize(this);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(this);


    }
    private void setUpToolbar(){
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView title=toolbar.findViewById(R.id.toolbarTitle);
        title.setText("Map");
        ImageView btnBack=toolbar.findViewById(R.id.toolbarBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Intent intent=new Intent(this,DetailMapActivity.class);
        startActivity(intent);
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.icon_map_coffe);
        Bitmap b = bitmapdraw1.getBitmap();
        final Bitmap smallMarker1 = Bitmap.createScaledBitmap(b, 70, 100, false);

        MarkerOptions options = new MarkerOptions();
        options.position(new LatLng(36.8558515, 10.1429342))
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
                .anchor(0.5f,1);
        googleMap.addMarker(options);

        MarkerOptions opt = new MarkerOptions();
        opt.position(new LatLng(36.8629936, 10.1659368))
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
                .anchor(0.5f,1);
        googleMap.addMarker(opt);

        MarkerOptions opt1 = new MarkerOptions();
        opt1.position(new LatLng(36.8629800, 10.1659399))
        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
        .anchor(0.5f,1);
        googleMap.addMarker(opt1);

        MarkerOptions opt2 = new MarkerOptions();
        opt2.position(new LatLng(36.8629936, 10.1659368)).icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        googleMap.addMarker(opt2);


        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(36.8558515, 10.1429342), 12));

        googleMap.setOnMarkerClickListener(this);

    }
}
