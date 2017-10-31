package com.maher.nowhere.ProfileActivity;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.maher.nowhere.ProfileActivity.fragments.FavorisFragment;
import com.maher.nowhere.ProfileActivity.fragments.PageFragment;
import com.maher.nowhere.ProfileActivity.fragments.ProfilePagerAdapter;
import com.maher.nowhere.ProfileActivity.fragments.ReservationsFragment;
import com.maher.nowhere.R;

import static com.maher.nowhere.R.id.pagerProfile;


public class ProfileActivity extends AppCompatActivity implements PageFragment.OnFragmentInteractionListener,
        FavorisFragment.OnFragmentInteractionListener,ReservationsFragment.OnFragmentInteractionListener,
        com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment.OnFragmentInteractionListener{

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setUpToolbar();
        collapsingToolbar();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabbLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Ma page"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Mes réservations"));
        tabLayout.addTab(tabLayout.newTab().setText("Favoris"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerProfile);
        final ProfilePagerAdapter profileAdapter = new ProfilePagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(profileAdapter);
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
    }
    private void setUpToolbar(){
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }
    private void collapsingToolbar(){
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Zeinab Azzabi");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
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
