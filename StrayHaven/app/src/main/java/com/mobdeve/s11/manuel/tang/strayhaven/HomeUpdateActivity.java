package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeUpdateActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private TextView tvRequests;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings,ibTracker, ibNotifications, ibMessages;
    private RecyclerView rvUpdate;
    private ArrayList<Feed> dataUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_update);

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

    public void initRecyclerView(){
        this.dataUpdate = new UpdateDataHelper().loadUpdateData();
        this.rvUpdate = findViewById(R.id.rv_home_upd_feed);
        this.rvUpdate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvUpdate.setAdapter(new FeedAdapter(this.dataUpdate));
    }

    //Initialize objects
    public void initComponents(){
        this.ivProfile = findViewById(R.id.iv_home_upd_user_pic);
        this.tvRequests = findViewById(R.id.tv_home_upd_request_tab);
        this.ibSettings = findViewById(R.id.ib_home_upd_settings);
        this.fabPost = findViewById(R.id.fab_home_upd_create_post);
        this.ibTracker = findViewById(R.id.ib_home_upd_tracker);
        this.ibNotifications = findViewById(R.id.ib_home_upd_notifications);
        this.ibMessages = findViewById(R.id.ib_home_upd_messages);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        tvRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, HomeRequestActivity.class);
                startActivity(intent);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, TrackerActivity.class);
                startActivity(intent);

            }
        });

        ibNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        ibMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeUpdateActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });
    }

}