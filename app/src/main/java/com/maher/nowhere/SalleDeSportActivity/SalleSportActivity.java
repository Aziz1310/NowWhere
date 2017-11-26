package com.maher.nowhere.SalleDeSportActivity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.maher.nowhere.R;
import com.maher.nowhere.SalleDeSportActivity.fragments.AProposSalleFragment;
import com.maher.nowhere.SalleDeSportActivity.fragments.SalleSportPagerAdapter;
import com.maher.nowhere.SalleDeSportActivity.fragments.TrynowFragment;

import static com.maher.nowhere.R.id.pagerSalle;

public class SalleSportActivity extends AppCompatActivity implements AProposSalleFragment.OnFragmentInteractionListener,
        com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment.OnFragmentInteractionListener,
        TrynowFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle_sport);
        setUpToolbar();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLSalle);
        tabLayout.addTab(tabLayout.newTab().setText("A propos"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Trynow"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerSalle);
        final SalleSportPagerAdapter salleSportPagerAdapter = new SalleSportPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(salleSportPagerAdapter);
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
