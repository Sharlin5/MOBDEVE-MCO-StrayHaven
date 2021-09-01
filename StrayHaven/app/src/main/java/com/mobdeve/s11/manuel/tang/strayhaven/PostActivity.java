package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private Button btnPost;
    private EditText etPostRequest, etPostLocation, etPostDescription;

    //Request type spinner
    private String[] request = {"Request Type", "Adopt", "Foster", "Update"};
    private ArrayAdapter requestArrayAdapter;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        this.initComponents();
        this.initFirebase();
    }

    private void initFirebase(){
        this.database = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();
        this.database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(Collections.users.name());

        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                username = snapshot.child("username").getValue().toString();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Initialize objects
    public void initComponents(){
        this.etPostRequest = findViewById(R.id.et_post_request);
        this.etPostDescription = findViewById(R.id.et_post_caption);
        this.etPostLocation = findViewById(R.id.et_post_loc);
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

                String request = etPostRequest.getText().toString().trim();
                String description = etPostDescription.getText().toString().trim();
                String location = etPostLocation.getText().toString().trim();

                Feed feed = new Feed(username, R.drawable.picture_feature1, request, location, description);
                storePost(feed);
            }
        });
    }

    private void storePost(Feed feed){

        database.getReference(Collections.feeds.name()).push().setValue(feed).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    successfulPost();
                } else {
                    failedPost();
                }
            }
        });
    }

    private void successfulPost(){
        Toast.makeText(this, "Post Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(PostActivity.this, HomeRequestActivity.class);
        startActivity(intent);
        finish();
    }

    private void failedPost(){
        Toast.makeText(this, "Post Failed", Toast.LENGTH_SHORT).show();
    }
}