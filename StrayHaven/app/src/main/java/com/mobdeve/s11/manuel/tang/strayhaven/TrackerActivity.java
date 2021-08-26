package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TrackerActivity extends AppCompatActivity {

    private ImageButton ibHome, ibNotification, ibMessage, ibSettings;
    private FloatingActionButton fabPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        this.initComponents();
        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    //Initialize objects
    public void initComponents(){
        this.ibHome = findViewById(R.id.ib_home_home);
        this.ibNotification = findViewById(R.id.ib_home_notifications);
        this.ibSettings = findViewById(R.id.ib_home_settings);
        this.fabPost = findViewById(R.id.fab_home_create_post);

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, PostActivity.class);
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

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ibNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TrackerActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
    }
}