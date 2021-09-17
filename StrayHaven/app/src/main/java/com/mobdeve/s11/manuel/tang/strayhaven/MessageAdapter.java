package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<Message> dataMessage;
    private Context context;

    public MessageAdapter(ArrayList<Message> dataMessage){
        this.dataMessage = dataMessage;
    }

    public MessageAdapter(Context context, ArrayList<Message> dataMessage){
        this.context = context;
        this.dataMessage = dataMessage;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.message_template, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(itemView);

        messageViewHolder.getLlMessage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUserKey());
                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getIvMessagePicture().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUserKey());
                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getTvProfilename().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUserKey());
                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getTvUsername().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUserKey());
                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getIbMessageDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseUser user = mAuth.getCurrentUser();
                String userId = user.getUid();

                DatabaseReference chatReference = database.getReference(Collections.chats.name());
                String receiverKey = dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUserKey();

                chatReference.child(userId).child(receiverKey).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().removeValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                context.startActivity(new Intent(context.getApplicationContext(), MessagesActivity.class));
            }
        });


        return messageViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message currentMessage = this.dataMessage.get(position);
        holder.setTvProfilename(currentMessage.getProfilename());
        holder.setTvUsername("@" + currentMessage.getUsername());
        holder.setIvMessagePicture(currentMessage.getImageId());
    }

    @Override
    public int getItemCount() {
        return this.dataMessage.size();
    }
}
