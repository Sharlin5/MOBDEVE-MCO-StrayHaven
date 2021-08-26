package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class FeedViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivFeedPicture;
    public TextView tvFeedLocation;
    public TextView tvFeedUser;
    public TextView tvFeedType;
    public ConstraintLayout clFeed;



    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivFeedPicture = itemView.findViewById(R.id.iv_feed_picture);
        this.tvFeedUser = itemView.findViewById(R.id.tv_feed_username);
        this.tvFeedType = itemView.findViewById(R.id.tv_feed_request_type);
        this.tvFeedLocation = itemView.findViewById(R.id.tv_feed_user_loc);
        this.clFeed = itemView.findViewById(R.id.cl_feed_post);
    }

    public void setIvFeedPicture(int picture) {
        this.ivFeedPicture.setImageResource(picture);
    }

    public void setTvFeedUser(String name){
        this.tvFeedUser.setText(name);
    }

    public void setTvFeedLocation(String location){
        this.tvFeedLocation.setText(location);
    }

    public void setTvFeedType(String feedType){
        this.tvFeedType.setText(feedType);
    }

    public ConstraintLayout getClFeed(){
        return this.clFeed;
    }
}
