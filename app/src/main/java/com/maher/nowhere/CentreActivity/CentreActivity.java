package com.maher.nowhere.CentreActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maher.nowhere.CentreActivity.fragments.AProposCentreFragment;
import com.maher.nowhere.CentreActivity.fragments.CentrePagerAdapter;
import com.maher.nowhere.CentreActivity.fragments.ProductsFragment;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.ProfileFriendActivity.fragments.photos.PhotosFragment;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Product;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.maher.nowhere.R.id.pagerCentre;

public class CentreActivity extends AppCompatActivity implements AProposCentreFragment.OnFragmentInteractionListener,
        PhotosFragment.OnFragmentInteractionListener,
        ProductsFragment.OnFragmentInteractionListener{

    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centre);
        dummyData();
        setupToolbar();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLCentre);
        tabLayout.addTab(tabLayout.newTab().setText("A propos"));
        tabLayout.addTab(tabLayout.newTab().setText("Photos"));
        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabTextColors(getResources().getColor(R.color.colorGreyText), getResources().getColor(R.color.white));

        final ViewPager viewPager = (ViewPager) findViewById(pagerCentre);
        final CentrePagerAdapter centrePagerAdapter = new CentrePagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(centrePagerAdapter);
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

    private void dummyData() {
        products = new ArrayList<>();
        products.add(new Product(R.drawable.profile_image,"Product","Name","60 DT"));
        products.add(new Product(R.drawable.profile_image,"Product","Name","60 DT"));
        products.add(new Product(R.drawable.profile_image,"Product","Name","60 DT"));
        products.add(new Product(R.drawable.profile_image,"Product","Name","60 DT"));
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
                startActivity(new Intent(CentreActivity.this, ProfileActivity.class));
            }
        });

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
