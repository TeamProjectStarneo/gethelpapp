package com.example.gethelpapp;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gethelpapp.db.model.Specialist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class HelperRecyclerAdapter extends RecyclerView.Adapter<HelperRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    static interface ActionCallback {
        void onLongClickListener(Specialist specialist );


    }

    private Context context;
    private List<Specialist> specialistList;

    static ActionCallback mActionCallbacks;

    HelperRecyclerAdapter(Context context, List<Specialist> specialistList) {
        this.context = context;
        this.specialistList = specialistList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_helpers, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindData(position);
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
        private TextView helperName;
        private TextView helperJob;
        private ImageView imageView;




        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);


            helperName = itemView.findViewById(R.id.helperName);
            helperJob = itemView.findViewById(R.id.helperJob);
            imageView= itemView.findViewById(R.id.imageView);



        }
        //binding data to the textviews
        void bindData(int position) {

            Specialist specialist = specialistList.get(position);

            String job = specialist.getJob();
            helperJob.setText(job);

            String name = String.valueOf(specialist.getName());
            helperName.setText(name);




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
