package com.maher.nowhere.CinemaActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maher.nowhere.CinemaActivity.fragments.CinemaPagerAdapter;
import com.maher.nowhere.CinemaActivity.fragments.EnSalleFragment;
import com.maher.nowhere.CinemaActivity.fragments.ProchainementFragment;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.EnSalle;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.pagerCinema;

public class CinemaActivity extends AppCompatActivity implements EnSalleFragment.OnFragmentInteractionListener,
        ProchainementFragment.OnFragmentInteractionListener {

    private ArrayList<EnSalle> enSalle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);
        setupToolbar();
        dummyData();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayoutCinema);
        tabLayout.addTab(tabLayout.newTab().setText("En Salle"));
        tabLayout.addTab(tabLayout.newTab().setText("Prochainement"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerCinema);
        final CinemaPagerAdapter cinemaPagerAdapter = new CinemaPagerAdapter(getSupportFragmentManager(), 2);
        viewPager.setAdapter(cinemaPagerAdapter);
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
                startActivity(new Intent(CinemaActivity.this, ProfileActivity.class));
            }
        });

    }

    private void dummyData() {
        enSalle = new ArrayList<>();
        enSalle.add(new EnSalle(R.drawable.image,"23","09","2017","UN SAC DE BILLES", "Le Colisée"));
        enSalle.add(new EnSalle(R.drawable.img3,"23","09","2017","BABY DRIVER", "Le RIO"));
        enSalle.add(new EnSalle(R.drawable.img2,"23","09","2017","ASKIM", "JCC"));
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
