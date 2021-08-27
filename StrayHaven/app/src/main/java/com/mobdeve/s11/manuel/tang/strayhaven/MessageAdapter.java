package com.mobdeve.s11.manuel.tang.strayhaven;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private ArrayList<Message> dataMessage;

    public MessageAdapter(ArrayList<Message> dataMessage){
        this.dataMessage = dataMessage;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.message_template, parent, false);
        MessageViewHolder messageViewHolder = new MessageViewHolder(itemView);


        return messageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        Message currentMessage = this.dataMessage.get(position);
        holder.setTvProfilename(currentMessage.getProfilename());
        holder.setTvUsername("@" + currentMessage.getUsername());
        holder.setIvMessagePicture(currentMessage.getImageId());
    }

    @Override
    public int getItemCount() {
        return this.dataMessage.size();
    }
}
