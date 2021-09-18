package com.mobdeve.s11.manuel.tang.strayhaven.message;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mobdeve.s11.manuel.tang.strayhaven.R;
import com.squareup.picasso.Picasso;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public ImageView ivMessagePicture;
    public TextView tvUsername;
    public TextView tvProfilename;
    public ConstraintLayout clMessage;
    public LinearLayout llMessage;
    public ImageButton ibMessageDelete;

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.ivMessagePicture = itemView.findViewById(R.id.iv_chat_list_user_pic);
        this.tvUsername = itemView.findViewById(R.id.tv_chat_list_username);
        this.tvProfilename = itemView.findViewById(R.id.tv_chat_list_name);
        this.clMessage = itemView.findViewById(R.id.cl_chat_list);
        this.llMessage = itemView.findViewById(R.id.ll_chat_list_post_details);
        this.ibMessageDelete = itemView.findViewById(R.id.ib_chat_list_delete);
    }

    public void setIvMessagePicture(String picture) {
        if (picture.equals(" ")){
            ivMessagePicture.setImageResource(R.drawable.icon_default_user);
        } else {
            Picasso.get().load(picture).into(ivMessagePicture);
        }
    }

    public void setTvUsername(String username){
        this.tvUsername.setText(username);
    }

    public void setTvProfilename(String profilename){
        this.tvProfilename.setText(profilename);
    }

    public ConstraintLayout getClMessage(){
        return this.clMessage;
    }

    public LinearLayout getLlMessage(){
        return this.llMessage;
    }

    public ImageView getIvMessagePicture(){
        return this.ivMessagePicture;
    }

    public TextView getTvUsername(){
        return this.tvUsername;
    }

    public TextView getTvProfilename(){
        return this.tvProfilename;
    }

    public ImageButton getIbMessageDelete() {
        return ibMessageDelete;
    }
}
