package com.maher.nowhere.detailAcceuilActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.commentsActivity.CommentActivity;
import com.maher.nowhere.model.Post;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

public class DetailAcceuilActivity extends AppCompatActivity {
    private Publication publication;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acceuil);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar()!=null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        publication=(Publication) getIntent().getSerializableExtra("publication");
        if (publication==null)
            finish();

        LinearLayout btnComment=findViewById(R.id.btnComment);
        ImageView btnShare=findViewById(R.id.btnShare);
        ImageView img=findViewById(R.id.img);
        TextView tvDescription=findViewById(R.id.tvDescription);

        Picasso.with(this)
                .load(Urls.IMG_URL_PUBLICATION+publication.getImage())
                .into(img);
        tvDescription.setText(publication.getDescription());

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailAcceuilActivity.this, CommentActivity.class);
                intent.putExtra("publication",publication);
                startActivity(intent);
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


}
