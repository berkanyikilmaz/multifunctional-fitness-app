package com.example.multifunctionalfitnessapp.activities;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.multifunctionalfitnessapp.Constants;
import com.example.multifunctionalfitnessapp.FirebaseManager;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.OnGetDataListener;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;
import com.example.multifunctionalfitnessapp.RecyclerViewInterface;
import com.example.multifunctionalfitnessapp.TimeInterval;
import com.example.multifunctionalfitnessapp.User;
import com.example.multifunctionalfitnessapp.UserData;
import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FitnessBuddyContainerAdapter extends RecyclerView.Adapter<FitnessBuddyContainerAdapter.MyViewHolder> {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    private final RecyclerViewInterface recyclerViewInterface;

    ArrayList<PersonTimeInterval> intervals;

    UserData userData;

    Context context;
    NormalUser normalUser;
    PersonTimeInterval[] timeIntervals;

    public FitnessBuddyContainerAdapter(Context context, NormalUser normalUser, RecyclerViewInterface recyclerViewInterface){
        this.context = context;
        this.normalUser = normalUser;
        this.recyclerViewInterface = recyclerViewInterface;
        Log.d("hey", "container");

        userData = UserData.getInstance();
        intervals = userData.fitnessBuddyTimeIntervals;
    }

    @NonNull
    @Override
    public FitnessBuddyContainerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.find_fitness_budy_1, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FitnessBuddyContainerAdapter.MyViewHolder holder, int position) {
        if (intervals.size() == 0) {
            Log.d("empty", "empty");
            return;
        }
        PersonTimeInterval interval = intervals.get(position);

        Log.d("hey", "holder");
        firebaseManager.getUserSnapshot(interval.fitnessBuddy.getUsername(), new OnGetDataListener() {
            @Override
            public void onSuccess(DataSnapshot snapshot) {
                String fitnessBuddyPhoneNo = snapshot.child("phoneNumber").getValue(String.class);
                String fitnessBuddyName = snapshot.child("name").getValue(String.class);
                String fitnessBuddySurname = snapshot.child("surname").getValue(String.class);

                holder.NameText.setText(fitnessBuddyName + " " + fitnessBuddySurname);
                holder.DateText.setText(Constants.DAYS[interval.dailySchedule.day]);
                holder.PhoneNumberText.setText(fitnessBuddyPhoneNo);
                holder.HourText.setText(interval.getStartingHour() + " - " + (interval.getStartingHour() + 1));
                holder.FacilityText.setText(interval.appointedFacility.getName());
            }

            @Override
            public void onStart() {

            }

            @Override
            public void onFailure() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return this.intervals.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView NameText;
        TextView DateText;
        TextView HourText;
        TextView PhoneNumberText;
        TextView FacilityText;

        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            NameText = itemView.findViewById(R.id.fitnessBuddyNameText);
            DateText = itemView.findViewById(R.id.fitnessBuddyDateText);
            HourText = itemView.findViewById(R.id.fitnessBuddyHourText);
            PhoneNumberText = itemView.findViewById(R.id.fitnessBuddyPhoneNumber);
            FacilityText = itemView.findViewById(R.id.fitnessBuddyFacilityText);

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
