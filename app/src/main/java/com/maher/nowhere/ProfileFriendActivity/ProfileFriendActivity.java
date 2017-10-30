package com.maher.nowhere.ProfileFriendActivity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maher.nowhere.ProfileFriendActivity.fragments.AmisFriendFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.MurFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.ProfileFriendPagerAdapter;
import com.maher.nowhere.R;

import static com.maher.nowhere.R.id.pagerFriendProfile;

public class ProfileFriendActivity extends AppCompatActivity implements MurFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener, AmisFriendFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_friend);

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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
