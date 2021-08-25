package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity {

    private ImageButton ibHome, ibTracker, ibNotification, ibMessage, ibSettings;
    private FloatingActionButton fabPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    //Initialize objects
    public void initComponents(){
        this.ibHome = findViewById(R.id.ib_home);
        this.ibTracker = findViewById(R.id.ib_tracker);
        this.ibNotification = findViewById(R.id.ib_notifications);
        this.ibTracker = findViewById(R.id.ib_tracker);
        this.ibSettings = findViewById(R.id.ib_settings);
        this.fabPost = findViewById(R.id.fab_create_post);

    }

}