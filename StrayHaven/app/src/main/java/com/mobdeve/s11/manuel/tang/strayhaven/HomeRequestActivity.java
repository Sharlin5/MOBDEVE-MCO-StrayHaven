package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRequestActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private TextView tvUpdates;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings,ibTracker, ibNotifications, ibMessages;
    private RecyclerView rvFeed;
    private ArrayList<Feed> dataFeed;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;

    private ArrayList<String> userKeys;
    private ArrayList<String> temppostername;
    private ArrayList<String> tempprofileUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_request);

        this.initComponents();
        this.initRequest();
        this.initProfilePic();

        if(!"activity_main".equals(getIntent().getStringExtra("from"))){
            overridePendingTransition(0,0);
            getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }

    }

    private void initProfilePic(){
        this.database = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();

        DatabaseReference reference = database.getReference(Collections.users.name());

        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String imageUrl = snapshot.child("profilepicUrl").getValue().toString();
                if (imageUrl.equals(" ")){
                    ivProfile.setImageResource(R.drawable.icon_default_user);
                } else {
                    Picasso.get().load(imageUrl).into(ivProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initRequest(){
        this.database = FirebaseDatabase.getInstance();
        this.dataFeed = new ArrayList<Feed>();

        database.getReference().child(Collections.request.name()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss:snapshot.getChildren()){
                    String type = dss.child("type").getValue(String.class);
                        String posterkey = dss.child("posterKey").getValue(String.class);
                        String caption = dss.child("caption").getValue(String.class);
                        String location = dss.child("location").getValue(String.class);
                        String date = dss.child("date").getValue(String.class);
                        String imageUrl = dss.child("postUrl").getValue(String.class);
                        String postername = dss.child("username").getValue(String.class);
                        String profileUrl = dss.child("profileUrl").getValue(String.class);
                        String postKey = dss.getKey();

                        Feed request = new Feed(posterkey, postername, profileUrl, imageUrl, type, location, caption, date);
                        request.setPostKey(postKey);
                        dataFeed.add(request);
                        //dataFeed.add(new Feed(postername, profileUrl, imageUrl, type, location, caption, date));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //FeedAdapter feedAdapter = new FeedAdapter(dataFeed);
        this.rvFeed = findViewById(R.id.rv_home_req_feed);
        this.rvFeed.setLayoutManager(new LinearLayoutManager(HomeRequestActivity.this, LinearLayoutManager.VERTICAL, true));
        this.rvFeed.setAdapter(new FeedAdapter(dataFeed));
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