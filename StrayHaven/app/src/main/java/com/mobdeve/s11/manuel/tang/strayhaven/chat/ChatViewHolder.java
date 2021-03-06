package com.mobdeve.s11.manuel.tang.strayhaven.chat;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s11.manuel.tang.strayhaven.R;
import com.squareup.picasso.Picasso;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView tvChatLeft, tvChatRight;
    public ImageView ivProfilePictureLeft;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);
        this.tvChatLeft = itemView.findViewById(R.id.tv_left_chat_message);
        this.tvChatRight = itemView.findViewById(R.id.tv_right_chat_message);
        this.ivProfilePictureLeft = itemView.findViewById(R.id.iv_chat_left_user_pic);
    }


    public void setTvChat(String tvChat, int viewType) {
        if(viewType == 1) {
            this.tvChatRight.setText(tvChat);
        } else {
            this.tvChatLeft.setText(tvChat);
        }
    }

    public void setIvProfilePicture(String profilePicture) {
            if (profilePicture.equals(" ")){
                ivProfilePictureLeft.setImageResource(R.drawable.icon_default_user);
            } else {
                Picasso.get().load(profilePicture).into(ivProfilePictureLeft);
            }

    }
}
