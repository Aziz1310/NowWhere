package com.maher.nowhere.PhotoActivity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.maher.nowhere.R;
import com.maher.nowhere.commentsActivity.CommentActivity;
import com.stfalcon.frescoimageviewer.ImageViewer;

/**
 * Created by maher on 18/01/2018.
 */

public class OverlayView extends RelativeLayout {

    LinearLayout btnComment;
    ImageView btnShare;
    ImageView img;
    ImageView btnBack;
    TextView tvDescription;
    PhotoActivity photoActivity;



    public OverlayView(Context context) {
        super(context);
        photoActivity=(PhotoActivity)context;
        init();
    }
    public void setDescription(String description) {
        tvDescription.setText(description);
    }
    private void sendShareIntent() {
    }

    private void init() {
        View view = inflate(getContext(), R.layout.photo_overlay, this);

         btnComment=view.findViewById(R.id.btnComment);
         btnShare=view.findViewById(R.id.btnShare);
         img=view.findViewById(R.id.img);
         tvDescription=view.findViewById(R.id.tvDescription);
         btnBack=view.findViewById(R.id.btn_back);

        btnShare.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendShareIntent();
            }
        });
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                photoActivity.finish();
            }
        });
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
