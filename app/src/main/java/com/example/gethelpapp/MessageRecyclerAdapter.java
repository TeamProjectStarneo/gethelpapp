package com.example.gethelpapp;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gethelpapp.db.model.Messages;
import com.example.gethelpapp.db.model.Specialist;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class MessageRecyclerAdapter extends RecyclerView.Adapter<MessageRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    static interface ActionCallback {
        void onLongClickListener(Messages messages  );


    }
    Uri selectedImageUri;
    private Context context;
    private List<Messages> messageList;
    Messages messages;
    static ActionCallback mActionCallbacks;

    MessageRecyclerAdapter(Context context, List<Messages> messageList) {
        this.context = context;
        this.messageList = messageList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_messages, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        messages = messageList.get(position);



        String job = messages.getMessage();

        if(messages.getSender()==true){

            holder.receiverView.setText(job);
            holder.senderView.setText("Sent");
        }
        else{
            holder.receiverView.setText(job);
            holder.senderView.setText("Received");
        }

        holder.bindData(position);

    }

    @Override
    //getting amount of rows in assignmentlist
    public int getItemCount() {
        return messageList.size();
    }

    void updateData(List<Messages> messages) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    //View Holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView receiverView;
        public TextView senderView;
        public ImageView imageView;




        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);


            receiverView = itemView.findViewById(R.id.recieveMsg);
            senderView = itemView.findViewById(R.id.sentMessage);



        }
        //binding data to the textviews
        void bindData( int position) {


            //String name = String.valueOf(specialist.getName());
            // helperName.setText(name);






        }

        @Override
        public void onClick(View view) {
            if (mActionCallbacks != null) {

            }

        }
    }

    static void addActionCallback(ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }
}
