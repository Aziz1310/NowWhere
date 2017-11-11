package com.maher.nowhere.ProfileFriendActivity;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.maher.nowhere.ProfileFriendActivity.fragments.AmisFriendFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.MurFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.ProfileFriendPagerAdapter;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Mur;

import java.util.ArrayList;

import static com.maher.nowhere.R.id.pagerFriendProfile;

public class ProfileFriendActivity extends AppCompatActivity implements MurFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener, AmisFriendFragment.OnFragmentInteractionListener {

    ArrayList<Mur> murs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_friend);
        setUpToolbar();
        collapsingToolbar();
        dummyData();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.layoutTab);
        tabLayout.addTab(tabLayout.newTab().setText("Mur"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Amis"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerFriendProfile);
        final ProfileFriendPagerAdapter profileFriendPagerAdapter = new ProfileFriendPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(profileFriendPagerAdapter);
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
    private void collapsingToolbar(){
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("Zeinab Azzabi");
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private void dummyData() {
        murs = new ArrayList<>();
        murs.add(new Mur(R.drawable.image));
        murs.add(new Mur(R.drawable.signup_image));
        murs.add(new Mur(R.drawable.gg));
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
