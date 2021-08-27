package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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


        feedViewHolder.getClFeed().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewPostActivity.class);

                intent.putExtra(Keys.KEY_FEED_USERNAME.name(), dataFeed.get(feedViewHolder.getBindingAdapterPosition()).getUsername());
                intent.putExtra(Keys.KEY_FEED_CAPTION.name(), dataFeed.get(feedViewHolder.getBindingAdapterPosition()).getCaption());
                intent.putExtra(Keys.KEY_FEED_LOCATION.name(), dataFeed.get(feedViewHolder.getBindingAdapterPosition()).getLocation());
                intent.putExtra(Keys.KEY_FEED_TYPE.name(), dataFeed.get(feedViewHolder.getBindingAdapterPosition()).getType());
                intent.putExtra(Keys.KEY_FEED_IMAGE.name(), dataFeed.get(feedViewHolder.getBindingAdapterPosition()).getImageId());

                v.getContext().startActivity(intent);
            }
        });

        feedViewHolder.getTvFeedUser().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                v.getContext().startActivity(intent);

            }
        });

        feedViewHolder.getIvFeedProfile().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentprofile = new Intent(v.getContext(), ProfileActivity.class);
                v.getContext().startActivity(intentprofile);
            }
        });

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
