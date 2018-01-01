package com.maher.nowhere.CentreActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.CentreActivity.fragments.AProposCentreFragment;
import com.maher.nowhere.CentreActivity.fragments.CentrePagerAdapter;
import com.maher.nowhere.CentreActivity.fragments.products.ProductsFragment;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.nom;
import static com.maher.nowhere.R.id.pagerCentre;

public class CentreActivity extends AppCompatActivity implements AProposCentreFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener,
        ProductsFragment.OnFragmentInteractionListener{

    private ArrayList<Product> products;
    Owner owner;
    String categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        setUpToolbar();
        owner=(Owner) getIntent().getSerializableExtra("owner");
        categorie=getIntent().getStringExtra("categorie");
        collapsingToolbar();

        TextView nomCentre = findViewById(R.id.nomCentre);
        CircleImageView imgCentre = findViewById(R.id.imgCentre);
        ImageView imgCover = findViewById(R.id.imgCentre);
        TextView lieuCentre = findViewById(R.id.lieuCentre);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLCentre);
        tabLayout.addTab(tabLayout.newTab().setText("A propos"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerCentre);
        viewPager.setOffscreenPageLimit(3);
        final CentrePagerAdapter centrePagerAdapter = new CentrePagerAdapter(getSupportFragmentManager(), 3,owner,categorie);
        viewPager.setAdapter(centrePagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        if (owner != null) {

            nomCentre.setText(owner.getNom());
            lieuCentre.setText(owner.getAdresse());

            Picasso.with(this).
                    load(Uri.parse(owner.getCouverture()))
                    .into(imgCover, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });
            Picasso.with(this).
                    load(Uri.parse(owner.getUrlImage()))
                    .into(imgCentre, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });

        }
    }

    private void setUpToolbar() {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }
    private void collapsingToolbar() {
        if (owner != null) {
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitle(owner.getNom());
            collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
