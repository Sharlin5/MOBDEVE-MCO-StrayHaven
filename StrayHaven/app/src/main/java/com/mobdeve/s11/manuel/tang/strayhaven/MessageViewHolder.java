package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivMessagePicture;
    public TextView tvUsername;
    public TextView tvProfilename;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivMessagePicture = itemView.findViewById(R.id.iv_chat_list_user_pic);
        this.tvUsername = itemView.findViewById(R.id.tv_chat_list_username);
        this.tvProfilename = itemView.findViewById(R.id.tv_chat_list_name);

    }

    public void setIvMessagePicture(int picture) {
        this.ivMessagePicture.setImageResource(picture);
    }

    public void setTvUsername(String username){
        this.tvUsername.setText(username);
    }

    public void setTvProfilename(String profilename){
        this.tvProfilename.setText(profilename);
    }
}
