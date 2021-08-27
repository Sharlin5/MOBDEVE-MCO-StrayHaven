package com.mobdeve.s11.manuel.tang.strayhaven;

import android.app.Notification;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        return notifViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        Notif currentNotif = this.dataNotif.get(position);
        holder.setTvUsername(currentNotif.getProfilename());
        holder.setIvNotifPicture(currentNotif.getImageId());
        holder.setTvDate(currentNotif.getCustomDate().toStringFull());
    }

    @Override
    public int getItemCount() {
        return this.dataNotif.size();
    }
}
