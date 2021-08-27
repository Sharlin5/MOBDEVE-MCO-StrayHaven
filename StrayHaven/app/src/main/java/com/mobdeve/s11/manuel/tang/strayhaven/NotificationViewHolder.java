package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivNotifPicture;
    public TextView tvUsername;
    public TextView tvDate;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivNotifPicture = itemView.findViewById(R.id.iv_notif_icon_display);
        this.tvUsername = itemView.findViewById(R.id.tv_notif_text_name);
        this.tvDate = itemView.findViewById(R.id.tv_notif_text_date);
    }

    public void setIvNotifPicture(int picture) {
        this.ivNotifPicture.setImageResource(picture);
    }

    public void setTvUsername(String username) {
        this.tvUsername.setText(username);
    }

    public void setTvDate(String date) {
        this.tvDate.setText(date);
    }

}
