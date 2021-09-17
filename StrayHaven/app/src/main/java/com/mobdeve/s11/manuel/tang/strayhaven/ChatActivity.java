package com.mobdeve.s11.manuel.tang.strayhaven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    private ImageButton ibBack, ibSend;
    private ImageView ivProfile;
    private TextView tvUsername;
    private EditText etChat;

    private RecyclerView rvChat;
    private ArrayList<Chat> dataChat;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;
    private FirebaseUser fUser;
    private String senderId, receiverId, receiverImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initComponents();
        initFirebase();
        initRecyclerView();

        overridePendingTransition(0,0);
        getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }

    private void initComponents() {
        this.ibBack = findViewById(R.id.iv_chat_back);
        this.ibSend = findViewById(R.id.ib_chat_send);
        this.ivProfile = findViewById(R.id.iv_chat_user_pic);
        this.tvUsername = findViewById(R.id.tv_chat_username);
        this.etChat = findViewById(R.id.et_chat_text_box);
        this.rvChat = findViewById(R.id.rv_chat_messages);

        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String chat = etChat.getText().toString();
                if(!chat.equals("")) {
                    sendMessage(senderId, receiverId, chat);
                    Toast.makeText(ChatActivity.this, "Message Sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChatActivity.this, "Message Empty", Toast.LENGTH_SHORT).show();
                }

                etChat.setText("");

            }
        });

    }

    private void initFirebase() {
        this.database = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = this.mAuth.getCurrentUser();
        this.senderId = this.fUser.getUid();

        DatabaseReference reference = database.getReference(Collections.users.name());
        Intent intent = getIntent();
        this.receiverId = intent.getStringExtra(Keys.KEY_POSTER_ID.name());

        reference.child(receiverId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String receiverUname = snapshot.child("username").getValue().toString();
                tvUsername.setText("@" + receiverUname);
                receiverImage = snapshot.child("profilepicUrl").getValue().toString();
                if (receiverImage.equals(" ") || receiverImage.equals(null)){
                    ivProfile.setImageResource(R.drawable.icon_default_user);
                } else {
                    Picasso.get().load(receiverImage).into(ivProfile);
                }
                //Toast.makeText(ChatActivity.this, "Receiver Key: " + receiverUname, Toast.LENGTH_LONG);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initRecyclerView() {
        database = FirebaseDatabase.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.fUser = this.mAuth.getCurrentUser();
        this.senderId = this.fUser.getUid();
        this.dataChat = new ArrayList<Chat>();

        DatabaseReference chatReference = database.getReference(Collections.chats.name());
        DatabaseReference userReference = database.getReference(Collections.users.name());

        Intent intent = getIntent();
        this.receiverId = intent.getStringExtra(Keys.KEY_POSTER_ID.name());

        chatReference.child(this.senderId).child(this.receiverId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss : snapshot.getChildren()) {
                    String senderkey = dss.child("sender").getValue().toString();
                    String receiverkey = dss.child("receiver").getValue().toString();
                    String message = dss.child("chat").getValue().toString();
                    Chat chat = new Chat(senderkey, receiverkey, message, receiverImage);
                    dataChat.add(chat);
                    //Toast.makeText(ChatActivity.this, dataChat.get(dataChat.size()-1).getMessage(), Toast.LENGTH_SHORT).show();
                    rvChat = findViewById(R.id.rv_chat_messages);
                    rvChat.setLayoutManager(new LinearLayoutManager(ChatActivity.this, LinearLayoutManager.VERTICAL, false));
                    rvChat.setAdapter(new ChatAdapter(ChatActivity.this, dataChat, senderId));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //save message
    private void sendMessage (String sender, String receiver, String chat) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("chat", chat);

        reference.child("chats").child(receiver).child(sender).push().setValue(hashMap);
        reference.child("chats").child(sender).child(receiver).push().setValue(hashMap);
    }
}