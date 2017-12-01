package com.maher.nowhere.SearchActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.maher.nowhere.ProfileActivity.ProfileActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.SearchActivity.adapter.SearchAdapter;
import com.maher.nowhere.mainActivity.MainActivity;
import com.maher.nowhere.model.Search;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchActivity extends AppCompatActivity {

    private ArrayList<Search> lsearch;
    private View view;
    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private int lastPosition=-1;
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        setupToolbar();
        String categorie=getIntent().getStringExtra("categorie");


        lsearch = new ArrayList<>();
        lsearch.add(new Search(R.drawable.image,"23","09","2017","BYE BYE SUMMER", "Carpe Dieam"));
        lsearch.add(new Search(R.drawable.img3,"11","09","2017","FIESTA GITANA", "Yuka"));
        lsearch.add(new Search(R.drawable.img1,"22","09","2017","SABRI MOSBAH", "Sabri"));
        lsearch.add(new Search(R.drawable.img2,"31","2","2017","SABRI MOSBAH", "Sabri"));
        lsearch.add(new Search(R.drawable.img4,"19","09","2017","SABRI MOSBAH", "Sabri"));

        recyclerView=(RecyclerView)findViewById(R.id.rv_search);
        lm=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(lm);
        SearchAdapter searchAdapter = new SearchAdapter(this, lsearch,categorie);
        recyclerView.setAdapter(searchAdapter);

    }
    private void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }catch (NullPointerException ignore){}


        CircleImageView profile=(CircleImageView)toolbar.findViewById(R.id.toolbarProfileImg);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SearchActivity.this, ProfileActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(lastPosition>-1)
        recyclerView.smoothScrollToPosition(lastPosition);
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
