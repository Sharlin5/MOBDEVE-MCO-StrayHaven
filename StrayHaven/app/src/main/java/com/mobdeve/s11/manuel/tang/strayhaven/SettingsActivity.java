package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private Button btnLogout, btnSave;
    private EditText etName, etDescription, etLocation, etPassword;
    private ImageView ivProfile;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int IMAGE_PERMISSION_CODE = 1001;
    private Uri imageUri;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private String userId;
    private String email, username;
    private String imageUrl;


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
        this.ivProfile = findViewById(R.id.iv_settings_user_pic);

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
                if (imageUri != null){
                    updatePhoto();
                    Toast.makeText(SettingsActivity.this, "url in string: " + imageUrl, Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(email, username, name, password, description, location, imageUrl);
                    updateUser(user);
                }
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
    }

    //Get image from gallery
    private void pickImageFromGallery(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        galleryActivityResultLauncher.launch(intent);
    }

    //display imageUri
    private ActivityResultLauncher<Intent> galleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        imageUri = data.getData();
                        ivProfile.setImageURI(imageUri);
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    // For profile pic update
    private void updatePhoto(){
        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        User photoUser = new User(email, username, name, password, description, location);

        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageReference.child("profilepics/" + imageUri.getLastPathSegment());
        UploadTask uploadTask = imageRef.putFile(imageUri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                Task<Uri> downloadUrl = imageRef.getDownloadUrl();
                downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String profileUrl = uri.toString();
                        photoUser.setProfilepicUrl(profileUrl);
                        if(isValid(name, password)){
                            User user = new User(email, username, name, password, description, location, profileUrl);
                            updateUser(user);
                        }
                    }
                });
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

                imageUrl = snapshot.child("profilepicUrl").getValue().toString();
                if (imageUrl.equals(" ")){
                    ivProfile.setImageResource(R.drawable.icon_default_user);
                } else {
                    Picasso.get().load(imageUrl).into(ivProfile);
                }

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