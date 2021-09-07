package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerViewHolder> {

    //private FirebaseDatabase database;

    private FirebaseDatabase database;
    /*
    private String username;
    private String postUrl;
    private String type;
    private String date;
    private String isdone;
    */
    private ArrayList<Feed> dataTracker;
    public TrackerAdapter(ArrayList<Feed> dataTracker){
        this.dataTracker = dataTracker;
    }

    @NonNull
    @Override
    public TrackerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.tracker_template, parent, false);
        TrackerViewHolder trackerViewHolder = new TrackerViewHolder(itemView);

        /*
        database = FirebaseDatabase.getInstance();

        String userkey = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPosterKey();
        String username = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getUsername();
        String profileUrl = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getProfileUrl();
        String postUrl = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPostUrl();
        String type = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getType();
        String location = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getLocation();
        String caption = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getCaption();
        String date = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getDate();

        // database.getReference(Collections.users.name()).child(mAuth.getCurrentUser().getUid())
        //                .setValue(user)

        trackerViewHolder.getSwStatus().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (trackerViewHolder.getSwStatus().isChecked()){
                    String isDone = "false";
                    Feed tracker = new Feed(userkey, username, profileUrl, postUrl, type, location, caption, date, isDone);
                    database.getReference(Collections.request.name()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dss:snapshot.getChildren()){
                                String tempUserkey = dss.child("posterKey").getValue(String.class);
                                String tempUsername = dss.child("username").getValue(String.class);
                                String tempProfileUrl = dss.child("profileUrl").getValue(String.class);
                                String tempPostUrl = dss.child("postUrl").getValue(String.class);
                                String tempType = dss.child("type").getValue(String.class);
                                String tempLocation = dss.child("location").getValue(String.class);
                                String tempCaption = dss.child("caption").getValue(String.class);
                                String tempDate = dss.child("date").getValue(String.class);
                                Feed tempTracker = new Feed(tempUserkey, tempUsername, tempProfileUrl, tempPostUrl, tempType, tempLocation, tempCaption, tempDate, isDone);
                                if (tracker.equals(tempTracker)){
                                    dss.getRef().setValue(tracker);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                } else {
                    String isDone = "true";
                    Feed tracker = new Feed(userkey, username, profileUrl, postUrl, type, location, caption, date, isDone);
                    database.getReference(Collections.request.name()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dss:snapshot.getChildren()){
                                String tempUserkey = dss.child("posterKey").getValue(String.class);
                                String tempUsername = dss.child("username").getValue(String.class);
                                String tempProfileUrl = dss.child("profileUrl").getValue(String.class);
                                String tempPostUrl = dss.child("postUrl").getValue(String.class);
                                String tempType = dss.child("type").getValue(String.class);
                                String tempLocation = dss.child("location").getValue(String.class);
                                String tempCaption = dss.child("caption").getValue(String.class);
                                String tempDate = dss.child("date").getValue(String.class);
                                Feed tempTracker = new Feed(tempUserkey, tempUsername, tempProfileUrl, tempPostUrl, tempType, tempLocation, tempCaption, tempDate, isDone);
                                if (tracker.equals(tempTracker)){
                                    dss.getRef().setValue(tracker);
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        */
        return trackerViewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull TrackerViewHolder holder, int position) {
        Feed currentTracker = this.dataTracker.get(position);
        holder.setIvTrackerPicture(currentTracker.getPostUrl());
        holder.setTvDate(currentTracker.getDate());
        holder.setTvType(currentTracker.getType());
        holder.setSwStatus(currentTracker.getIsDone());
    }

    @Override
    public int getItemCount() {
        return this.dataTracker.size();
    }
}
