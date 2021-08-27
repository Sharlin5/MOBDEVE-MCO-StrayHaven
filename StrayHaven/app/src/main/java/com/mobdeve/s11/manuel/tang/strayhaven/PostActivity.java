package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class PostActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        this.initComponents();
    }

    //Initialize objects
    public void initComponents(){
        this.ibBack = findViewById(R.id.ib_post_back);
        this.btnPost = findViewById(R.id.btn_post_done);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, HomeRequestActivity.class);
                startActivity(intent);
            }
        });
    }
}