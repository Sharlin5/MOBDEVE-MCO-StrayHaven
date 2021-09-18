package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.mobdeve.s11.manuel.tang.strayhaven.misc.Collections;
import com.mobdeve.s11.manuel.tang.strayhaven.settings.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etUsername, etPassword;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private ProgressBar pbRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initFirebase();
        initComponents();
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance();
    }

    private void initComponents(){
        etEmail = findViewById(R.id.et_register_email);
        etUsername = findViewById(R.id.et_register_username);
        etPassword = findViewById(R.id.et_register_password);
        pbRegister = findViewById(R.id.pb_register);

        btnRegister = findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(checkValid(email, username, password)){
                    User user = new User(email, username, password);
                    storeUser(user);
                }
            }
        });
    }

    private boolean checkValid(String email, String username, String password){
        boolean isValid = true;
        if(email.isEmpty()){
            this.etEmail.setError("Required Value");
            isValid = false;
        }
        if(username.isEmpty()){
            this.etUsername.setError("Required Value");
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

    private void storeUser(User user){
        pbRegister.setVisibility(View.VISIBLE);

        this.mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            database.getReference(Collections.users.name()).child(mAuth.getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        successfulRegistration();
                                    } else {
                                        failedRegistration();
                                    }
                                }
                            });
                        }
                    }
                });
    }

    private void successfulRegistration(){
        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void failedRegistration(){
        Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
    }

}