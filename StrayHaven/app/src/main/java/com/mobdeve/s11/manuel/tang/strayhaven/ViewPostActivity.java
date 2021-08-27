package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.Key;

public class ViewPostActivity extends AppCompatActivity {

    private ImageButton ibBack, ibHome, ibTracker, ibNotifications, ibMessages;
    private ImageView ivPicture;
    private TextView tvUsername, tvLocation, tvCaption, tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);

        this.initComponents();

        Intent intent = getIntent();
        this.ivPicture.setImageResource(intent.getIntExtra(Keys.KEY_FEED_IMAGE.name(), 0));
        this.tvUsername.setText(intent.getStringExtra(Keys.KEY_FEED_USERNAME.name()));
        this.tvCaption.setText(intent.getStringExtra(Keys.KEY_FEED_CAPTION.name()));
        this.tvLocation.setText(intent.getStringExtra(Keys.KEY_FEED_LOCATION.name()));
        this.tvType.setText(intent.getStringExtra(Keys.KEY_FEED_TYPE.name()));

    }

    private void initComponents(){
        tvUsername = findViewById(R.id.tv_view_username);
        tvType = findViewById(R.id.tv_view_request_type);
        tvLocation = findViewById(R.id.tv_view_user_loc);
        tvCaption = findViewById(R.id.tv_view_caption);
        ibBack = findViewById(R.id.ib_view_back);
        ibHome = findViewById(R.id.ib_view_home);
        ibTracker = findViewById(R.id.ib_view_tracker);
        ibNotifications = findViewById(R.id.ib_view_notifications);
        ibMessages = findViewById(R.id.ib_view_messages);
        ivPicture = findViewById(R.id.iv_view_picture);

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