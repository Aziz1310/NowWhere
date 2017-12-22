package com.maher.nowhere.RestaurantProfileActivity;

import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.fragments.menu.MenuFragment;
import com.maher.nowhere.RestaurantProfileActivity.fragments.RestaurantPagerAdapter;
import com.maher.nowhere.model.Owner;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.pagerRestaut;

public class RestaurantProfileActivity extends AppCompatActivity implements MenuFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener{
    private Owner owner;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_profile);
        setUpToolbar();
        owner=(Owner) getIntent().getSerializableExtra("owner");
        collapsingToolbar();

        TextView nomRestaut=findViewById(R.id.nomRestaut);
        CircleImageView imgRestaut=findViewById(R.id.imgRestaut);
        ImageView imgCover=findViewById(R.id.imgCover);
        TextView lieuRestaut=findViewById(R.id.lieuRestaut);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLRestaut);
        tabLayout.addTab(tabLayout.newTab().setText("A propos"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Menu"));
        tabLayout.addTab(tabLayout.newTab().setText("FeedBack"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerRestaut);
        final RestaurantPagerAdapter restaurantPagerAdapter = new RestaurantPagerAdapter(getSupportFragmentManager(), 4,owner);
        viewPager.setAdapter(restaurantPagerAdapter);
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

        if(owner !=null){

            nomRestaut.setText(owner.getNom());
            lieuRestaut.setText(owner.getAdresse());

            Picasso.with(this).
                    load(Uri.parse(owner.getCouverture()))
                    .into(imgCover, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {}

                        @Override
                        public void onError() {
                        }
                    });
            Picasso.with(this).
                    load(Uri.parse(owner.getUrlImage()))
                    .into(imgRestaut, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {}

                        @Override
                        public void onError() {
                        }
                    });




        }

    }

    private void setUpToolbar() {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    private void collapsingToolbar(){
        if (owner!=null){
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbarLayout.setTitle(owner.getNom());
            collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
            collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        }

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
