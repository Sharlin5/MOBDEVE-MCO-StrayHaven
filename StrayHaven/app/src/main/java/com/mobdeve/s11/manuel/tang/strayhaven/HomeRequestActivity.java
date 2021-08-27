package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeRequestActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private TextView tvUpdates;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings,ibTracker, ibNotifications, ibMessages;
    private RecyclerView rvFeed;
    private FeedAdapter feedAdapter;
    private ArrayList<Feed> dataFeed;
    /*
    private ActivityResultLauncher feedActivityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();

                    }
                }
            });
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_request);

        this.initComponents();
        this.initRecyclerView();

        if(!"activity_main".equals(getIntent().getStringExtra("from"))){
            overridePendingTransition(0,0);
            getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }

    }

    private void initRecyclerView(){
        this.dataFeed = new FeedDataHelper().loadFeedData();
        this.rvFeed = findViewById(R.id.rv_home_req_feed);
        this.rvFeed.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvFeed.setAdapter(new FeedAdapter(this.dataFeed));
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    //Initialize objects
    private void initComponents(){
        this.ivProfile = findViewById(R.id.iv_home_req_user_pic);
        this.tvUpdates = findViewById(R.id.tv_home_req_update_tab);
        this.ibSettings = findViewById(R.id.ib_home_req_settings);
        this.fabPost = findViewById(R.id.fab_home_req_create_post);
        this.ibTracker = findViewById(R.id.ib_home_req_tracker);
        this.ibNotifications = findViewById(R.id.ib_home_req_notifications);
        this.ibMessages = findViewById(R.id.ib_home_req_messages);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        tvUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, HomeUpdateActivity.class);
                startActivity(intent);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, TrackerActivity.class);
                startActivity(intent);

            }
        });

        ibNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        ibMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeRequestActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });
    }

}