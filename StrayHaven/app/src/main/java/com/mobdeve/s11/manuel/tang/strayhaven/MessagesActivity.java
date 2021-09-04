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

public class MessagesActivity extends AppCompatActivity {

    private ImageView ivProfile;
    private ImageButton ibSettings, ibHome, ibTracker, ibNotifications;
    private RecyclerView rvMessage;
    private ArrayList<Message> dataMessage;
    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);
        this.initComponents();
        this.initRecyclerView();
        this.initProfilePic();
        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void initRecyclerView(){
        this.dataMessage = new MessageDataHelper().loadMessageData();
        this.rvMessage = findViewById(R.id.rv_message_feed);
        this.rvMessage.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        this.rvMessage.setAdapter(new MessageAdapter(this.dataMessage));
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

    //Initialize objects
    public void initComponents(){
        this.ivProfile = findViewById(R.id.iv_message_user_pic);
        this.ibSettings = findViewById(R.id.ib_message_settings);
        this.ibHome = findViewById(R.id.ib_message_home);
        this.ibTracker = findViewById(R.id.ib_message_tracker);
        this.ibNotifications = findViewById(R.id.ib_message_notifications);

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, HomeRequestActivity.class);
                startActivity(intent);
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, TrackerActivity.class);
                startActivity(intent);
            }
        });

        ibNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MessagesActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

    }

}