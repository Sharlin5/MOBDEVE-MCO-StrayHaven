package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

public class SettingsActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private Button btnLogout, btnSave;
    private EditText etName, etDescription, etLocation, etPassword;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private String userId;
    private String email, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.initComponents();
        this.initFirebase();
        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    //Initialize objects
    public void initComponents(){
        this.ibBack = findViewById(R.id.ib_settings_back);
        this.btnLogout = findViewById(R.id.btn_settings_logout);
        this.etName = findViewById(R.id.et_settings_name);
        this.etDescription = findViewById(R.id.et_settings_desc);
        this.etLocation = findViewById(R.id.et_settings_loc);
        this.etPassword = findViewById(R.id.et_settings_pass);
        this.btnSave = findViewById(R.id.btn_settings_save);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String location = etLocation.getText().toString().trim();

                if(isValid(name, password)){
                    User user = new User(email, username, name, password, description, location);
                    updateUser(user);
                }

            }
        });
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();
        this.database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(Collections.users.name());
        reference.child(this.userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                email = snapshot.child("email").getValue().toString();
                username = snapshot.child("username").getValue().toString();
                String name = snapshot.child("profilename").getValue().toString();
                etName.setText(name);
                String password = snapshot.child("password").getValue().toString();
                etPassword.setText(password);
                String description = snapshot.child("description").getValue().toString();
                if (!description.equals(" ") && !description.isEmpty()) {
                    etDescription.setText(description);
                }
                String location= snapshot.child("location").getValue().toString();
                if (!location.equals(" ") && !location.isEmpty()) {
                    etLocation.setText(location);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateUser(User user){
        database.getReference(Collections.users.name()).child(mAuth.getCurrentUser().getUid())
                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    successfulUpdate();
                } else {
                    failedUpdate();
                }
            }
        });
    }

    private void successfulUpdate(){
        Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SettingsActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
    }

    private void failedUpdate(){
        Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
    }

    private boolean isValid(String name, String password){
        boolean isValid = true;
        if(name.isEmpty()){
            this.etName.setError("Required Value");
            isValid = false;
        }
        if(password.isEmpty()){
            this.etPassword.setError("Required Value");
            isValid = false;
        } else {
            if(password.length() < 8){
                this.etPassword.setError("Must at least be 8 characters long");
                isValid = false;
            }
        }
        return isValid;
    }
}