package com.maher.nowhere.categoriesDetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.model.Categ;

public class CategoriesDetailActivity extends AppCompatActivity {

    Categ categ;
    private TextView tvName;
    private ImageView icon;
    private AppCompatButton btnSearch;
    private TextView btnFindAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories_detail);
        categ=(Categ) getIntent().getSerializableExtra("categorie");
        tvName=(TextView)findViewById(R.id.tvTitle);
        icon=(ImageView)findViewById(R.id.iv_icon);
        btnSearch=(AppCompatButton)findViewById(R.id.btnSearch);
        btnFindAll=(TextView) findViewById(R.id.btnShowAll);




        if(categ!=null){

        }
    }
}
