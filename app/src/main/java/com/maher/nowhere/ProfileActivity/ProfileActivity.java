package com.maher.nowhere.ProfileActivity;

import android.app.Fragment;
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

import com.maher.nowhere.ProfileActivity.fragments.favoris.FavorisFragment;
import com.maher.nowhere.ProfileActivity.fragments.page.PageFragment;
import com.maher.nowhere.ProfileActivity.fragments.ProfilePagerAdapter;
import com.maher.nowhere.ProfileActivity.fragments.reservation.ReservationsFragment;
import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.R;
import com.maher.nowhere.Settings.SettingsActivity;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.pagerProfile;
import static com.maher.nowhere.R.id.viewpager;


public class ProfileActivity extends AppCompatActivity implements PageFragment.OnFragmentInteractionListener,
        FavorisFragment.OnFragmentInteractionListener,ReservationsFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener{

     ViewPager viewPager;



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

         viewPager = (ViewPager) findViewById(pagerProfile);
         viewPager.setOffscreenPageLimit(4);




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
        final User user=User.getCurrentUser(this);
        final CircleImageView profileImage;
        TextView tvUserName,tvUserAdresse;
        profileImage=(CircleImageView)findViewById(R.id.imageView);
        tvUserName=(TextView)findViewById(R.id.nomProfile);
        tvUserAdresse=(TextView)findViewById(R.id.textView);
        final ImageView img=findViewById(R.id.img);

        Picasso.with(this).load(Uri.parse(Urls.IMG_URL_USER_COVER +user.getCoverPhoto()))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(img, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(ProfileActivity.this).load(Uri.parse(Urls.IMG_URL_USER_COVER +user.getCoverPhoto()))
                                .into(img);
                    }
                    @Override
                    public void onError() {
                    }
                });

        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER+user.getImage())).resize(100,100)
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(profileImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Picasso.with(ProfileActivity.this).load(Uri.parse(Urls.IMG_URL_USER +user.getImage()))
                                .into(profileImage);
                    }
                    @Override
                    public void onError() {
                    }
                });
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int index = viewPager.getCurrentItem();
        ProfilePagerAdapter adapter = ((ProfilePagerAdapter)viewPager.getAdapter());
        PageFragment fragment = (PageFragment) adapter.getItem(index);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
