package com.maher.nowhere.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.maher.nowhere.R;
import com.maher.nowhere.chat.adapter.ChatRoomThreadAdapter;
import com.maher.nowhere.gcm.NotificationUtils;
import com.maher.nowhere.model.Conversation;
import com.maher.nowhere.model.ConversationUser;
import com.maher.nowhere.model.Message;
import com.maher.nowhere.model.User;
import com.maher.nowhere.utiles.Config;
import com.maher.nowhere.utiles.ConnectionSingleton;
import com.maher.nowhere.utiles.EndlessRecyclerViewScrollListener;
import com.maher.nowhere.utiles.MyApplication;
import com.maher.nowhere.utiles.Urls;
import com.maher.nowhere.utiles.Utiles;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChatRoomActivity extends AppCompatActivity {

    private String TAG = ChatRoomActivity.class.getSimpleName();
    private Integer reciverid, senderid;
    private String chatRoomId;
    private RecyclerView recyclerView;
    private ChatRoomThreadAdapter mAdapter;
    private ArrayList<Message> messageArrayList;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private EditText inputMessage;
    private ImageView btnSend, imgProfile;
    private String urlPhoto;
    private String title;
    private int numMsg=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);


        inputMessage = (EditText) findViewById(R.id.message);
        btnSend = (ImageView) findViewById(R.id.btn_send);
        imgProfile = findViewById(R.id.img);

        Intent intent = getIntent();
        chatRoomId = intent.getStringExtra("chat_room_id");
        senderid = intent.getIntExtra("sender_id", User.getCurrentUser(this).getId());
        reciverid = intent.getIntExtra("reciver_id", 0);
        title = intent.getStringExtra("name");
        urlPhoto = intent.getStringExtra("img");
        setUpToolbar(title);

        Picasso.with(this).
                load(Uri.parse(Urls.IMG_URL_USER + urlPhoto)).resize(100, 100)
                .into(imgProfile, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });


        if (chatRoomId == null) {
            Toast.makeText(getApplicationContext(), "Chat room not found!", Toast.LENGTH_SHORT).show();
            finish();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        messageArrayList = new ArrayList<>();

        // self user id is to identify the message owner
        String selfUserId = User.getCurrentUser(ChatRoomActivity.this).getId() + "";

        mAdapter = new ChatRoomThreadAdapter(this, messageArrayList, selfUserId);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        fetchChatRooms();

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                numMsg+=10;
                fetchChatRooms();
                loadNextDataFromApi(page);
                Log.d(TAG, "onLoadMore: "+page+" total "+totalItemsCount);
            }
        };
      //  recyclerView.addOnScrollListener(scrollListener);


        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputMessage.getText().toString().isEmpty())
                    return;
                btnSend.setClickable(false);
                Message message = new Message();

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = new Date();
                String ourformat = formatter.format(date.getTime());
                //ServerValue.TIMESTAMP
                message.setSenderId(senderid + "");
                message.setSenderName(User.getCurrentUser(ChatRoomActivity.this).getName());
                message.setDate(ourformat);
                message.setText(inputMessage.getText().toString());
                sendMessage(message);
                inputMessage.setText("");
            }
        });

        registerChatRoom();

    }

    private void loadNextDataFromApi(int page) {

    }

    private void sendMessage(Message message) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(Urls.FIREBASE_CHANELS).child(chatRoomId);

        String idMessage = myRef.child(Urls.FIREBASE_MESSAGES).push().getKey();
        message.setId(idMessage);

        myRef.orderByKey().equalTo(chatRoomId).getRef().child(Urls.FIREBASE_MESSAGES).child(idMessage).setValue(message).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            inputMessage.setText("");
                        }
                        btnSend.setClickable(true);
                    }
                });
    }

    private void fetchChatRooms() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(Urls.FIREBASE_CHANELS).child(chatRoomId).child(Urls.FIREBASE_MESSAGES);
        myRef.orderByKey().limitToLast(numMsg).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message = dataSnapshot.getValue(Message.class);
                messageArrayList.add(message);
                mAdapter.notifyDataSetChanged();
                recyclerView.smoothScrollToPosition(messageArrayList.size() - 1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void registerChatRoom() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference().child(Urls.FIREBASE_CONVERSATIONS);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(chatRoomId)) {
                    Log.d(TAG, "registerChatRoom: chatRoom already exist");
                    return;
                }
                Log.d(TAG, "registerChatRoom: chatRoom does not exist");
                User currentUser=User.getCurrentUser(ChatRoomActivity.this);
                Conversation conversation = new Conversation();
                conversation.setId(chatRoomId);
                conversation.setUser1(
                        new ConversationUser(String.valueOf(reciverid),title,urlPhoto )
                );
                conversation.setUser2(
                        new ConversationUser(String.valueOf(currentUser.getId()),currentUser.getName(),currentUser.getImage())
                );

                DatabaseReference myRef1 = database.getReference().child(Urls.FIREBASE_CONVERSATIONS);
                myRef1.child(chatRoomId).setValue(conversation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();

        // registering the receiver for new notification
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        NotificationUtils.clearNotifications();
    }

    private void setUpToolbar(String title) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {
            getSupportActionBar().setTitle(title);
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


}
