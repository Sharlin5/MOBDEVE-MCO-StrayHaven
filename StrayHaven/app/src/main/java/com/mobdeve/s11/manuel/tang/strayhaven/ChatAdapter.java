package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private ArrayList<Chat> dataChat;
    private Context context;
    private String imageUrl;

    public ChatAdapter(ArrayList<Chat> dataChat ){
        this.dataChat = dataChat;
    }

    public ChatAdapter(ChatActivity context, ArrayList<Chat> dataChat, String imageUrl) {
        this.dataChat = dataChat;
        this.context = context;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.chat_item_right, parent, false);
            ChatViewHolder chatViewHolder = new ChatViewHolder(itemView);
            return chatViewHolder;
        } else {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.chat_item_left, parent, false);
            ChatViewHolder chatViewHolder = new ChatViewHolder(itemView);
            return chatViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        Chat chat = dataChat.get(position);

        holder.tvChat.setText(chat.getMessage());

        if (imageUrl.equals(" ")) {
            holder.ivLeftProfilePicture.setImageResource(R.drawable.icon_default_user);
        } else {
            Picasso.get().load(imageUrl).into(holder.ivLeftProfilePicture);
        }
    }

    @Override
    public int getItemCount() {
        return dataChat.size();
    }

    @Override
    public int getItemViewType(int position) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // Left message, receiver
        if (dataChat.get(position).getSender().equals(user.getUid())) {
            return 0;
        } else { //Right message, user
            return 1;
        }
    }
}
