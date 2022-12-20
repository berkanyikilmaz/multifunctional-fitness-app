package com.example.multifunctionalfitnessapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RemoveDialog extends AppCompatDialogFragment {

    private RemoveDialogListener listener;
    private TextView name;
    private PersonTimeInterval interval;

    private TextView timePeriodText;
    private TextView facilityText;

    public RemoveDialog(TextView name, PersonTimeInterval interval){
        this.name = name;
        this.interval = interval;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.remove_appointenment_pop_up, null);

        timePeriodText = view.findViewById(R.id.timePeriod);
        facilityText = view.findViewById(R.id.appointmentMessage);

        timePeriodText.setText(Constants.DAYS[interval.dailySchedule.day] + " " + interval.startingHour + " - " + (interval.startingHour + 1) );
        facilityText.setText("Appointment in " + interval.appointedFacility.getName());

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
    }
}
