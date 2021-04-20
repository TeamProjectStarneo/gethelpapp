package com.example.gethelpapp;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.gethelpapp.db.model.Reminder;
import com.example.gethelpapp.db.model.Specialist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



public class ReminderRecyclerAdapter extends RecyclerView.Adapter<ReminderRecyclerAdapter.ViewHolder> {

    //Interface for callbacks
    static interface ActionCallback {
        void onLongClickListener(Reminder reminder );


    }

    private Context context;
    private List<Reminder> reminderList;

    static ActionCallback mActionCallbacks;

    ReminderRecyclerAdapter(Context context, List<Reminder> reminderList) {
        this.context = context;
        this.reminderList = reminderList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_reminders, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindData(position);
    }

    @Override
    //getting amount of rows in assignmentlist
    public int getItemCount() {
        return reminderList.size();
    }

    void updateData(List<Reminder> reminders) {
        this.reminderList = reminders;
        notifyDataSetChanged();
    }

    //View Holder
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView whyLabel1;
        private TextView doctorLabel;
        private TextView dateLabel1;
        private ImageView imageView;




        ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);


            dateLabel1 = itemView.findViewById(R.id.dateLabel1);
            doctorLabel = itemView.findViewById(R.id.doctorLabel);


            whyLabel1 = itemView.findViewById(R.id.dateLabel1);
            imageView= itemView.findViewById(R.id.imageView);



        }
        //binding data to the textviews
        void bindData(int position) {

            Reminder reminder = reminderList.get(position);

            String why = String.valueOf(reminder.getWhy());
            Log.i("why",why);
            if(why.length()>1) {
                whyLabel1.setText(why);
            }
            String name = String.valueOf(reminder.getDoctorName());
            Log.i("name",name);
            if(name.length()>1) {
               doctorLabel.setText(name);
            }

            String date = String.valueOf(reminder.getDate());
            Log.i("Date",date);
            if(date.length()>1){
                //dateLabel1.setText(date);
            }





        }

        @Override
        public void onClick(View view) {
            if (mActionCallbacks != null) {
                mActionCallbacks.onLongClickListener(reminderList.get(getAdapterPosition()));
            }

        }
    }

    static void addActionCallback(ActionCallback actionCallbacks) {
        mActionCallbacks = actionCallbacks;
    }
}
