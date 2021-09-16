package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ViewMessageActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private ImageView ivProfile;
    private TextView tvUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);
        this.initComponents();

        Intent intent = getIntent();
        //this.ivProfile.setImageResource(intent.getIntExtra(Keys.KEY_MESSAGE_IMAGE.name(), 0));
        //this.tvUsername.setText(intent.getStringExtra(Keys.KEY_MESSAGE_USERNAME.name()));
    }

    private void initComponents(){
        ibBack = findViewById(R.id.iv_chat_back);
        ivProfile = findViewById(R.id.iv_chat_user_pic);
        tvUsername = findViewById(R.id.tv_chat_username);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}