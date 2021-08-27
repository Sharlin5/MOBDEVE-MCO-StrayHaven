package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerViewHolder> {

    private ArrayList<Tracker> dataTracker;

    public TrackerAdapter(ArrayList<Tracker> dataTracker){
        this.dataTracker = dataTracker;
    }

    @NonNull
    @Override
    public TrackerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.tracker_template, parent, false);
        TrackerViewHolder trackerViewHolder = new TrackerViewHolder(itemView);

        return trackerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrackerViewHolder holder, int position) {
        Tracker currentTracker = this.dataTracker.get(position);
        holder.setIvTrackerPicture(currentTracker.getImageId());
        holder.setTvDate(currentTracker.getDate().toStringFull());
        holder.setTvType(currentTracker.getPosttype());
    }

    @Override
    public int getItemCount() {
        return this.dataTracker.size();
    }
}
