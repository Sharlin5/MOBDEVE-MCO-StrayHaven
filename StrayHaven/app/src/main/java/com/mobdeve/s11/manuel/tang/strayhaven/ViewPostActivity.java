package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
    private FloatingActionButton fabInterested;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private String userId;
    private String postId;
    private String posterId;

    private Boolean likechecker = false;

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
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();
        this.database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(Collections.users.name());
        DatabaseReference updateReference = database.getReference(Collections.update.name());
        DatabaseReference requestReference = database.getReference(Collections.request.name());
        DatabaseReference likeReference = database.getReference(Collections.likes.name());
        DatabaseReference notifReference = database.getReference(Collections.notifs.name());

        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String postname = snapshot.child("username").getValue().toString().trim(); // name of the current user
                Intent intent = getIntent();

                String userPost = intent.getStringExtra(Keys.KEY_FEED_USERNAME.name());
                String userFeedType = intent.getStringExtra(Keys.KEY_FEED_TYPE.name());
                String postId = intent.getStringExtra(Keys.KEY_POST_ID.name());
                posterId = intent.getStringExtra(Keys.KEY_POSTER_ID.name());
                final String postKey = postId;

                String feedImage = intent.getStringExtra(Keys.KEY_POST_IMAGE.name());
                String currDate = new CustomDate().toStringFull();

                // delete button visibility
                if (postname.equals(userPost)){
                    ibDelete.setVisibility(View.VISIBLE);
                    fabInterested.setVisibility(View.GONE);
                } else {
                    ibDelete.setVisibility(View.GONE);
                    fabInterested.setVisibility(View.VISIBLE);
                }

                // delete button functionality
                ibDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (userFeedType.equals("Update")){
                            DatabaseReference feedReference = updateReference;
                            feedReference.child(postId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                successfulDelete();
                                            } else {
                                                failedDelete();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        } else {
                            DatabaseReference feedReference = requestReference;
                            feedReference.child(postId).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    snapshot.getRef().removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                successfulDelete();
                                            } else {
                                                failedDelete();
                                            }
                                        }
                                    });
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }
                });

                // set heart view
                database.getReference(Collections.likes.name()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.child(postKey).hasChild(userId)){
                            fabInterested.setImageResource(R.drawable.icon_heart_on);
                            fabInterested.setImageTintList(ColorStateList.valueOf(Color.rgb(255, 1, 1)));
                        } else {
                            fabInterested.setImageResource(R.drawable.icon_heart_off);
                            fabInterested.setImageTintList(ColorStateList.valueOf(Color.rgb(255, 1, 1)));
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                // Like/Heart button functionality
                fabInterested.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        likechecker = true;
                        likeReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (likechecker.equals(true)){
                                    if (snapshot.child(postKey).hasChild(userId)){
                                        likeReference.child(postKey).child(userId).removeValue();
                                        likechecker = false;
                                    } else {
                                        likeReference.child(postKey).child(userId).setValue(true);
                                        likechecker = false;
                                        if (userFeedType.equals("Update")) {
                                            Notif notif = new Notif(postname, feedImage, "likes the update! Thank you for your hard work!", currDate, userId);
                                            notifReference.child(posterId).push().setValue(notif);
                                        } else {
                                            Notif notif = new Notif(postname, feedImage, "seems to be interested! You can send a message by visiting their profile.", currDate, userId);
                                            notifReference.child(posterId).push().setValue(notif);
                                        }

                                    }
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });



                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void successLike(){
        Toast.makeText(this, "Liked post", Toast.LENGTH_SHORT).show();
    }

    private void failedLike(){
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    private void successfulDelete(){
        Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewPostActivity.this, HomeRequestActivity.class);
        startActivity(intent);
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
        fabInterested = findViewById(R.id.fab_view_heart);

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

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewPosterActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(),posterId);
                v.getContext().startActivity(intent);
            }
        });

        tvUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewPosterActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(),posterId);
                v.getContext().startActivity(intent);
            }
        });
    }
}