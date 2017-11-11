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
import com.maher.nowhere.LoginActivity;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.callbaks.VolleyCallback;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Reservation;
import com.maher.nowhere.providers.EventManager;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReservationActivity extends AppCompatActivity {

    private  Toolbar toolbar;

    private Post post;

    private TextView tvTitle,tvNumPersonne,tvNumH;
    private TextView tvPlace,tvDay,tvMonth,tvYear;
    private TextView tvDate,tvAdresse;
    private LottieAnimationView lottieAnimationView;
    ImageView img1;

    private AppCompatButton btnPlusPersonne,btnMinusPersonne,btnPlusH,btnMinusH,btnReserver;
    private EventManager eventManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        setupToolbar();
        eventManager=new EventManager(this);


        tvAdresse=(TextView)findViewById(R.id.tvAdresse);
        tvDate=(TextView)findViewById(R.id.tvDate);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        tvPlace=(TextView)findViewById(R.id.tvName);
        tvDay=(TextView)findViewById(R.id.tvDay);
        tvMonth=(TextView)findViewById(R.id.tvMonth);
        tvYear=(TextView)findViewById(R.id.tvYear);
        btnMinusH=(AppCompatButton)findViewById(R.id.btnMinusH);
        btnMinusPersonne=(AppCompatButton)findViewById(R.id.btnMinus);
        btnPlusH=(AppCompatButton)findViewById(R.id.btnPlusH);
        btnPlusPersonne=(AppCompatButton)findViewById(R.id.btnPlus);
        tvNumH=(TextView)findViewById(R.id.tvTime);
        tvNumPersonne=(TextView)findViewById(R.id.tvPersNumber);
        btnReserver=(AppCompatButton)findViewById(R.id.btnReserver);
        lottieAnimationView=(LottieAnimationView)findViewById(R.id.loadingAnimation);
        img1=(ImageView)findViewById(R.id.img1);

        post=(Post) getIntent().getSerializableExtra("post");

        tvAdresse.setText(post.getOwner().getAdresse());
        tvTitle.setText(post.getName());
        tvPlace.setText(post.getOwner().getNom());
        tvDate.setText("Ouvert\n de "+post.getHeureDebut()+" à "+post.getHeureFin());
        // tvDay.setText(post.getDay());
        //tvMonth.setText(post.getDay());
        Picasso.with(this).
                load(Uri.parse(post.getUrlImage()))
                .into(img1, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {}

                    @Override
                    public void onError() {
                    }
                });
        buttonsClick();


    }

    private void buttonsClick(){
        btnMinusH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnMinusPersonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(tvNumPersonne.getText().toString());
                if(num>1)
                    tvNumPersonne.setText((num-1)+"");
                else if (num==1)
                    Toast.makeText(ReservationActivity.this,"le minimum est 1 ",Toast.LENGTH_SHORT);
            }
        });
        btnPlusPersonne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num=Integer.parseInt(tvNumPersonne.getText().toString())+1;
                tvNumPersonne.setText(num+"");

            }
        });
        btnPlusH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnReserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                v.setClickable(false);
                lottieAnimationView.setVisibility(View.VISIBLE);
                eventManager.reservation(post.getId(), tvNumPersonne.getText().toString()
                        , tvNumH.getText().toString(), new VolleyCallback() {
                            @Override
                            public void onSuccess(Object response) {
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                                Snackbar.with(ReservationActivity.this, null)
                                        .type(Type.SUCCESS)
                                        .message("success")
                                        .duration(Duration.SHORT)
                                        .show();
                                new android.os.Handler().postDelayed(
                                        new Runnable() {
                                            public void run() {
                                                onBackPressed();
                                            }
                                        },
                                        1000);

                            }

                            @Override
                            public void onError(Object error) {
                                v.setClickable(true);
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                                System.out.println(error+"maher reservation");
                                Snackbar.with(ReservationActivity.this, null)
                                        .type(Type.ERROR)
                                        .message("error")
                                        .duration(Duration.SHORT)
                                        .show();

                            }
                        });
            }
        });
    }


    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        CircleImageView profile=(CircleImageView)toolbar.findViewById(R.id.toolbarProfileImg);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReservationActivity.this, ProfileActivity.class));
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

    private void hideComponents(){
        btnReserver.setVisibility(View.GONE);
        btnPlusPersonne.setVisibility(View.INVISIBLE);
        btnMinusPersonne.setVisibility(View.INVISIBLE);
        btnPlusH.setVisibility(View.INVISIBLE);
        btnMinusH.setVisibility(View.INVISIBLE);
    }


}
