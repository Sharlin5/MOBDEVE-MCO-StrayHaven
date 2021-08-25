package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton ibHome, ibTracker, ibNotification, ibMessage, ibSettings;
    private FloatingActionButton fabPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.initComponents();
    }

    //Initialize objects
    public void initComponents(){
        this.ibHome = findViewById(R.id.ib_home);
        this.ibTracker = findViewById(R.id.ib_tracker);
        this.ibNotification = findViewById(R.id.ib_notifications);
        this.ibTracker = findViewById(R.id.ib_tracker);
        this.ibSettings = findViewById(R.id.ib_settings);
        this.fabPost = findViewById(R.id.fab_create_post);

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, TrackerActivity.class);
                startActivity(intent);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ibNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
    }

}