package com.example.multifunctionalfitnessapp.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.FacilityOwner;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FacilityContainerAdapter extends RecyclerView.Adapter<FacilityContainerAdapter.MyViewHolder> {

    private final RecyclerViewInterface recyclerViewInterface;

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    Context context;
    FacilityOwner facilityOwner;
    UserData userData;

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
        int timeIntervalIndex;
        int dayIndex;
        userData = UserData.getInstance();
        firebaseManager.getCompleteSnapshot(new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                userData.setFacilityOwnerFromDatabase(snapshot);
                facilityOwner = userData.facilityOwner;
                Log.d("ownerName", facilityOwner.getUsername());
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
        holder.facilityTitle.setText(facilityOwner.getFacilities().get(position).getName());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Calendar.getInstance().setTimeZone(Calendar.getInstance().getTimeZone());
        Date currentTime = Calendar.getInstance().getTime();
        String currentDate = simpleDateFormat.format(currentTime);
        timeIntervalIndex = Integer.parseInt(currentDate.substring(11,13));
        dayIndex = Integer.parseInt(currentDate.substring(0,2)) % 7;
        holder.timePeriod.setText(currentDate);
        holder.quota.setText("Quota: " + facilityOwner.getFacilities().get(position).getName());
        holder.appointments.setText("Appointments: " + facilityOwner.getFacilities().get(position).getName());
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
