package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrackerActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings, ibHome, ibNotifications, ibMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        this.initComponents();
        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    //Initialize objects
    public void initComponents(){
        this.ivProfile = findViewById(R.id.iv_tracker_user_pic);
        this.fabPost = findViewById(R.id.fab_tracker_create_post);
        this.ibSettings = findViewById(R.id.ib_tracker_settings);
        this.ibHome = findViewById(R.id.ib_tracker_home);
        this.ibNotifications = findViewById(R.id.ib_tracker_notifications);
        this.ibMessages = findViewById(R.id.ib_tracker_messages);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ibNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        ibMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });

    }
}