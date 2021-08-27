package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TrackerViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivTrackerPicture;
    public TextView tvType;
    public TextView tvDate;

    public TrackerViewHolder(@NonNull View itemView) {
        super(itemView);

        this.ivTrackerPicture = itemView.findViewById(R.id.iv_tracker_post);
        this.tvType = itemView.findViewById(R.id.tv_tracker_type);
        this.tvDate = itemView.findViewById(R.id.tv_tracker_date);

    }

    public void setIvTrackerPicture(int picture) {
        this.ivTrackerPicture.setImageResource(picture);
    }

    public void setTvType(String type) {
        this.tvType.setText(type);
    }

    public void setTvDate(String date) {
        this.tvDate.setText(date);
    }
}
