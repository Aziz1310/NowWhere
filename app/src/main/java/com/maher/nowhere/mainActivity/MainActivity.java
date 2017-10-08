package com.maher.nowhere.mainActivity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.maher.nowhere.mainActivity.fragments.Accueil;
import com.maher.nowhere.mainActivity.fragments.Categories;
import com.maher.nowhere.R;
import com.maher.nowhere.mainActivity.fragments.MainActivityFragmentStatePagerAdapter;
import com.maher.nowhere.mainActivity.fragments.Weeklik;
import com.maher.nowhere.model.Categ;
import com.maher.nowhere.model.Post;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Weeklik.OnFragmentInteractionListener,
        Accueil.OnFragmentInteractionListener
        ,Categories.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ArrayList<Post> posts;
    ArrayList<Categ> post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dummyDate();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setupDrawerToggle();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Weeklik"));
        tabLayout.addTab(tabLayout.newTab().setText("Accueil"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final MainActivityFragmentStatePagerAdapter adapter = new MainActivityFragmentStatePagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

    }

    private void dummyDate() {
        posts = new ArrayList<>();
        posts.add(new Post(R.drawable.image));
        posts.add(new Post(R.drawable.signup_image));
        posts.add(new Post(R.drawable.image));
        posts.add(new Post(R.drawable.signup_image));
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
        drawerLayout.setBackgroundColor(getResources().getColor(R.color.white));

    }

    class MainActivityFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

        int numOfTabs;

        public MainActivityFragmentStatePagerAdapter(FragmentManager fm, int NumberOfTabs) {
            super(fm);
            this.numOfTabs = NumberOfTabs;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    Weeklik weeklik = new Weeklik();
                    return Weeklik.newInstance(posts);
                case 1:
                    Accueil accueil = new Accueil();
                    return accueil;
                case 2:
                    Categories categories = new Categories();
                    return categories;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return numOfTabs;
        }

    }
}
