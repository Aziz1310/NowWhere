package com.maher.nowhere.reservationActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.chootdev.csnackbar.Duration;
import com.chootdev.csnackbar.Snackbar;
import com.chootdev.csnackbar.Type;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Reservation;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.EventManager;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReservationActivity extends AppCompatActivity implements ReservationActivityView {


    private Post post;

    private TextView tvNumPersonne;
    private TextView tvNumH;
    private LottieAnimationView lottieAnimationView;
    private ImageView img1;
    private AppCompatButton btnPlusPersonne, btnMinusPersonne, btnPlusH, btnMinusH, btnReserver;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        setupToolbar();


        TextView tvAdresse = (TextView) findViewById(R.id.tvAdresse);
        TextView tvDate = (TextView) findViewById(R.id.tvDate);
        TextView tvTitle = (TextView) findViewById(R.id.tvTitle);
        TextView tvPlace = (TextView) findViewById(R.id.tvName);
        TextView tvDay = (TextView) findViewById(R.id.tvDay);
        TextView tvMonth = (TextView) findViewById(R.id.tvMonth);
        TextView tvYear = (TextView) findViewById(R.id.tvYear);
        btnMinusH = (AppCompatButton) findViewById(R.id.btnMinusH);
        btnMinusPersonne = (AppCompatButton) findViewById(R.id.btnMinus);
        btnPlusH = (AppCompatButton) findViewById(R.id.btnPlusH);
        btnPlusPersonne = (AppCompatButton) findViewById(R.id.btnPlus);
        tvNumH = (TextView) findViewById(R.id.tvTime);
        tvNumPersonne = (TextView) findViewById(R.id.tvPersNumber);
        btnReserver = (AppCompatButton) findViewById(R.id.btnReserver);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingAnimation);
        img1 = (ImageView) findViewById(R.id.img1);

        post = (Post) getIntent().getSerializableExtra("post");


        tvNumH.setText(post.getHeureDebut());
        tvAdresse.setText(post.getOwner().getAdresse());
        tvTitle.setText(post.getName());
        tvPlace.setText(post.getOwner().getNom());
        tvDate.setText("Ouvert\n de " + post.getHeureDebut() + " à " + post.getHeureFin());
        tvYear.setText(post.getYear());
        tvDay.setText(post.getDayOfWeek());
        tvMonth.setText(post.getMonthNumber());
        Picasso.with(this).
                load(Uri.parse(post.getUrlImage()))
                .into(img1, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                    }
                });
        ReservationPresenter reservationPresenter = new ReservationPresenter(this, this);
        buttonsClick(reservationPresenter);
    }

    private void buttonsClick(final ReservationPresenter reservationPresenter) {
        btnMinusH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationPresenter.decrementHour(tvNumH.getText().toString(),post.getHeureDebut(),post.getHeureFin());
            }
        });
        btnMinusPersonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationPresenter.decrementNbrPersonnes(tvNumPersonne.getText().toString());
            }
        });
        btnPlusPersonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationPresenter.incrementNbrPersonnes(tvNumPersonne.getText().toString());

            }
        });
        btnPlusH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reservationPresenter.incrementHour(tvNumH.getText().toString(),post.getHeureDebut(),post.getHeureFin());
            }
        });

        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                reservationPresenter.reserver(post.getId(), tvNumPersonne.getText().toString()
                        , tvNumH.getText().toString());
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
                startActivity(new Intent(ReservationActivity.this, ProfileActivity.class));
            }
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void hideComponents() {
        btnReserver.setVisibility(View.GONE);
        btnPlusPersonne.setVisibility(View.INVISIBLE);
        btnMinusPersonne.setVisibility(View.INVISIBLE);
        btnPlusH.setVisibility(View.INVISIBLE);
        btnMinusH.setVisibility(View.INVISIBLE);
    }


    @Override
    public void getHour(String hour) {
        tvNumH.setText(hour);
    }

    @Override
    public void getNbrPersonnes(String nbr) {
        tvNumPersonne.setText(nbr);
    }

    @Override
    public void showProgress() {
        btnReserver.setClickable(false);
        lottieAnimationView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        btnReserver.setClickable(true);
        lottieAnimationView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void success() {
        Snackbar.with(ReservationActivity.this, null);
        Snackbar.type(Type.SUCCESS);
        Snackbar.message("success");
        Snackbar.duration(Duration.SHORT);
        Snackbar.show();
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        onBackPressed();
                    }
                },
                1000);
    }

    @Override
    public void error() {
        Snackbar.with(ReservationActivity.this, null);
        Snackbar.type(Type.ERROR);
        Snackbar.message("vous avez deja reserver");
        Snackbar.duration(Duration.SHORT);
        Snackbar.show();
    }

}
