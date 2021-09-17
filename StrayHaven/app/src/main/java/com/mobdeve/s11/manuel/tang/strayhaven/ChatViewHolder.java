package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView tvChat;
    public ImageView ivLeftProfilePicture;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        this.tvChat = itemView.findViewById(R.id.tv_chat_message);
        this.ivLeftProfilePicture = itemView.findViewById(R.id.iv_chat_left_user_pic);
    }
}
