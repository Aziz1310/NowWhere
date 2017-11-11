package com.maher.nowhere.mainActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.mainActivity.fragments.AccueilFragment;
import com.maher.nowhere.mainActivity.fragments.CategoriesFragment;
import com.maher.nowhere.R;
import com.maher.nowhere.mainActivity.fragments.WeeklikFragment;
import com.maher.nowhere.model.Categorie;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.SwipeViewPager;
import com.maher.nowhere.utiles.Urls;
import com.maher.nowhere.utiles.Utiles;
import com.mindorks.placeholderview.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements WeeklikFragment.OnFragmentInteractionListener,
        AccueilFragment.OnFragmentInteractionListener
        ,CategoriesFragment.OnFragmentInteractionListener {

    private DrawerLayout drawerLayout;
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ArrayList<Post> posts;
    ArrayList<Categorie> post;
    SwipeViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Date date= new Utiles().parseDate("23/11/2017 02:00");
        System.out.println(date.toString());

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        setupToolbar();
        setupDrawerToggle();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Weeklik"));
        tabLayout.addTab(tabLayout.newTab().setText("Accueil"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));


        viewPager = (SwipeViewPager) findViewById(R.id.pager);
        viewPager.setPagingEnabled(false);
        viewPager.setOffscreenPageLimit(3);
        final MainActivityFragmentStatePagerAdapter adapter = new MainActivityFragmentStatePagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition()==0)
                    viewPager.setPagingEnabled(false);
                else
                    viewPager.setPagingEnabled(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (NullPointerException ignore){}
        User user=User.getCurrentUser(this);


        CircleImageView profile=(CircleImageView)toolbar.findViewById(R.id.toolbarProfileImg);
        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER+user.getImage()))
                .into(profile, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onError() {
                    }
                });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
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
                    return WeeklikFragment.newInstance(posts);
                case 1:
                    AccueilFragment accueil = new AccueilFragment();
                    return accueil;
                case 2:
                    CategoriesFragment categories = new CategoriesFragment();
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
