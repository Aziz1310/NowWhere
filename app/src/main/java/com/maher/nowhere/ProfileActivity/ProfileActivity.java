package com.maher.nowhere.ProfileActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ProfileActivity.fragments.FavorisFragment;
import com.maher.nowhere.ProfileActivity.fragments.PageFragment;
import com.maher.nowhere.ProfileActivity.fragments.ProfilePagerAdapter;
import com.maher.nowhere.ProfileActivity.fragments.ReservationsFragment;
import com.maher.nowhere.R;
import com.maher.nowhere.Settings.SettingsActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.pagerProfile;


public class ProfileActivity extends AppCompatActivity implements PageFragment.OnFragmentInteractionListener,
        FavorisFragment.OnFragmentInteractionListener,ReservationsFragment.OnFragmentInteractionListener,
        com.maher.nowhere.ProfileFriendActivity.fragments.PhotosFragment.OnFragmentInteractionListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        setUpToolbar();
        collapsingToolbar();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabbLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Ma page"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Mes réservations"));
        tabLayout.addTab(tabLayout.newTab().setText("Favoris"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerProfile);
        final ProfilePagerAdapter profileAdapter = new ProfilePagerAdapter(getSupportFragmentManager(), 4);
        viewPager.setAdapter(profileAdapter);
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

        getUserInfo();
        ImageView btnSettings=findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, SettingsActivity.class));
            }
        });


    }
    private void setUpToolbar(){
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }
    private void collapsingToolbar(){
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(User.getCurrentUser(this).getName());
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
    }

    private void getUserInfo(){
        User user=User.getCurrentUser(this);
        CircleImageView profileImage;
        TextView tvUserName,tvUserAdresse;
        profileImage=(CircleImageView)findViewById(R.id.imageView);
        tvUserName=(TextView)findViewById(R.id.nomProfile);
        tvUserAdresse=(TextView)findViewById(R.id.textView);

        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER+user.getImage())).resize(100,100)
                .into(profileImage);
        tvUserName.setText(user.getName());


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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
