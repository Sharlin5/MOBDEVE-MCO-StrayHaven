package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RightChatViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivProfile;
    private TextView tvChat;

    public RightChatViewHolder(@NonNull View itemView) {
        super(itemView);
        ivProfile = itemView.findViewById(R.id.iv_chat_right_user_pic);
        tvChat = itemView.findViewById(R.id.tv_right_chat_message);
    }

    public void setTvChat(TextView tvChat) {
        this.tvChat = tvChat;
    }

    public void setIvProfile(ImageView ivProfile) {
        this.ivProfile = ivProfile;
    }

    public TextView getTvChat() {
        return tvChat;
    }

    public ImageView getIvProfile() {
        return ivProfile;
    }
}
