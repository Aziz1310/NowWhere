package com.maher.nowhere.MapActivity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.maher.nowhere.CentreActivity.CentreActivity;
import com.maher.nowhere.CinemaActivity.FilmDetailActivity;
import com.maher.nowhere.R;
import com.maher.nowhere.RestaurantProfileActivity.RestaurantProfileActivity;
import com.maher.nowhere.SalleDeSportActivity.SalleSportActivity;
import com.maher.nowhere.SearchDetailActivity.SearchDetailActivity;
import com.maher.nowhere.model.Owner;
import com.maher.nowhere.model.User;
import com.maher.nowhere.providers.EventManager;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailMapActivity extends AppCompatActivity {

    private ImageView btnBack;
    private CircleImageView imageCrape;
    private Button btn_event;
    private TextView tvNom, tvDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_map);
        final Owner owner = (Owner) getIntent().getSerializableExtra("owner");
        final String categorie =  getIntent().getStringExtra("categorie");
        btnBack = (ImageView) findViewById(R.id.btn_back);
        btn_event = (Button) findViewById(R.id.btn_event);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        tvNom = (TextView) findViewById(R.id.textView5);
        imageCrape=(CircleImageView)findViewById(R.id.imageCrape);

        if(owner!=null){
            tvNom.setText(owner.getNom());
            tvDescription.setText(owner.getDescription());
            Picasso.with(this).
                    load(Uri.parse(owner.getUrlImage())).resize(100,100)
                    .into(imageCrape);
            btn_event.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailMapActivity.this, SearchDetailActivity.class);
                    switch (categorie){
                        case "Réstaurant":
                        case "Discos":
                        case "Caffées":
                            intent=new Intent(DetailMapActivity.this, RestaurantProfileActivity.class);
                            break;
                        case "magic places":
                        case "Cinémas":
                            intent = new Intent(DetailMapActivity.this, FilmDetailActivity.class);
                            break;
                        case "Centres":
                            intent = new Intent(DetailMapActivity.this, SalleSportActivity.class);
                            break;
                        case "Art":
                            intent = new Intent(DetailMapActivity.this, CentreActivity.class);
                            break;

                    }
                    intent.putExtra("owner",owner);
                    intent.putExtra("categorie",categorie);
                    System.out.println("categorie type "+categorie);
                    startActivity(intent);
                }
            });
        }



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
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
