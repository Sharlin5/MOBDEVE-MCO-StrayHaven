package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class TrackerViewHolder extends RecyclerView.ViewHolder {
    public ImageView ivTrackerPicture;
    public TextView tvType;
    public TextView tvDate;
    public Switch swStatus;
    public ImageButton ibTrackerDelete;

    public TrackerViewHolder(@NonNull View itemView) {
        super(itemView);

        this.ivTrackerPicture = itemView.findViewById(R.id.iv_tracker_post);
        this.tvType = itemView.findViewById(R.id.tv_tracker_type);
        this.tvDate = itemView.findViewById(R.id.tv_tracker_date);
        this.swStatus = itemView.findViewById(R.id.sw_tracker_status);
        this.ibTrackerDelete = itemView.findViewById(R.id.ib_tracker_delete);
    }

    public void setIvTrackerPicture(int picture) {
        this.ivTrackerPicture.setImageResource(picture);
    }

    public void setIvTrackerPicture(String pictureUrl){
        Picasso.get().load(pictureUrl).into(ivTrackerPicture);
    }

    public void setTvType(String type) {
        this.tvType.setText(type);
    }

    public void setTvDate(String date) {
        this.tvDate.setText(date);
    }

    public void setSwStatus(String status) {
        if (status.equals("false")){
            this.swStatus.setChecked(true);
        } else {
            this.swStatus.setChecked(false);
        }
    }

    public Switch getSwStatus(){
        return this.swStatus;
    }

    public ImageButton getIbTrackerDelete(){
        return this.ibTrackerDelete;
    }
}
