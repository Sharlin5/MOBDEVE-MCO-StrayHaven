package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrackerAdapter extends RecyclerView.Adapter<TrackerViewHolder> {

    private FirebaseDatabase database;
    /*
    private String username;
    private String postUrl;
    private String type;
    private String date;
    private String isdone;
    */
    private ArrayList<Feed> dataTracker;
    private Context context;
    public TrackerAdapter(ArrayList<Feed> dataTracker){
        this.dataTracker = dataTracker;
    }

    public TrackerAdapter(Context context, ArrayList<Feed> dataTracker){
        this.context = context;
        this.dataTracker = dataTracker;
    }


    @NonNull
    @Override
    public TrackerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.tracker_template, parent, false);
        TrackerViewHolder trackerViewHolder = new TrackerViewHolder(itemView);
        //String postKey = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPostKey();

        trackerViewHolder.getIbTrackerDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRequest(trackerViewHolder);
            }
        });

        trackerViewHolder.getSwStatus().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateFirebase(trackerViewHolder, isChecked);
            }
        });
        return trackerViewHolder;
    }

    private void updateFirebase(TrackerViewHolder trackerViewHolder, boolean isChecked){
        this.database = FirebaseDatabase.getInstance();
        DatabaseReference trackerReference = database.getReference(Collections.request.name());
        DatabaseReference notifReference = database.getReference(Collections.notifs.name());
        DatabaseReference likeReference = database.getReference(Collections.likes.name());

        String postKey = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPostKey();
        String posterKey = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPosterKey();
        String postCaption = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getCaption();
        String postLocation = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getLocation();
        String postUrl = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPostUrl();
        String postProfileUrl = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getProfileUrl();
        String postType = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getType();
        String postUsername = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getUsername();
        String postDate = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getDate();
        String isDone;
        String currDate = new CustomDate().toStringFull();


        if (isChecked){
            isDone = "false";
            Feed feed = new Feed(posterKey, postUsername, postProfileUrl, postUrl, postType, postLocation, postCaption, postDate, isDone);
            trackerReference.child(postKey).setValue(feed);
        } else {
            isDone = "true";
            Feed feed = new Feed(posterKey, postUsername, postProfileUrl, postUrl, postType, postLocation, postCaption, postDate, isDone);
            trackerReference.child(postKey).setValue(feed);

            likeReference.child(postKey).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dss: snapshot.getChildren()){
                        String currLiker = dss.getKey();
                        Notif notif = new Notif(postUsername, postUrl, "Set this request as done.", currDate, posterKey);
                        notifReference.child(currLiker).push().setValue(notif);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    private void deleteRequest(TrackerViewHolder trackerViewHolder){
        this.database = FirebaseDatabase.getInstance();
        DatabaseReference trackerReference = database.getReference(Collections.request.name());
        DatabaseReference likeReference = database.getReference(Collections.likes.name());
        DatabaseReference notifReference = database.getReference(Collections.notifs.name());

        //curr post key
        String postKey = dataTracker.get(trackerViewHolder.getBindingAdapterPosition()).getPostKey();

        // notify user

        // delete
        trackerReference.child(postKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
