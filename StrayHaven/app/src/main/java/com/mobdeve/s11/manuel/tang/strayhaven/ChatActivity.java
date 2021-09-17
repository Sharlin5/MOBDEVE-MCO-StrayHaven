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

import java.util.ArrayList;
import java.util.HashMap;

public class ChatActivity extends AppCompatActivity {

    ChatAdapter chatAdapter;
    ArrayList<Chat> dataChat;

    private ImageButton ibBack, ibSend;
    private ImageView ivProfile;
    private TextView tvUsername;
    private EditText etChat;

    private RecyclerView rvChat;

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String userId, receiver, imageUrl;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        initFirebase();
        initComponents();
        initRecyclerView();

    }

    private void initComponents() {
        this.ibBack = findViewById(R.id.iv_chat_back);
        this.ibSend = findViewById(R.id.ib_chat_send);
        this.ivProfile = findViewById(R.id.iv_chat_user_pic);
        this.tvUsername = findViewById(R.id.tv_chat_username);
        this.etChat = findViewById(R.id.et_chat_text_box);

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
                    sendMessage(userId, receiver, chat);
                } else {
                    Toast.makeText(ChatActivity.this, "Message Empty", Toast.LENGTH_SHORT).show();
                }

                etChat.setText("");

            }
        });

    }

    private void initRecyclerView() {
        this.rvChat = findViewById(R.id.rv_chat_messages);
        rvChat.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        rvChat.setLayoutManager(linearLayoutManager);

    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
        this.user = this.mAuth.getCurrentUser();
        this.userId = this.user.getUid();
        this.database = FirebaseDatabase.getInstance();

        DatabaseReference reference = database.getReference(Collections.users.name());
        Intent intent = getIntent();
        String receiverId = intent.getStringExtra(Keys.KEY_POSTER_ID.name());

        this.receiver = receiverId;

        reference.child(receiverId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String username = snapshot.child("username").getValue().toString();
                tvUsername.setText("@" + username);
                imageUrl = snapshot.child("profilepicUrl").getValue().toString();
                if (imageUrl.equals(" ")){
                    ivProfile.setImageResource(R.drawable.icon_default_user);
                } else {
                    Picasso.get().load(imageUrl).into(ivProfile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void sendMessage (String sender, String receiver, String chat) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("sender", sender);
        hashMap.put("receiver", receiver);
        hashMap.put("chat", chat);

        reference.child("messages").push().setValue(hashMap);
    }

    private void readChat (String userId, String receiverId, String imageUrl) {
        dataChat = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataChat.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chat chat = snapshot.getValue(Chat.class);
                    if(chat.getReceiver().equals(userId) && chat.getSender().equals(receiverId) ||
                        chat.getReceiver().equals(receiverId) && chat.getSender().equals(userId)) {
                        dataChat.add(chat);
                    }

                    chatAdapter = new ChatAdapter(ChatActivity.this, dataChat, imageUrl);
                    rvChat.setAdapter(chatAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}