package com.example.multifunctionalfitnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyViewHolder>{


    public RecyclerViewInterface recyclerViewInterface;
    public ArrayList<TimeInterval> timeintervals;
    public AdapterClass(ArrayList<TimeInterval> timeintervals ){
        this.timeintervals = timeintervals;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView intervalText;

        public MyViewHolder(final View view,RecyclerViewInterface recyclerViewInterface){
            super(view);
            intervalText = view.findViewById(R.id.timeIntervalInformation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            });
        }

    }



    @NonNull
    @Override
    public AdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_interval_panel,parent,false);
        return new MyViewHolder(itemView,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClass.MyViewHolder holder, int position) {
        String hours = "the appointment starting "+timeintervals.get(position).getStartingHour()+"";
        holder.intervalText.setText(hours);

    }

    @Override
    public int getItemCount() {
        return timeintervals.size();
    }
}
