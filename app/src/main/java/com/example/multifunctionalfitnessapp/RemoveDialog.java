package com.example.multifunctionalfitnessapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.multifunctionalfitnessapp.activities.LogInActivity;
import com.example.multifunctionalfitnessapp.activities.User_Main_Menu_Activity;
import com.google.firebase.database.DataSnapshot;

public class RemoveDialog extends AppCompatDialogFragment {

    FirebaseManager firebaseManager = FirebaseManager.getInstance();

    private RemoveDialogListener listener;
    private TextView name;
    private PersonTimeInterval interval;

    private NormalUser normalUser;
    private TextView timePeriodText;
    private TextView facilityText;

    private Activity mainView;

    public RemoveDialog(TextView name, PersonTimeInterval interval, Activity view){
        this.name = name;
        this.interval = interval;
        this.mainView = view;

        normalUser = UserData.getInstance().normalUser;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.remove_appointenment_pop_up, null);

        timePeriodText = view.findViewById(R.id.timePeriod);
        facilityText = view.findViewById(R.id.appointmentMessage);

        timePeriodText.setText(Constants.DAYS[interval.dailySchedule.day] + "\n" + interval.startingHour + " - " + (interval.startingHour + 1) );
        facilityText.setText("Appointment in " + interval.appointedFacility.getName());

        TextView title = new TextView(view.getContext());
        title.setGravity(Gravity.CENTER);
        title.setText("Appointment Menu");
        title.setTextSize(28);
        title.setBackgroundColor(Color.RED);
        builder.setCustomTitle(title);

        builder.setView(view).setTitle("Remove Your Appointment").setNegativeButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.applyRemoval(name);
            }
        }).setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //closes dialog nothing is needed
            }
        }).setNeutralButton("Find Fitness Buddy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (interval.fitnessBuddy.getUsername() != null) {
                    Toast.makeText(mainView, "You already have a fitness buddy!", Toast.LENGTH_SHORT).show();
                }
                else {
                    interval.wantsFitnessBuddy = true;
                    firebaseManager.updateUserSchedule(normalUser, interval.dailySchedule.day, interval.startingHour, "wantsFitnessBuddy", true);

                    listener.findFitnessBuddy(interval, new OnGetDataListener() {
                        @Override
                        public void onSuccess(DataSnapshot snapshot) {
                            Log.d("fitness", "fitness buddy found");
                            Log.d("fitness", "fitness buddy: " + interval.fitnessBuddy.getUsername());
                            Toast.makeText(mainView, "We find a fitness buddy for you!!!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onStart() {
                            Log.d("fitness", "searching for fitness buddy");
                        }

                        @Override
                        public void onFailure() {
                            Log.d("fitness", "fitness buddy couldnt found");
                            Toast.makeText(mainView, "No fitness buddy found...", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (RemoveDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ExampleDialogListener");
        }
    }

    public interface RemoveDialogListener{
        void applyRemoval(TextView name);
        void findFitnessBuddy(PersonTimeInterval interval, OnGetDataListener listener);
    }
}
