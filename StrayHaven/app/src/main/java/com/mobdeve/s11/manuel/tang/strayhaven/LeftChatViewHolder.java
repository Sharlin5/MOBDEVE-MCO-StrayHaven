package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LeftChatViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivProfpic;
    private TextView tvChat;

    public LeftChatViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivProfpic = itemView.findViewById(R.id.iv_chat_left_user_pic);
        this.tvChat = itemView.findViewById(R.id.tv_left_chat_message);
    }

    public void setIvProfpic(ImageView ivProfpic) {
        this.ivProfpic = ivProfpic;
    }

    public void setTvChat(TextView tvChat) {
        this.tvChat = tvChat;
    }

    public TextView getTvChat() {
        return tvChat;
    }

    public ImageView getIvProfpic() {
        return ivProfpic;
    }
}
