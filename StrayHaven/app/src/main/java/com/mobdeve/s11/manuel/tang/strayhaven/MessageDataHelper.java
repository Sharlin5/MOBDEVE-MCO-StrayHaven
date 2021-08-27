package com.mobdeve.s11.manuel.tang.strayhaven;

import java.util.ArrayList;

public class MessageDataHelper {

    public ArrayList<Message> loadMessageData(){
        ArrayList<Message> data = new ArrayList<Message>();
        data.add(new Message("maryann", "Mary", R.drawable.picture_feature1));
        data.add(new Message("melissaaa", "Melisa", R.drawable.picture_feature3));
        data.add(new Message("johnas", "John", R.drawable.picture_feature5));

        return data;
    }

}
