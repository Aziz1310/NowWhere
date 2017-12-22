package com.maher.nowhere.SearchDetailActivity;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.actions.ReserveIntents;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.User;
import com.maher.nowhere.reservationActivity.ReservationActivity;
import com.maher.nowhere.utiles.BitmapUtils;
import com.maher.nowhere.utiles.ShadowLayout;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchDetailActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMarkerClickListener {


    private static final TimeInterpolator sDecelerator = new DecelerateInterpolator();
    private static final TimeInterpolator sAccelerator = new AccelerateInterpolator();
    private static final int ANIM_DURATION = 500;
    static float sAnimatorScale = 1;

    private BitmapDrawable mBitmapDrawable;
    private ColorMatrix colorizerMatrix = new ColorMatrix();
    ColorDrawable mBackground;
    int mLeftDelta;
    int mTopDelta;
    float mWidthScale;
    float mHeightScale;
    private ImageView mImageView;
    private FrameLayout mTopLevelLayout;
    private ShadowLayout mShadowLayout;
    private int mOriginalOrientation;
    private GoogleMap map;
    private Toolbar toolbar;
    private ImageView btnIgo,img1;
    private Post post;
    private Owner owner;

    private TextView tvTitle;
    private TextView tvPlace,tvDay,tvMonth,tvYear;
    private TextView tvDate,tvAdresse,tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_detail);
        setupToolbar();
        mShadowLayout = (ShadowLayout) findViewById(R.id.shadowLayout);
        mImageView = (ImageView) findViewById(R.id.img1);
        btnIgo = (ImageView) findViewById(R.id.btnIGo);

        tvDescription=(TextView)findViewById(R.id.tvDescription);
        tvAdresse=(TextView)findViewById(R.id.tvAdresse);
        tvDate=(TextView)findViewById(R.id.tvDate);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        tvPlace=(TextView)findViewById(R.id.tvName);
        tvDay=(TextView)findViewById(R.id.tvDay);
        tvMonth=(TextView)findViewById(R.id.tvMonth);
        tvYear=(TextView)findViewById(R.id.tvYear);

        post=(Post) getIntent().getSerializableExtra("post");
        owner=(Owner) getIntent().getSerializableExtra("owner");
        if(post!=null){
            tvDescription.setText(post.getDescription());
            tvAdresse.setText(post.getOwner().getAdresse());
            tvTitle.setText(post.getName());
            tvPlace.setText(post.getOwner().getNom());
            tvDate.setText(String.format("Ouvert\n de %s à %s", post.getHeureDebut(), post.getHeureFin()));
            tvYear.setText(post.getYear());
            tvDay.setText(post.getDayOfWeek());
            tvMonth.setText(post.getMonthNumber());
            Picasso.with(this).
                    load(Uri.parse(post.getUrlImage()))
                    .into(mImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {}

                        @Override
                        public void onError() {
                        }
                    });
        }else if (owner !=null){
            tvDescription.setText(owner.getDescription());
            tvAdresse.setText(owner.getAdresse());
            tvTitle.setText(owner.getNom());
          //  tvPlace.setText(post.getOwner().getNom());
            tvDate.setText(String.format("Ouvert\n de %s à %s", owner.getHeure_overture(), owner.getHeure_fermeture()));
           // tvYear.setText(post.getYear());
           // tvDay.setText(post.getDayOfWeek());
           // tvMonth.setText(post.getMonthNumber());
            Picasso.with(this).
                    load(Uri.parse(owner.getUrlImage()))
                    .into(mImageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {}

                        @Override
                        public void onError() {
                        }
                    });

        }

        MapView mMapView = (MapView) findViewById(R.id.map);
        MapsInitializer.initialize(this);

        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        mMapView.getMapAsync(this);

        btnIgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchDetailActivity.this, ReservationActivity.class);
                intent.putExtra("post",post);
                startActivity(intent);
            }
        });


    }

    private void setupToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException ignore) {
        }

        User user=User.getCurrentUser(this);

        CircleImageView profile = (CircleImageView) toolbar.findViewById(R.id.toolbarProfileImg);
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
                startActivity(new Intent(SearchDetailActivity.this, ProfileActivity.class));
            }
        });

    }



    /**
     * Overriding this method allows us to run our exit animation first, then exiting
     * the activity when it is complete.
     */
    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void finish() {
        super.finish();

        // override transitions to skip the standard window animations
        overridePendingTransition(R.anim.fragment_fade_in, R.anim.fragment_fade_out);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        this.map = googleMap;
        try {
            boolean success = googleMap.setMapStyle(

                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.jsonstyle));

            if (!success) {
                Log.e("vv", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("vv", "Can't find style. Error: ", e);
        }
        BitmapDrawable bitmapdraw1 = (BitmapDrawable) getResources().getDrawable(R.drawable.icon_map_coffe);
        Bitmap b = bitmapdraw1.getBitmap();
        final Bitmap smallMarker1 = Bitmap.createScaledBitmap(b, 40, 60, false);

        MarkerOptions options = new MarkerOptions();
        if(post!=null){
            options.position(new LatLng(post.getOwner().getLatitude(), post.getOwner().getLongitude()))
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
                    .anchor(0.5f, 1);

            googleMap.addMarker(options);

        }else if(owner!=null){
            options.position(new LatLng(owner.getLatitude(), owner.getLongitude()))
                    .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1))
                    .anchor(0.5f, 1);

            googleMap.addMarker(options);

        }

        googleMap.getUiSettings().setScrollGesturesEnabled(false);

       // System.out.println("maher lat long " +post.getOwner().getLatitude()+" "+ post.getOwner().getLongitude());

        if(post!=null)
        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(
                        new LatLng(post.getOwner().getLatitude(), post.getOwner().getLongitude()), 12));
        else  if(owner!=null)
            googleMap.animateCamera(
                    CameraUpdateFactory.newLatLngZoom(
                            new LatLng(owner.getLatitude(), owner.getLongitude()), 12));

}
}
