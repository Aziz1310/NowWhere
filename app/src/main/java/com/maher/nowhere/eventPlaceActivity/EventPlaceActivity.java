package com.maher.nowhere.eventPlaceActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maher.nowhere.CinemaActivity.fragments.CinemaPagerAdapter;
import com.maher.nowhere.CinemaActivity.fragments.EnSalleFragment;
import com.maher.nowhere.CinemaActivity.fragments.ProchainementFragment;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.eventPlaceActivity.fragments.EventPlacePagerAdapter;
import com.maher.nowhere.model.EnSalle;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.pagerCinema;

public class EventPlaceActivity extends AppCompatActivity implements
        ProchainementFragment.OnFragmentInteractionListener {

    private ArrayList<EnSalle> enSalle;
    private String categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        setupToolbar();
        categorie = getIntent().getStringExtra("categorie");


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutCinema);
        tabLayout.addTab(tabLayout.newTab().setText("Places"));
        tabLayout.addTab(tabLayout.newTab().setText("Evénements"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerCinema);
        final EventPlacePagerAdapter eventPlacePagerAdapter = new EventPlacePagerAdapter(getSupportFragmentManager(), 2,categorie);
        viewPager.setAdapter(eventPlacePagerAdapter);
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

    private void setupToolbar() {
        User user=User.getCurrentUser(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CircleImageView profile = (CircleImageView) toolbar.findViewById(R.id.toolbarProfileImg);
        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER + user.getImage())).resize(50, 50)
                .into(profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EventPlaceActivity.this, ProfileActivity.class));
            }
        });

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
