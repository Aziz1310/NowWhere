package com.maher.nowhere.commentsActivity;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.airbnb.lottie.LottieAnimationView;
import com.maher.nowhere.R;
import com.maher.nowhere.model.Comment;
import com.maher.nowhere.model.Publication;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Urls;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class CommentActivity extends AppCompatActivity implements CommentView {

    private RecyclerView recyclerView;
    private LinearLayoutManager lm;
    private ArrayList<Comment> comments;
    private LottieAnimationView lottieAnimationView;
    private Publication publication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setUpToolbar();
        publication = (Publication) getIntent().getSerializableExtra("publication");

        CircleImageView profile_img = findViewById(R.id.profile_img);
        final EditText etComment = findViewById(R.id.etComment);
        final ImageView btnComment = findViewById(R.id.btnComment);
        recyclerView = findViewById(R.id.rv_comment);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.loadingAnimation);

        final CommentPresenter commentPresenter = new CommentPresenter(this, this);


        comments = new ArrayList<>();
        comments = publication.getComments();
        lm = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);
        CommentAdapter commentAdapter = new CommentAdapter(this, comments);
        recyclerView.setLayoutManager(lm);
        recyclerView.setAdapter(commentAdapter);


        Picasso.with(this).load(Uri.parse(Urls.IMG_URL_USER + User.getCurrentUser(this).getImage())).into(profile_img, new com.squareup.picasso.Callback() {
            @Override
            public void onSuccess() {
                System.out.println(" maher image loaded with success");
            }

            @Override
            public void onError() {
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etComment.getText().toString().trim().isEmpty()) {
                    btnComment.setClickable(false);
                    commentPresenter.addComment(User.getCurrentUser(CommentActivity.this).getId(),
                            publication.getId(),
                            etComment.getText().toString());
                }

            }
        });
    }

    private void setUpToolbar() {
        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Commentaires");
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException ignore) {
        }
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

    @Override
    public void showProgress() {
        lottieAnimationView.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        lottieAnimationView.setVisibility(View.GONE);

    }

    @Override
    public void networkError() {
        System.out.println("network error load comments");
        ImageView btnComment = findViewById(R.id.btnComment);
        btnComment.setClickable(true);
    }

    @Override
    public void loadAllComments(ArrayList<Comment> comments) {
        System.out.println("load all comments");
        final EditText etComment = findViewById(R.id.etComment);
        ImageView btnComment = findViewById(R.id.btnComment);

        this.comments = new ArrayList<>();
        this.comments = comments;
        CommentAdapter commentAdapter = new CommentAdapter(this, comments);
        recyclerView.setAdapter(commentAdapter);
        etComment.setText("");
        btnComment.setClickable(true);

    }


}
