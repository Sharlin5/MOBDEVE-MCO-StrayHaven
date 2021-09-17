package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Context;
import android.provider.ContactsContract;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private ArrayList<Chat> dataChat;
    private Context context;

    public ChatAdapter(ArrayList<Chat> dataChat ){
        this.dataChat = dataChat;
    }

    public ChatAdapter(ChatActivity context, ArrayList<Chat> dataChat) {
        this.dataChat = dataChat;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.chat_item_left, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(itemView);

        return chatViewHolder;
    }

    //Toast.makeText(context, dataChat.get(chatViewHolder.getBindingAdapterPosition()).getMessage(), Toast.LENGTH_SHORT).show();


    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = dataChat.get(position);
        holder.setIvProfilePicture(" ");
        holder.setTvChat(chat.getMessage());
        //chat.getMessage()
    }

    @Override
    public int getItemCount() {
        return this.dataChat.size();
    }

}

/*
    @Override
    public int getItemViewType(int position) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Left message, receiver
        if (dataChat.get(position).getReceiver().equals(user.getUid())) {
            return 0;
        } else { //Right message, user
            return 1;
        }
    }*/
/*
        if (viewType == 1) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.chat_item_right, parent, false);
            ChatViewHolder chatViewHolder = new ChatViewHolder(itemView, 1);
            return chatViewHolder;
        } else {

        }*/