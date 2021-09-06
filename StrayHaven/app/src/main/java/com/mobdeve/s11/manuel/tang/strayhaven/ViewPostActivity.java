package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.Key;

public class ViewPostActivity extends AppCompatActivity {

    private ImageButton ibBack, ibHome, ibTracker, ibNotifications, ibMessages, ibDelete;
    private ImageView ivPicture, ivProfile;
    private TextView tvUsername, tvLocation, tvCaption, tvType, tvDate;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        this.initComponents();
        this.initFirebase();

        Intent intent = getIntent();
        String pictureUrl = intent.getStringExtra(Keys.KEY_POST_IMAGE.name());
        Picasso.get().load(pictureUrl).into(ivPicture);
        String imageUrl = intent.getStringExtra(Keys.KEY_POST_PROFILE.name());
        if (imageUrl.equals(" ")){
            ivProfile.setImageResource(R.drawable.icon_default_user);
        } else {
            Picasso.get().load(imageUrl).into(ivProfile);
        }
        //this.ivPicture.setImageResource(intent.getIntExtra(Keys.KEY_FEED_IMAGE.name(), 0));
        this.tvUsername.setText(intent.getStringExtra(Keys.KEY_FEED_USERNAME.name()));
        this.tvCaption.setText(intent.getStringExtra(Keys.KEY_FEED_CAPTION.name()));
        this.tvLocation.setText(intent.getStringExtra(Keys.KEY_FEED_LOCATION.name()));
        this.tvType.setText(intent.getStringExtra(Keys.KEY_FEED_TYPE.name()));
        this.tvDate.setText(intent.getStringExtra(Keys.KEY_FEED_DATE.name()));

        String checkPostid = intent.getStringExtra(Keys.KEY_POST_ID.name());

        Toast.makeText(this, checkPostid, Toast.LENGTH_SHORT).show();
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();
        this.database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(Collections.users.name());
        DatabaseReference feedReference = database.getReference(Collections.feeds.name());

        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String postname = snapshot.child("username").getValue().toString().trim();
                Intent intent = getIntent();

                String userPost = intent.getStringExtra(Keys.KEY_FEED_USERNAME.name());
                int userImage = intent.getIntExtra(Keys.KEY_FEED_IMAGE.name(), 0);
                String feedImage = intent.getStringExtra(Keys.KEY_POST_IMAGE.name());
                String userCaption = intent.getStringExtra(Keys.KEY_FEED_CAPTION.name());
                String userLocation = intent.getStringExtra(Keys.KEY_FEED_LOCATION.name());
                String userFeedType = intent.getStringExtra(Keys.KEY_FEED_TYPE.name());
                String userFeedDate = intent.getStringExtra(Keys.KEY_FEED_DATE.name());
                String postId = intent.getStringExtra(Keys.KEY_POST_ID.name());



                if (postname.equals(userPost)){
                    ibDelete.setVisibility(View.VISIBLE);

                    ibDelete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            feedReference.child(postId).removeValue();
                            /*
                            feedReference.child(postId).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        successfulDelete();
                                    } else {
                                        failedDelete();
                                    }
                                }
                            });*/

                        }
                    });

                } else {
                    ibDelete.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void successfulDelete(){
        Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void failedDelete(){
        Toast.makeText(this, "Delete Failed", Toast.LENGTH_SHORT).show();
    }


    private void initComponents(){
        tvUsername = findViewById(R.id.tv_view_username);
        tvType = findViewById(R.id.tv_view_request_type);
        tvLocation = findViewById(R.id.tv_view_user_loc);
        tvCaption = findViewById(R.id.tv_view_caption);
        tvDate = findViewById(R.id.tv_view_date_posted);
        ibBack = findViewById(R.id.ib_view_back);
        ibHome = findViewById(R.id.ib_view_home);
        ibTracker = findViewById(R.id.ib_view_tracker);
        ibNotifications = findViewById(R.id.ib_view_notifications);
        ibMessages = findViewById(R.id.ib_view_messages);
        ivPicture = findViewById(R.id.iv_view_picture);
        ivProfile = findViewById(R.id.iv_view_user_pic);
        ibDelete = findViewById(R.id.ib_view_delete);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPostActivity.this, HomeRequestActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ibMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPostActivity.this, MessagesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ibTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPostActivity.this, TrackerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ibNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewPostActivity.this, NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}