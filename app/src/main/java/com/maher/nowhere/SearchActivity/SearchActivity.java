package com.maher.nowhere.SearchActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maher.nowhere.MapActivity.MapPresenter;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.SearchActivity.adapter.SearchAdapter;
import com.maher.nowhere.SearchActivity.adapter.SearchOwnerAdapter;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.mainActivity.adapter.AcceuilAdapter;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Search;
import com.maher.nowhere.providers.EventManager;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchActivity extends AppCompatActivity implements SearchView {

    private ArrayList<Owner> lsearch;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private int lastPosition = -1;
    private Toolbar toolbar;
    private LottieAnimationView lottieAnimationView;
    private String categorie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingAnimation);


        setupToolbar();
        categorie = getIntent().getStringExtra("categorie");

        SearchPresenter searchPresenter = new SearchPresenter(this, this);
        searchPresenter.loadAllPosts(categorie);


        lsearch = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.rv_search);
        lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        SearchOwnerAdapter searchAdapter = new SearchOwnerAdapter(this, lsearch, categorie);
        recyclerView.setAdapter(searchAdapter);

    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException ignore) {
        }


        CircleImageView profile = (CircleImageView) toolbar.findViewById(R.id.toolbarProfileImg);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (lastPosition > -1)
            recyclerView.smoothScrollToPosition(lastPosition);
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
    public void loadAllPosts(ArrayList<Owner> owners) {
        System.out.println("load all owners");

        lsearch = new ArrayList<>();
        lsearch = owners;
        SearchOwnerAdapter searchOwnerAdapter = new SearchOwnerAdapter(this, lsearch, categorie);
        recyclerView.setAdapter(searchOwnerAdapter);
    }

    @Override
    public void loadNoPosts() {
        lsearch = new ArrayList<>();
        SearchOwnerAdapter searchOwnerAdapter = new SearchOwnerAdapter(this, lsearch, categorie);
        recyclerView.setAdapter(searchOwnerAdapter);
    }


}
