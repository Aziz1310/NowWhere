package com.maher.nowhere.reservationActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.Reservation;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReservationActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        setupToolbar();
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


}
