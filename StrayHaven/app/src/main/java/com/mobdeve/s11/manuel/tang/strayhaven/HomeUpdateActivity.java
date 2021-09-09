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
import android.widget.TextView;

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

public class HomeUpdateActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private TextView tvRequests;
    private FloatingActionButton fabPost;
    private ImageButton ibSettings,ibTracker, ibNotifications, ibMessages;
    private RecyclerView rvUpdate;
    private ArrayList<Feed> dataUpdate;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_update);

        this.initComponents();
        this.initUpdate();
        this.initProfilePic();

        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
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

    private void initUpdate(){
        this.database = FirebaseDatabase.getInstance();
        this.dataUpdate = new ArrayList<Feed>();

        database.getReference().child(Collections.update.name()).addValueEventListener(new ValueEventListener() {
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
                        Feed update = new Feed(posterkey, postername, profileUrl, imageUrl, type, location, caption, date);
                        update.setPostKey(postKey);
                        dataUpdate.add(update);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //FeedAdapter feedAdapter = new FeedAdapter(dataUpdate);
        this.rvUpdate = findViewById(R.id.rv_home_upd_feed);
        this.rvUpdate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
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