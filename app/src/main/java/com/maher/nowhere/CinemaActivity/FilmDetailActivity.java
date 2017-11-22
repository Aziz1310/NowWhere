package com.maher.nowhere.CinemaActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.User;
import com.maher.nowhere.reservationActivity.ReservationActivity;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FilmDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView mImageView;
    private ImageView btnIgo,img1;
    private Post post;
    private TextView tvTitle;
    private TextView tvPlace,tvDay,tvMonth,tvYear;
    private TextView tvDate,tvAdresse,tvDescription,tvAprop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);

        mImageView = (ImageView) findViewById(R.id.img1);
        btnIgo = (ImageView) findViewById(R.id.btnIGo);
        tvDescription = (TextView)findViewById(R.id.tvDescriptionFilm);
        tvAprop = (TextView)findViewById(R.id.tvApropFilm);
        tvAdresse = (TextView)findViewById(R.id.tvAdresseLieufilm);
        tvDate = (TextView)findViewById(R.id.tvDateFilm);
        tvTitle = (TextView)findViewById(R.id.tvTitleFilm);
        tvPlace = (TextView)findViewById(R.id.tvLieuFilm);
        tvDay = (TextView)findViewById(R.id.tvDayFilm);
        tvMonth = (TextView)findViewById(R.id.tvMonthFilm);
        tvYear = (TextView)findViewById(R.id.tvYearFilm);

        post=(Post) getIntent().getSerializableExtra("post");
        if(post!=null){
            tvDescription.setText(post.getDescription());
            tvAprop.setText(post.getApropos());
            tvAdresse.setText(post.getOwner().getAdresse());
            tvTitle.setText(post.getName());
            tvPlace.setText(post.getOwner().getNom());
            tvDate.setText("De 23 au 25 Octobre\n "+post.getHeureDispo());
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
        }
        btnIgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FilmDetailActivity.this, ReservationActivity.class);
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
                startActivity(new Intent(FilmDetailActivity.this, ProfileActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
