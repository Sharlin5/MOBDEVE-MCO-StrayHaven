package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView tvChat;
    public ImageView ivProfilePicture;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvChat = itemView.findViewById(R.id.tv_left_chat_message);
        this.ivProfilePicture = itemView.findViewById(R.id.iv_chat_left_user_pic);
    }

    public void setTvChat(String tvChat) {
        this.tvChat.setText(tvChat);
    }

    public void setIvProfilePicture(String profilePicture) {
            if (profilePicture.equals(" ")){
                ivProfilePicture.setImageResource(R.drawable.icon_default_user);
            } else {
                Picasso.get().load(profilePicture).into(ivProfilePicture);
            }

    }
}
