package com.maher.nowhere.PhotoActivity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Photo;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.ArrayList;

public class PhotoActivity extends AppCompatActivity {
    ImageView img;
    private OverlayView overlayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
      //  setUpToolbar();
        overlayView=new OverlayView(PhotoActivity.this);

       // img = (ImageView) findViewById(R.id.img);

        //   img.setImageResource(getIntent().getExtras().getInt("img"));
       // Picasso.with(this).load(Uri.parse(getIntent().getExtras().getString("img"))).into(img);

        String description = getIntent().getStringExtra("description");
        ArrayList<String> list = new ArrayList<>();
        list.add(getIntent().getExtras().getString("img"));

        overlayView.setDescription(description);



        ImageViewer.Builder builder =  new ImageViewer.Builder(this, list);
        builder.setStartPosition(0).
                 setOverlayView(overlayView)
                .hideStatusBar(false)
                .allowSwipeToDismiss(false)
                .show();
        builder.setOnDismissListener(new ImageViewer.OnDismissListener() {
            @Override
            public void onDismiss() {
                onBackPressed();
            }
        });

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
       // supportFinishAfterTransition();
        finish();
    }

    @Override
    public void finish() {
        super.finish();

    }
}
