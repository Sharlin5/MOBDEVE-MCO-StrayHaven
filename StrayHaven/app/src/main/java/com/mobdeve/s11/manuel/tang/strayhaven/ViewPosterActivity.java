package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

public class ViewPosterActivity extends AppCompatActivity {

    private FloatingActionButton fabPost;
    private ImageButton ibBack, ibSettings, ibHome, ibTracker, ibNotifications, ibMessages;
    private Button btnMessage;

    private TextView tvUsername, tvLocation, tvDescription, tvProfilename;
    private LinearLayout llLoc, llNoContent, llProfileFeatured;

    private ImageView ivProfile;
    private ImageView ivFeatured1, ivFeatured2, ivFeatured3, ivFeatured4, ivFeatured5;



    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
        initFirebase();

    }

    public void initComponents(){
        this.tvUsername = findViewById(R.id.tv_profile_username);
        this.tvDescription = findViewById(R.id.tv_profile_user_desc);
        this.tvLocation = findViewById(R.id.tv_profile_user_loc);
        this.tvProfilename = findViewById(R.id.tv_profile_name);
        this.fabPost = findViewById(R.id.fab_profile_create_post);
        this.ibBack = findViewById(R.id.ib_profile_back);
        this.ibSettings = findViewById(R.id.ib_profile_settings);
        this.ibHome = findViewById(R.id.ib_profile_home);
        this.ibTracker = findViewById(R.id.ib_profile_tracker);
        this.ibNotifications = findViewById(R.id.ib_profile_notifications);
        this.ibMessages = findViewById(R.id.ib_profile_messages);
        this.llLoc = findViewById(R.id.ll_profile_loc);
        this.ivProfile = findViewById(R.id.iv_profile_user_pic);
        this.btnMessage = findViewById(R.id.btn_profile_message);
        this.llNoContent = findViewById(R.id.ll_profile_no_content);
        this.llProfileFeatured = findViewById(R.id.ll_profile_featured);
        this.ivFeatured1 = findViewById(R.id.iv_profile_featured1);
        this.ivFeatured2 = findViewById(R.id.iv_profile_featured2);
        this.ivFeatured3 = findViewById(R.id.iv_profile_featured3);
        this.ivFeatured4 = findViewById(R.id.iv_profile_featured4);
        this.ivFeatured5 = findViewById(R.id.iv_profile_featured5);

        fabPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPosterActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPosterActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPosterActivity.this, HomeRequestActivity.class);
                startActivity(intent);
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPosterActivity.this, TrackerActivity.class);
                startActivity(intent);
            }
        });

        ibNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPosterActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });

        ibMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPosterActivity.this, MessagesActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();
        this.database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(Collections.users.name());
        Intent intent = getIntent();
        String posterKey = intent.getStringExtra(Keys.KEY_POSTER_ID.name());

        if(!posterKey.equals(userId)){
            btnMessage.setVisibility(View.VISIBLE);
        } else {
            btnMessage.setVisibility(View.GONE);
        }

        reference.child(posterKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("username").getValue().toString();
                tvUsername.setText("@" + username);
                String profilename = snapshot.child("profilename").getValue().toString();
                tvProfilename.setText(profilename);
                String description = snapshot.child("description").getValue().toString();
                tvDescription.setText(description);
                String location = snapshot.child("location").getValue().toString();
                tvLocation.setText(location);
                String imageUrl = snapshot.child("profilepicUrl").getValue().toString();
                if (imageUrl.equals(" ")){
                    ivProfile.setImageResource(R.drawable.icon_default_user);
                } else {
                    Picasso.get().load(imageUrl).into(ivProfile);
                }

                String featured1Url = snapshot.child("featured1").getValue().toString();
                String featured2Url = snapshot.child("featured2").getValue().toString();
                String featured3Url = snapshot.child("featured3").getValue().toString();
                String featured4Url = snapshot.child("featured4").getValue().toString();
                String featured5Url = snapshot.child("featured5").getValue().toString();

                if (featured1Url.equals(" ") && featured2Url.equals(" ") && featured3Url.equals(" ") && featured4Url.equals(" ") && featured5Url.equals(" ")){
                    llNoContent.setVisibility(View.VISIBLE);
                    llProfileFeatured.setVisibility(View.GONE);
                } else {
                    llNoContent.setVisibility(View.GONE);
                    llProfileFeatured.setVisibility(View.VISIBLE);

                    if (featured1Url.equals(" ")){
                        ivFeatured1.setVisibility(View.GONE);
                    } else {
                        ivFeatured1.setVisibility(View.VISIBLE);
                        Picasso.get().load(featured1Url).into(ivFeatured1);
                    }

                    if (featured2Url.equals(" ")){
                        ivFeatured2.setVisibility(View.GONE);
                    } else {
                        ivFeatured2.setVisibility(View.VISIBLE);
                        Picasso.get().load(featured2Url).into(ivFeatured2);
                    }

                    if (featured3Url.equals(" ")){
                        ivFeatured3.setVisibility(View.GONE);
                    } else {
                        ivFeatured3.setVisibility(View.VISIBLE);
                        Picasso.get().load(featured3Url).into(ivFeatured3);
                    }

                    if (featured4Url.equals(" ")){
                        ivFeatured4.setVisibility(View.GONE);
                    } else {
                        ivFeatured4.setVisibility(View.VISIBLE);
                        Picasso.get().load(featured4Url).into(ivFeatured4);
                    }

                    if (featured5Url.equals(" ")){
                        ivFeatured5.setVisibility(View.GONE);
                    } else {
                        ivFeatured5.setVisibility(View.VISIBLE);
                        Picasso.get().load(featured5Url).into(ivFeatured5);
                    }
                }

                if (!location.equals(" ") && !location.isEmpty()) {
                    llLoc.setVisibility(View.VISIBLE);
                } else {
                    llLoc.setVisibility(View.GONE);
                }

                if (!description.equals(" ") && !description.isEmpty()) {
                    tvDescription .setVisibility(View.VISIBLE);
                } else {
                    tvDescription .setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}