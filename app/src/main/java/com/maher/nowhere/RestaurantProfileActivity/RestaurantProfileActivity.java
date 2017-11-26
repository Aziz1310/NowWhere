package com.maher.nowhere.RestaurantProfileActivity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.fragments.MenuFragment;
import com.maher.nowhere.RestaurantProfileActivity.fragments.RestaurantPagerAdapter;
import com.maher.nowhere.model.MenuH;
import com.maher.nowhere.model.MenuV;

import java.util.ArrayList;

import static com.maher.nowhere.R.id.pagerRestaut;

public class RestaurantProfileActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener,
        com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment.OnFragmentInteractionListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        setUpToolbar();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLRestaut);
        tabLayout.addTab(tabLayout.newTab().setText("A propos"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("FeedBack"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerRestaut);
        final RestaurantPagerAdapter restaurantPagerAdapter = new RestaurantPagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(restaurantPagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

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

    private void setUpToolbar() {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

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
}
