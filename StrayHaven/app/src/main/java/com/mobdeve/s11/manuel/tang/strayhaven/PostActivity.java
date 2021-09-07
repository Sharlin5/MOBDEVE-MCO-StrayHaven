package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
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

import java.io.ByteArrayOutputStream;

public class PostActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private Button btnPost;
    private EditText etPostRequest, etPostLocation, etPostDescription;
    private ImageView ivPost;

    //Request type spinner
    private String[] request = {"Request Type", "Adopt", "Foster", "Update"};
    private ArrayAdapter requestArrayAdapter;

    private static final int PERMISSION_CODE = 1000;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private String userId;
    private String username;
    private String userkey;
    private Uri imageUri;
    private String profileUrl;

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
                profileUrl = snapshot.child("profilepicUrl").getValue().toString();
                userkey = snapshot.getKey();
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
        this.ivPost = findViewById(R.id.iv_post_image);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { finish(); }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storePost();
                /*
                Intent intent = new Intent(PostActivity.this, HomeRequestActivity.class);
                startActivity(intent);*/
            }
        });

        ivPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImageFromGallery();
            }
        });
    }

    private void storePost(){
        String request = etPostRequest.getText().toString().trim();
        String description = etPostDescription.getText().toString().trim();
        String location = etPostLocation.getText().toString().trim();
        String date = new CustomDate().toStringFull();

        // storage
        StorageReference storageReference = FirebaseStorage.getInstance().getReference();
        StorageReference imageRef = storageReference.child("feedpics/" + imageUri.getLastPathSegment());
        UploadTask uploadTask = imageRef.putFile(imageUri);

        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                StorageMetadata snapshotMetadata = taskSnapshot.getMetadata();
                Task<Uri> downloadUrl = imageRef.getDownloadUrl();
                downloadUrl.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String postUrl = uri.toString();
                        if(!hasEmpty(request, location, description)  && (request.equals("Foster") || request.equals("Adopt"))){
                            //Feed feed = new Feed(userkey, postUrl, request, location, description, date);
                            String isDone = "false";
                            Feed feed = new Feed(userkey, username, profileUrl, postUrl,request, location, description, date, isDone);
                            database.getReference(Collections.request.name()).push().setValue(feed).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        successfulPost();
                                    } else {
                                        failedPost();
                                    }
                                }
                            });
                        } else if (!hasEmpty(request, location, description)  && (request.equals("Update"))){
                            Feed feed = new Feed(userkey, username, profileUrl, postUrl,request, location, description, date);
                            database.getReference(Collections.update.name()).push().setValue(feed).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                    }
                });
            }
        });
    }

    private boolean hasEmpty(String request, String location, String caption){
        boolean empty = false;
        if(request.isEmpty()){
            this.etPostRequest.setError("Required Value");
            empty = true;
        }
        if(location.isEmpty()){
            this.etPostLocation.setError("Required Value");
            empty = true;
        }
        if (caption.isEmpty()){
            this.etPostDescription.setError("Required Value");
            empty = true;
        }
        if (imageUri == null){
            Toast.makeText(this, "Please upload a photo.", Toast.LENGTH_SHORT).show();
        }

        return empty;
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

    //For image upload
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
                        ivPost.setImageURI(imageUri);
                    } else {
                        Toast.makeText(PostActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
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

        //Camera intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //intent.putExtra(Keys.KEY_PROFILE_IMAGE.name(), tempUri.toString());
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
                    Toast.makeText(PostActivity.this, "Permission denied...", Toast.LENGTH_SHORT).show();
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
                        ivPost.setImageURI(tempUri);
                    } else {
                        Toast.makeText(PostActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}