package com.maher.nowhere.categoriesDetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.SearchActivity.SearchActivity;

public class CategoriesDetailActivity extends AppCompatActivity {
    TextView btnShowAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_detail);
        btnShowAll=(TextView)findViewById(R.id.btnShowAll);
        btnShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CategoriesDetailActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }
}
