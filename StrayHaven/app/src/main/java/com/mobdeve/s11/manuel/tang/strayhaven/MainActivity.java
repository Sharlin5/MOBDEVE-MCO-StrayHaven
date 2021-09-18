package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mobdeve.s11.manuel.tang.strayhaven.home.HomeRequestActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView btnRegister;
    private ProgressBar pbLogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        if (firebaseUser != null) {
            Intent intent = new Intent(MainActivity.this, HomeRequestActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initComponents();
        this.initFirebase();
    }

    public void initComponents(){
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        pbLogin = findViewById(R.id.pb_login);

        btnLogin = findViewById(R.id.btn_login_confirm);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                if(checkValidLogin(email, password)){
                    signIn(email, password);
                }
            }
        });

        btnRegister = findViewById(R.id.tv_signup_link);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initFirebase(){
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void signIn(String email, String password){
        pbLogin.setVisibility(View.VISIBLE);
        this.mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(MainActivity.this, HomeRequestActivity.class).putExtra("from", "activity_main");
                            startActivity(intent);
                            finish();
                        }
                        else {
                            pbLogin.setVisibility(View.GONE);
                            failedLogin();
                        }
                    }
                });
    }

    private void failedLogin(){
        Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
    }

    private boolean checkValidLogin(String email, String password){
        boolean isValid = true;
        if(email.isEmpty()){
            this.etEmail.setError("Required Field");
            isValid = false;
        }
        if (password.isEmpty()){
            this.etPassword.setError("Required Field");
            isValid = false;
        }
        return isValid;
    }
}