package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ComponentActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.security.Key;

public class SettingsActivity extends AppCompatActivity {

    private ImageButton ibBack, ibCamera, ibGallery;
    private Button btnLogout, btnSave;
    private EditText etName, etDescription, etLocation, etPassword;
    private ImageView ivProfile, ivFeatured1, ivFeatured2, ivFeatured3, ivFeatured4, ivFeatured5;
    private FrameLayout flFeatured1, flFeatured2, flFeatured3, flFeatured4, flFeatured5;

    private static final int PERMISSION_CODE = 1000;
    private static final int IMAGE_CAPTURE_CODE = 1001;
    private Uri imageUri;
    private Uri featured1Uri, featured2Uri, featured3Uri, featured4Uri, featured5Uri;

    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private String userId;
    private String email, username;
    private String imageUrl;
    private String featured1Url, featured2Url, featured3Url, featured4Url, featured5Url;
    private User currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.initFirebase();
        this.initComponents();
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
        this.ibCamera = findViewById(R.id.ib_settings_camera);
        this.ibGallery = findViewById(R.id.ib_settings_gallery);
        this.btnLogout = findViewById(R.id.btn_settings_logout);
        this.etName = findViewById(R.id.et_settings_name);
        this.etDescription = findViewById(R.id.et_settings_desc);
        this.etLocation = findViewById(R.id.et_settings_loc);
        this.etPassword = findViewById(R.id.et_settings_pass);
        this.btnSave = findViewById(R.id.btn_settings_save);
        this.ivProfile = findViewById(R.id.iv_settings_user_pic);
        this.ivFeatured1 = findViewById(R.id.iv_settings_featured_1);
        this.ivFeatured2 = findViewById(R.id.iv_settings_featured_2);
        this.ivFeatured3 = findViewById(R.id.iv_settings_featured_3);
        this.ivFeatured4 = findViewById(R.id.iv_settings_featured_4);
        this.ivFeatured5 = findViewById(R.id.iv_settings_featured_5);
        this.flFeatured1 = findViewById(R.id.fl_settings_featured_1);
        this.flFeatured2 = findViewById(R.id.fl_settings_featured_2);
        this.flFeatured3 = findViewById(R.id.fl_settings_featured_3);
        this.flFeatured4 = findViewById(R.id.fl_settings_featured_4);
        this.flFeatured5 = findViewById(R.id.fl_settings_featured_5);


        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(SettingsActivity.this, "Logged Out", Toast.LENGTH_SHORT).show();

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
                if(isValid(name, password)){
                    updatePhoto();
                }
            }
        });

        ibGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery("Profile");
            }
        });

        ibCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromCamera();
            }
        });

        flFeatured1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery("Featured1");
            }
        });

        flFeatured2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery("Featured2");
            }
        });

        flFeatured3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery("Featured3");
            }
        });

        flFeatured4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery("Featured4");
            }
        });

        flFeatured5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery("Featured5");
            }
        });
    }

    //Get image from gallery
    private void pickImageFromGallery(String type){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        switch (type){
            case "Profile": {
                profileGalleryActivityResultLauncher.launch(intent);
                break;
            }
            case "Featured1":{
                featured1GalleryActivityResultLauncher.launch(intent);
                break;
            }
            case "Featured2":{
                featured2GalleryActivityResultLauncher.launch(intent);
                break;
            }
            case "Featured3":{
                featured3GalleryActivityResultLauncher.launch(intent);
                break;
            }
            case "Featured4":{
                featured4GalleryActivityResultLauncher.launch(intent);
                break;
            }
            case "Featured5":{
                featured5GalleryActivityResultLauncher.launch(intent);
                break;
            }
        }
    }

    //display imageUri for profile
    private ActivityResultLauncher<Intent> profileGalleryActivityResultLauncher = registerForActivityResult(
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

    // get imageUri for featured 1
    private ActivityResultLauncher<Intent> featured1GalleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        featured1Uri = data.getData();
                        ivFeatured1.setImageURI(featured1Uri);
                        ivFeatured1.requestLayout();
                        ivFeatured1.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                        ivFeatured1.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> featured2GalleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        featured2Uri = data.getData();
                        ivFeatured2.setImageURI(featured2Uri);
                        ivFeatured2.requestLayout();
                        ivFeatured2.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                        ivFeatured2.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> featured3GalleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        featured3Uri = data.getData();
                        ivFeatured3.setImageURI(featured3Uri);
                        ivFeatured3.requestLayout();
                        ivFeatured3.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                        ivFeatured3.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> featured4GalleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        featured4Uri = data.getData();
                        ivFeatured4.setImageURI(featured4Uri);
                        ivFeatured4.requestLayout();
                        ivFeatured4.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                        ivFeatured4.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    private ActivityResultLauncher<Intent> featured5GalleryActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        featured5Uri = data.getData();
                        ivFeatured5.setImageURI(featured5Uri);
                        ivFeatured5.requestLayout();
                        ivFeatured5.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                        ivFeatured5.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    //get image from camera
    private void pickImageFromCamera(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_DENIED ||
                    checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_DENIED){
                //permission not enabled, request it
                String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                //show popup to request permissions
                requestPermissions(permission, PERMISSION_CODE);
            }
            else {
                //permission already granted
                openCamera();
            }
        }
        else {
            //system os < marshmallow
            openCamera();
        }
    }

    private void openCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraActivityResultLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    //permission from popup was granted
                    openCamera();
                }
                else {
                    //permission from popup was denied
                    Toast.makeText(SettingsActivity.this, "Permission denied...", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private ActivityResultLauncher<Intent> cameraActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Bundle bundle = result.getData().getExtras();
                        Bitmap bitmap = (Bitmap) bundle.get("data");

                        Uri tempUri = getImageUri(getApplicationContext(), bitmap);
                        imageUri = tempUri;
                        ivProfile.setImageURI(tempUri);
                    } else {
                        Toast.makeText(SettingsActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    // For profile pic update
    private void updatePhoto(){
        this.database = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();

        String name = etName.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String location = etLocation.getText().toString().trim();

        currUser = new User(email, username, name, password, description, location);
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();

        User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, featured2Url, featured3Url, featured4Url, featured5Url);
        updateUser(user);

        if (imageUri != null){
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
                            imageUrl = uri.toString();
                            User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, " ", " ", " ", " ");
                            updateUser(user);
                        }
                    });
                }
            });
        }

        if (featured1Uri != null){
            StorageReference featuredRef = storageReference.child("featuredpics/" + featured1Uri.getLastPathSegment());
            UploadTask uploadFeatured1 = featuredRef.putFile(featured1Uri);
            uploadFeatured1.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                    Task<Uri> downloadUrl = featuredRef.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            featured1Url = uri.toString();
                            User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, " ", " ", " ", " ");
                            updateUser(user);
                        }
                    });
                }
            });
        }

        if (featured2Uri != null){
            StorageReference featuredRef = storageReference.child("featuredpics/" + featured2Uri.getLastPathSegment());
            UploadTask uploadFeatured2 = featuredRef.putFile(featured2Uri);
            uploadFeatured2.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                    Task<Uri> downloadUrl = featuredRef.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            featured2Url = uri.toString();
                            User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, featured2Url, featured3Url, featured4Url, featured5Url);
                            updateUser(user);
                        }
                    });
                }
            });
        }

        if (featured3Uri != null){
            StorageReference featuredRef = storageReference.child("featuredpics/" + featured3Uri.getLastPathSegment());
            UploadTask uploadFeatured3 = featuredRef.putFile(featured3Uri);
            uploadFeatured3.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                    Task<Uri> downloadUrl = featuredRef.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            featured3Url = uri.toString();
                            User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, featured2Url, featured3Url, featured4Url, featured5Url);
                            updateUser(user);
                        }
                    });
                }
            });
        }

        if (featured4Uri != null){
            StorageReference featuredRef = storageReference.child("featuredpics/" + featured4Uri.getLastPathSegment());
            UploadTask uploadFeatured4 = featuredRef.putFile(featured4Uri);
            uploadFeatured4.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                    Task<Uri> downloadUrl = featuredRef.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            featured4Url = uri.toString();
                            User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, featured2Url, featured3Url, featured4Url, featured5Url);
                            updateUser(user);
                        }
                    });
                }
            });
        }

        if (featured5Uri != null){
            StorageReference featuredRef = storageReference.child("featuredpics/" + featured5Uri.getLastPathSegment());
            UploadTask uploadFeatured5 = featuredRef.putFile(featured5Uri);
            uploadFeatured5.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                    Task<Uri> downloadUrl = featuredRef.getDownloadUrl();
                    downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            featured5Url = uri.toString();
                            User user = new User(email, username, name, password, description, location, imageUrl, featured1Url, featured2Url, featured3Url, featured4Url, featured5Url);
                            updateUser(user);
                        }
                    });
                }
            });
        }
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

                featured1Url = snapshot.child("featured1").getValue().toString();
                if (!featured1Url.equals(" ")){
                    Picasso.get().load(featured1Url).into(ivFeatured1);
                    ivFeatured1.requestLayout();
                    ivFeatured1.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                    ivFeatured1.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                }

                featured2Url = snapshot.child("featured2").getValue().toString();
                if (!featured2Url.equals(" ")){
                    Picasso.get().load(featured2Url).into(ivFeatured2);
                    ivFeatured2.requestLayout();
                    ivFeatured2.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                    ivFeatured2.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                }

                featured3Url = snapshot.child("featured3").getValue().toString();
                if (!featured3Url.equals(" ")){
                    Picasso.get().load(featured3Url).into(ivFeatured3);
                    ivFeatured3.requestLayout();
                    ivFeatured3.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                    ivFeatured3.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                }

                featured4Url = snapshot.child("featured4").getValue().toString();
                if (!featured4Url.equals(" ")){
                    Picasso.get().load(featured4Url).into(ivFeatured4);
                    ivFeatured4.requestLayout();
                    ivFeatured4.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                    ivFeatured4.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                }

                featured5Url = snapshot.child("featured5").getValue().toString();
                if (!featured5Url.equals(" ")){
                    Picasso.get().load(featured5Url).into(ivFeatured5);
                    ivFeatured5.requestLayout();
                    ivFeatured5.getLayoutParams().height = FrameLayout.LayoutParams.MATCH_PARENT;
                    ivFeatured5.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
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