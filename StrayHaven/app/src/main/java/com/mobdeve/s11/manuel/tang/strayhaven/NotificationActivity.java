package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NotificationActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings, ibHome, ibTracker, ibMessages;
    private RecyclerView rvNotif;
    private ArrayList<Notif> dataNotif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        this.initComponents();
        this.initRecyclerView();
        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void initRecyclerView(){
        this.dataNotif = new NotificationDataHelper().loadNotifData();
        this.rvNotif = findViewById(R.id.rv_notif_feed);
        this.rvNotif.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvNotif.setAdapter(new NotificationAdapter(this.dataNotif));
    }

    //Initialize objects
    public void initComponents(){
        this.ivProfile = findViewById(R.id.iv_notif_user_pic);
        this.fabPost = findViewById(R.id.fab_notif_create_post);
        this.ibSettings = findViewById(R.id.ib_notif_settings);
        this.ibHome = findViewById(R.id.ib_notif_home);
        this.ibTracker = findViewById(R.id.ib_notif_tracker);
        this.ibMessages = findViewById(R.id.ib_notif_messages);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, HomeRequestActivity.class);
                startActivity(intent);
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, TrackerActivity.class);
                startActivity(intent);
            }
        });

        ibMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotificationActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });
    }
}