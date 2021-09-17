package com.mobdeve.s11.manuel.tang.strayhaven;

import android.content.Intent;
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

        messageViewHolder.getLlMessage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);

                intent.putExtra(Keys.KEY_MESSAGE_IMAGE.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getImageId());
                intent.putExtra(Keys.KEY_MESSAGE_USERNAME.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUsername());

                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getIvMessagePicture().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);

                intent.putExtra(Keys.KEY_MESSAGE_IMAGE.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getImageId());
                intent.putExtra(Keys.KEY_MESSAGE_USERNAME.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUsername());

                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getTvProfilename().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);

                intent.putExtra(Keys.KEY_MESSAGE_IMAGE.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getImageId());
                intent.putExtra(Keys.KEY_MESSAGE_USERNAME.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUsername());

                v.getContext().startActivity(intent);
            }
        });

        messageViewHolder.getTvUsername().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ChatActivity.class);

                intent.putExtra(Keys.KEY_MESSAGE_IMAGE.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getImageId());
                intent.putExtra(Keys.KEY_MESSAGE_USERNAME.name(), dataMessage.get(messageViewHolder.getBindingAdapterPosition()).getUsername());

                v.getContext().startActivity(intent);
            }
        });
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
