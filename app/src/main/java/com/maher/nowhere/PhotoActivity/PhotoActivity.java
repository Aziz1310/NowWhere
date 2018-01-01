package com.maher.nowhere.PhotoActivity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.maher.nowhere.R;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        setUpToolbar();

        img = (ImageView) findViewById(R.id.img);

     //   img.setImageResource(getIntent().getExtras().getInt("img"));
        Picasso.with(this).load(Uri.parse(getIntent().getExtras().getString("img"))).into(img);



    }

    private void setUpToolbar() {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        supportFinishAfterTransition();
    }

    @Override
    public void finish() {
        super.finish();

    }
}
