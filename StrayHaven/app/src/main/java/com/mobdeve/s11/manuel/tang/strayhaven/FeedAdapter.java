package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FeedAdapter extends RecyclerView.Adapter<FeedViewHolder> {

    private ArrayList<Feed> dataFeed;

    public FeedAdapter(ArrayList<Feed> dataFeed){
        this.dataFeed = dataFeed;
    }
    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.feed_template, parent, false);
        FeedViewHolder feedViewHolder = new FeedViewHolder(itemView);
        /*
        feedViewHolder.getClFeed().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),)
            }
        });*/
        return feedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Feed currentFeed = this.dataFeed.get(position);
        holder.setTvFeedUser(currentFeed.getUsername());
        holder.setTvFeedLocation(currentFeed.getLocation());
        holder.setTvFeedType(currentFeed.getType());
        holder.setIvFeedPicture(currentFeed.getImageId());

    }

    @Override
    public int getItemCount() {
        return this.dataFeed.size();
    }
}
