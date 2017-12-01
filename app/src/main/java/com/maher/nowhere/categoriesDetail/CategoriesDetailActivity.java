package com.maher.nowhere.categoriesDetail;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.MapActivity.MapActivity;
import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.SearchActivity.SearchActivity;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.Categorie;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriesDetailActivity extends AppCompatActivity {


    Categorie categ;
    private TextView tvName;
    private ImageView icon;
    private AppCompatButton btnSearch;
    private TextView btnShowAll;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_detail);
        setuptoolbar();


        categ = (Categorie) getIntent().getSerializableExtra("categorie");
        tvName = (TextView) findViewById(R.id.tvTitle);
        icon = (ImageView) findViewById(R.id.iv_icon);
        btnSearch = (AppCompatButton) findViewById(R.id.btnSearch);
        btnShowAll = (TextView) findViewById(R.id.btnShowAll);


        if (categ != null) {
            btnSearch.setText(categ.getShortName() + " à proximité");
            icon.setImageResource(categ.getImg2());
            tvName.setText(categ.getText());

        }
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesDetailActivity.this, SearchActivity.class);
                intent.putExtra("categorie",categ.getShortName());
                startActivity(intent);
            }
        });
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoriesDetailActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setuptoolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException ignore) {
        }

        CircleImageView profile=(CircleImageView)toolbar.findViewById(R.id.toolbarProfileImg);
        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER+ User.getCurrentUser(this).getImage())).resize(50,50)
                .into(profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriesDetailActivity.this, ProfileActivity.class));
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
