package com.mobdeve.s11.manuel.tang.strayhaven.notification;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s11.manuel.tang.strayhaven.R;
import com.squareup.picasso.Picasso;

public class NotificationViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivNotifPicture;
    public TextView tvUsername;
    public TextView tvDate;
    public TextView tvNotif;
    public ConstraintLayout clNotif;

    public NotificationViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivNotifPicture = itemView.findViewById(R.id.iv_notif_icon_display);
        this.tvUsername = itemView.findViewById(R.id.tv_notif_text_name);
        this.tvDate = itemView.findViewById(R.id.tv_notif_text_date);
        this.tvNotif = itemView.findViewById(R.id.tv_notif_text_row_2);
        this.clNotif = itemView.findViewById(R.id.cl_notif_feed);
    }

    public void setIvNotifPicture(String picture) {
        Picasso.get().load(picture).into(ivNotifPicture);
    }

    public void setTvUsername(String username) {
        this.tvUsername.setText(username);
    }

    public void setTvDate(String date) {
        this.tvDate.setText(date);
    }

    public void setTvNotif(String notification){
        this.tvNotif.setText(notification);
    }

    public ConstraintLayout getClNotif() {
        return clNotif;
    }
}
