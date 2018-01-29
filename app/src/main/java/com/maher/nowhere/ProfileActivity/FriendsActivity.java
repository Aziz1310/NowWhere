package com.maher.nowhere.ProfileActivity;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

public class FriendsActivity extends AppCompatActivity {
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        User user = (User) getIntent().getSerializableExtra("user");
        if (user == null)
            finish();

        ImageView imgCover = findViewById(R.id.relativeLayoutP);
        ImageView imgProfile = findViewById(R.id.imageProfile);
        TextView tvNom = findViewById(R.id.nomProfile);
        ImageView btnAdd = findViewById(R.id.imageAdd);
        ImageView btnMsg = findViewById(R.id.imageChat);

        Picasso.with(this)
                .load(Urls.IMG_URL_USER_COVER+user.getCoverPhoto())
                .into(imgCover);
        Picasso.with(this)
                .load(Urls.IMG_URL_USER+user.getImage())
                .into(imgProfile);
        tvNom.setText(user.getName());


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
