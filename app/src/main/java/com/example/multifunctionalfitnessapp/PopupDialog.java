package com.example.multifunctionalfitnessapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class PopupDialog extends Dialog {

    private Context context;
    Button button = findViewById(R.id.removeButton);
    

    public PopupDialog(@NonNull Context context) {
        super(context, R.style.NoActionBarDialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remove_appointenment_pop_up);



        //find your views like this and use them as you want

    }
     public void buttonClicked(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    public void onBackPressed() {
        //do nothing <----- only this if you want to forbid the user to exit the dialog with the back button, else don't override this method
    }
}
