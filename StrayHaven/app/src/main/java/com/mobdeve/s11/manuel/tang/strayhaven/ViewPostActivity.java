package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPostActivity extends AppCompatActivity {

    private ImageButton ibBack, ibHome, ibTracker, ibNotifications, ibMessages;
    private ImageView ivPicture;
    private TextView tvUsername, tvLocation, tvCaption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_post);
        
    }
}