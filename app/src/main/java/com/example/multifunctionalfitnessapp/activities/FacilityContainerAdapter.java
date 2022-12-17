package com.example.multifunctionalfitnessapp.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.FacilityOwner;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;

public class FacilityContainerAdapter extends RecyclerView.Adapter<FacilityContainerAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    Context context;
    FacilityOwner facilityOwner;

    public FacilityContainerAdapter(Context context, FacilityOwner facilityOwner, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.facilityOwner = facilityOwner;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public FacilityContainerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.facility_panel, parent, false);
        return new MyViewHolder(view,recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FacilityContainerAdapter.MyViewHolder holder, int position) {
        holder.facilityTitle.setText(facilityOwner.getFacilities().get(position).getName());
        holder.timePeriod.setText("09-10"); //should be current time period
        holder.quota.setText("Quota: 8");
        holder.appointments.setText("Appointments: 5");
        //for last two we should get the current time periods quota and appointments
    }

    @Override
    public int getItemCount() {

        return facilityOwner.getFacilities().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView facilityTitle, timePeriod, quota, appointments;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            facilityTitle = itemView.findViewById(R.id.facilityTitle);
            timePeriod =  itemView.findViewById(R.id.timePeriod);
            quota =  itemView.findViewById(R.id.quotaText);
            appointments =  itemView.findViewById(R.id.appointmentsText);

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
