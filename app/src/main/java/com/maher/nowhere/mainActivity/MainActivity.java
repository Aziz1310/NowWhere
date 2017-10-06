package com.maher.nowhere.mainActivity;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.maher.nowhere.Accueil;
import com.maher.nowhere.Categories;
import com.maher.nowhere.R;
import com.maher.nowhere.ViewActivity;
import com.maher.nowhere.Weeklik;
import com.maher.nowhere.mainActivity.Adapter.ScreenSlidePagerAdapter;

public class MainActivity extends AppCompatActivity implements Weeklik.OnFragmentInteractionListener,
        Accueil.OnFragmentInteractionListener
        ,Categories.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Weeklik"));
        tabLayout.addTab(tabLayout.newTab().setText("Accueil"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final ViewActivity adapter = new ViewActivity(getSupportFragmentManager(),3);
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
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
