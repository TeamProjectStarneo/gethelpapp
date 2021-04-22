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


import com.example.gethelpapp.db.model.Inbox;
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



public class InboxRecyclerAdapter extends RecyclerView.Adapter<InboxRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    static interface ActionCallback {
        void onLongClickListener(Inbox Inbox );


    }
    Uri selectedImageUri;
    private Context context;
    private List<Inbox> inboxList;
    Inbox Inbox;
    static ActionCallback mActionCallbacks;

    InboxRecyclerAdapter(Context context, List<Inbox> inboxList) {
        this.context = context;
        this.inboxList = inboxList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_inbox, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Inbox = inboxList.get(position);

        holder.bindData(position);
        TextView senderView;
        TextView receiverView;

        String job = Inbox.getSpecialistJob();
        String name = Inbox.getSpecialistName();
        String date = Inbox.getLastmessage();

        if(Inbox.getLastmessage()==null){
            holder.lastMessage.setText("No messages");
        }else{
            holder.lastMessage.setText(date);
        }

        holder.specialistJob.setText(job);
        holder.specialistName.setText(name);


    }

    @Override
    //getting amount of rows in assignmentlist
    public int getItemCount() {
        return inboxList.size();
    }

    void updateData(List<Inbox> inbox) {
        this.inboxList = inboxList;
        notifyDataSetChanged();
    }

    //View Holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView specialistName;
        public TextView specialistJob;
        public TextView lastMessage;
        public ImageView imageView;




        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);


            specialistName = itemView.findViewById(R.id.doctorName);
            specialistJob = itemView.findViewById(R.id.doctorJob);
            lastMessage = itemView.findViewById(R.id.sentMessage);
            //imageView= itemView.findViewById(R.id.imageView);



        }
        //binding data to the textviews
        void bindData( int position) {


            //String name = String.valueOf(specialist.getName());
            // helperName.setText(name);






        }

        @Override
        public void onClick(View view) {
            if (mActionCallbacks != null) {
                mActionCallbacks.onLongClickListener(inboxList.get(getAdapterPosition()));
            }

        }
    }

    static void addActionCallback(InboxRecyclerAdapter.ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }
}
