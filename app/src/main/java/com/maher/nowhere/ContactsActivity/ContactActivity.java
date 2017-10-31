package com.maher.nowhere.ContactsActivity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.ContactsActivity.fragments.ContactPagerAdapter;

public class ContactActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);


        setUpToolbar();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Mes Amis"));
        tabLayout.addTab(tabLayout.newTab().setText("Invitations"));
        tabLayout.addTab(tabLayout.newTab().setText("Suggestions"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final ContactPagerAdapter cadapter = new ContactPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(cadapter);
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

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException ignore) {
        }
        TextView title = toolbar.findViewById(R.id.toolbarTitle);
        title.setText("Contacts");

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
