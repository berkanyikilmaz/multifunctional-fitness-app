package com.example.multifunctionalfitnessapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.TimeInterval;

public class TimeIntervalContainerAdapter extends RecyclerView.Adapter<TimeIntervalContainerAdapter.MyViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;



    Context context;
    TimeInterval timeIntervals[];

    public TimeIntervalContainerAdapter(Context context, TimeInterval[] timeIntervals, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.timeIntervals = timeIntervals;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public TimeIntervalContainerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.time_interval_panel, parent, false);
        return new TimeIntervalContainerAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeIntervalContainerAdapter.MyViewHolder holder, int position) {
        holder.timeIntervalInfo.setText( (position+1) + ") " + timeIntervals[position].getStartingHour() + " - " + (timeIntervals[position].getStartingHour() + 1));//+ ((PersonTimeInterval)timeIntervals[position]).appointedFacility);
    }

    @Override
    public int getItemCount() {
        return this.timeIntervals.length;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView fitnessBuddyInfo;
        TextView timeIntervalInfo;
        TextView facilityInfo;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            timeIntervalInfo = (TextView) itemView.findViewById(R.id.timeIntervalInformation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if ( position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (recyclerViewInterface != null){
                        int position = getAdapterPosition();
                        if ( position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemLongClick(position);
                        }
                    }
                    return true;
                }
            });
        }
    }
}
