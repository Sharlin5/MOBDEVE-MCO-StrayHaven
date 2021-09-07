package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
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

public class TrackerActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings, ibHome, ibNotifications, ibMessages;
    private RecyclerView rvTracker;
    private ArrayList<Feed> dataTracker;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        this.initComponents();
        this.initProfilePic();
        this.initRecyclerView();
        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    // sets profile picture
    private void initProfilePic(){
        this.database = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();

        DatabaseReference reference = database.getReference(Collections.users.name());

        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username = snapshot.child("username").getValue().toString();
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

    //initialize recycler view
    private void initRecyclerView(){
        this.database = FirebaseDatabase.getInstance();
        this.dataTracker = new ArrayList<Feed>();
        this.userId = this.user.getUid();

        database.getReference().child(Collections.request.name()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss:snapshot.getChildren()){
                    String type = dss.child("type").getValue(String.class);
                    String postername = dss.child("username").getValue(String.class);
                    if(postername.equals(username)){
                        String posterkey = dss.child("posterKey").getValue(String.class);
                        String caption = dss.child("caption").getValue(String.class);
                        String location = dss.child("location").getValue(String.class);
                        String date = dss.child("date").getValue(String.class);
                        String imageUrl = dss.child("postUrl").getValue(String.class);
                        String profileUrl = dss.child("profileUrl").getValue(String.class);
                        String postKey = dss.getKey();
                        String isDone = dss.child("isDone").getValue(String.class);
                        dataTracker.add(new Feed(postKey, posterkey, postername, profileUrl, imageUrl, type, location, caption, date, isDone));
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        TrackerAdapter trackerAdapter = new TrackerAdapter(this.dataTracker);
        trackerAdapter.notifyDataSetChanged();

        this.rvTracker = findViewById(R.id.rv_tracker_feed);
        this.rvTracker.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvTracker.setAdapter(new TrackerAdapter(this, this.dataTracker));

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
                Intent intent = new Intent(TrackerActivity.this, HomeRequestActivity.class);
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