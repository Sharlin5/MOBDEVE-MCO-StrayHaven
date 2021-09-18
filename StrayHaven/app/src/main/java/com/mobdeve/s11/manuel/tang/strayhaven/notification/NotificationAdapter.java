package com.mobdeve.s11.manuel.tang.strayhaven.notification;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s11.manuel.tang.strayhaven.misc.Keys;
import com.mobdeve.s11.manuel.tang.strayhaven.R;
import com.mobdeve.s11.manuel.tang.strayhaven.profile.ViewPosterActivity;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationViewHolder> {

    private ArrayList<Notif> dataNotif;

    public NotificationAdapter(ArrayList<Notif> dataNotif){
        this.dataNotif = dataNotif;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.notification_template, parent, false);
        NotificationViewHolder notifViewHolder = new NotificationViewHolder(itemView);

        notifViewHolder.getClNotif().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewPosterActivity.class);
                intent.putExtra(Keys.KEY_POSTER_ID.name(), dataNotif.get(notifViewHolder.getBindingAdapterPosition()).getNotifierKey());
                v.getContext().startActivity(intent);
            }
        });

        return notifViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notif currentNotif = this.dataNotif.get(position);
        holder.setTvUsername(currentNotif.getNotifierName());
        holder.setIvNotifPicture(currentNotif.getNotifUrl());
        holder.setTvDate(currentNotif.getDate());
        holder.setTvNotif(currentNotif.getNotification());
    }

    @Override
    public int getItemCount() {
        return this.dataNotif.size();
    }
}
