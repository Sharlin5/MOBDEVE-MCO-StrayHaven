package com.mobdeve.s11.manuel.tang.strayhaven;

import android.media.Image;
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
    public ImageView ivFeedProfile;


    public FeedViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivFeedPicture = itemView.findViewById(R.id.iv_feed_picture);
        this.tvFeedUser = itemView.findViewById(R.id.tv_feed_username);
        this.tvFeedType = itemView.findViewById(R.id.tv_feed_request_type);
        this.tvFeedLocation = itemView.findViewById(R.id.tv_feed_user_loc);
        this.clFeed = itemView.findViewById(R.id.cl_feed_post);
        this.ivFeedProfile = itemView.findViewById(R.id.iv_feed_user_pic);
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

    public void setIvFeedProfile(int feedProfile){
        this.ivFeedProfile.setImageResource(feedProfile);
    }

    public ConstraintLayout getClFeed(){
        return this.clFeed;
    }

    public TextView getTvFeedUser(){
        return this.tvFeedUser;
    }

    public ImageView getIvFeedProfile(){
        return this.ivFeedProfile;
    }

    public ImageView getIvFeedPicture() {
        return this.ivFeedPicture;
    }
}
