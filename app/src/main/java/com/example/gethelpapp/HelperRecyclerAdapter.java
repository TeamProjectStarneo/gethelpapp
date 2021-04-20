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



public class HelperRecyclerAdapter extends RecyclerView.Adapter<HelperRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    static interface ActionCallback {
        void onLongClickListener(Specialist specialist );


    }
    Uri selectedImageUri;
    private Context context;
    private List<Specialist> specialistList;
    Specialist specialist;
    static ActionCallback mActionCallbacks;

    HelperRecyclerAdapter(Context context, List<Specialist> specialistList) {
        this.context = context;
        this.specialistList = specialistList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_recycler_helpers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        specialist = specialistList.get(position);

        holder.bindData(position);
        String job = specialist.getJob();
        holder.helperJob.setText(job);

        String name = String.valueOf(specialist.getName());
        holder.helperName.setText(name);

        //holder.imageView.setImageDrawable();
        String path = specialist.getImage();
        try {
            File f=new File(path);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));

            holder.imageView.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    //getting amount of rows in assignmentlist
    public int getItemCount() {
        return specialistList.size();
    }

    void updateData(List<Specialist> specialists) {
        this.specialistList = specialists;
        notifyDataSetChanged();
    }

    //View Holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView helperName;
        public TextView helperJob;
        public ImageView imageView;




        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);


            helperName = itemView.findViewById(R.id.helperName);
            helperJob = itemView.findViewById(R.id.helperJob);
            imageView= itemView.findViewById(R.id.imageView);



        }
        //binding data to the textviews
        void bindData( int position) {

            //specialist = specialistList.get(position);

           // String job = specialist.getJob();
            //helperJob.setText(job);

            //String name = String.valueOf(specialist.getName());
           // helperName.setText(name);






        }

        @Override
        public void onClick(View view) {
            if (mActionCallbacks != null) {
                mActionCallbacks.onLongClickListener(specialistList.get(getAdapterPosition()));
            }

        }
    }

     static void addActionCallback(ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }
}
