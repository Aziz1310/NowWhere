package com.maher.nowhere.MapActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.providers.EventManager;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener,
        com.maher.nowhere.MapActivity.MapView {


    private LottieAnimationView lottieAnimationView;
    GoogleMap map;
    private String categorie;
    private ArrayList<Owner> owners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingAnimation);
        owners = new ArrayList<>();

        categorie = getIntent().getStringExtra("categorie");
        System.out.println(categorie + " categorie");

        setUpToolbar();

        MapView mMapView = (MapView) findViewById(R.id.map);
        MapsInitializer.initialize(this);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(this);

        MapPresenter mapPresenter = new MapPresenter(this, this);
        mapPresenter.loadAllPosts(36.7949999, 10.0732374, categorie, 200);


    }

    private void setUpToolbar() {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException ignore) {
        }
        TextView title = toolbar.findViewById(R.id.toolbarTitle);
        title.setText("Map");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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

        Intent intent = new Intent(this, DetailMapActivity.class);
        Owner owner = new Owner();
        owner.setLongitude(marker.getPosition().longitude);
        owner.setLatitude(marker.getPosition().latitude);

        for (Owner ow : owners) {
            if (ow.getLatitude() == owner.getLatitude() && ow.getLongitude() == owner.getLongitude())
            {
                intent.putExtra("owner", ow);
                intent.putExtra("categorie", categorie);
                System.out.println("owner equals ow");
            }

        }

        startActivity(intent);

        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.icon_map_coffe);
        Bitmap b = bitmapdraw1.getBitmap();
        final Bitmap smallMarker1 = Bitmap.createScaledBitmap(b, 40, 60, false);


        try {
            boolean success = googleMap.setMapStyle(

                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.jsonstyle));

            if (!success) {
                Log.e("vv", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("vv", "Can't find style. Error: ", e);
        }

    }

    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.INVISIBLE);

    }


    @Override
    public void networkError() {

    }

    @Override
    public void loadAllPosts(ArrayList<Owner> owners, int mapDrawable) {
        this.owners = owners;
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(mapDrawable);
        Bitmap b = bitmapdraw1.getBitmap();
        final Bitmap smallMarker1 = Bitmap.createScaledBitmap(b, 40, 60, false);

        for (Owner owner : owners) {
            MarkerOptions options = new MarkerOptions();
            options.position(new LatLng(owner.getLatitude(), owner.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
                    .anchor(0.5f, 1);

            map.addMarker(options);

            map.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            new LatLng(owner.getLatitude(), owner.getLongitude()), 12));


        }
        map.setOnMarkerClickListener(this);
    }

    @Override
    public void loadNoPosts() {

    }

}
