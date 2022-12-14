package com.example.multifunctionalfitnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.multifunctionalfitnessapp.DailySchedule;
import com.example.multifunctionalfitnessapp.NormalUser;
import com.example.multifunctionalfitnessapp.PersonTimeInterval;
import com.example.multifunctionalfitnessapp.R;

public class Daily_Schedule_Activity extends AppCompatActivity {


    TableLayout layout = (TableLayout) findViewById(R.id.layout0);
    int rowNumber = layout.getChildCount();

    DailySchedule dailySchedule = new DailySchedule();
    PersonTimeInterval interval = (PersonTimeInterval) dailySchedule.getInterval(0);
    String facilityName = interval.getAppointedFacility().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daily_schedule);
        specifyUnavailablePeriods();
        appointmentEdit();



    }

    public void specifyUnavailablePeriods(){
        for(int n = 1; n< rowNumber; n++) {
            TableRow row = (TableRow)layout.getChildAt(n);
            TextView name = (TextView) row.getChildAt(2);

            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!name.getBackground().equals(Color.RED))
                    name.setBackgroundColor(Color.RED);
                    else
                        name.setBackgroundColor(Color.WHITE);
                }
            });



        }
    }

    public void appointmentEdit() {
        for (int n = 1; n < rowNumber; n++) {
            TableRow row = (TableRow) layout.getChildAt(n);
            TextView name = (TextView) row.getChildAt(2);
            PersonTimeInterval interval = (PersonTimeInterval) dailySchedule.getInterval(n - 1);
            if (interval.isAppointed) {
                name.setText("Appointment at:" + facilityName);
                name.setBackgroundColor(Color.BLUE);

            }

            /*public void updateHours () {
                // we are working for normal user right now
                // we should look for every time interval 1 by 1
                // it goes from 8-9 to 18-19.
                // we should have a for loop controlling each and making the necassary udpates
                DailySchedule dailySchedule = new DailySchedule();
                // i will be the starting hour

                for (int i = 0; i <= 23; i++) {
                    // getting the PersonTimeInterval object for the specific hour
                    PersonTimeInterval interval = (PersonTimeInterval) dailySchedule.getInterval(i);
                    if (interval.isAppointed) {
                        // in this case the interval is appointed,
                        // we should display the appointed facility
                        String facilityName = interval.getAppointedFacility().getName();
                        if (i == 0) {
                            ((TextView) findViewById(R.id.Hour1)).setText("appointment at " + facilityName);
                            ((TextView) findViewById(R.id.Hour1)).setBackgroundColor(Color.BLUE);
                        } else if (i == 1) {
                            ((TextView) findViewById(R.id.Hour2)).setText("appointment at " + facilityName);
                        } else if (i == 2) {
                            ((TextView) findViewById(R.id.Hour3)).setText("appointment at " + facilityName);
                        } else if (i == 3) {
                            ((TextView) findViewById(R.id.Hour4)).setText("appointment at " + facilityName);
                        } else if (i == 4) {
                            ((TextView) findViewById(R.id.Hour5)).setText("appointment at " + facilityName);
                        } else if (i == 5) {
                            ((TextView) findViewById(R.id.Hour6)).setText("appointment at " + facilityName);
                        } else if (i == 6) {
                            ((TextView) findViewById(R.id.Hour7)).setText("appointment at " + facilityName);
                        } else if (i == 7) {
                            ((TextView) findViewById(R.id.Hour8)).setText("appointment at " + facilityName);
                        } else if (i == 8) {
                            ((TextView) findViewById(R.id.Hour9)).setText("appointment at " + facilityName);
                        } else if (i == 9) {
                            ((TextView) findViewById(R.id.Hour10)).setText("appointment at " + facilityName);
                        } else if (i == 10) {
                            ((TextView) findViewById(R.id.Hour11)).setText("appointment at " + facilityName);
                        } else if (i == 11) {
                            ((TextView) findViewById(R.id.Hour12)).setText("appointment at " + facilityName);
                        } else if (i == 12) {
                            ((TextView) findViewById(R.id.Hour13)).setText("appointment at " + facilityName);
                        } else if (i == 13) {
                            ((TextView) findViewById(R.id.Hour14)).setText("appointment at " + facilityName);
                        } else if (i == 14) {
                            ((TextView) findViewById(R.id.Hour15)).setText("appointment at " + facilityName);
                        } else if (i == 15) {
                            ((TextView) findViewById(R.id.Hour16)).setText("appointment at " + facilityName);
                        } else if (i == 16) {
                            ((TextView) findViewById(R.id.Hour17)).setText("appointment at " + facilityName);
                        } else if (i == 17) {
                            ((TextView) findViewById(R.id.Hour18)).setText("appointment at " + facilityName);
                        } else if (i == 18) {
                            ((TextView) findViewById(R.id.Hour19)).setText("appointment at " + facilityName);
                        } else if (i == 19) {
                            ((TextView) findViewById(R.id.Hour20)).setText("appointment at " + facilityName);
                        } else if (i == 20) {
                            ((TextView) findViewById(R.id.Hour21)).setText("appointment at " + facilityName);
                        } else if (i == 21) {
                            ((TextView) findViewById(R.id.Hour22)).setText("appointment at " + facilityName);
                        } else if (i == 22) {
                            ((TextView) findViewById(R.id.Hour23)).setText("appointment at " + facilityName);
                        } else if (i == 23) {
                            ((TextView) findViewById(R.id.Hour24)).setText("appointment at " + facilityName);
                        }

                    }
                }
            }
            /*
            else {
                // in this case the interval is not appointed,
                // therefore free
                if (i == 0) {
                    ((Button) findViewById(R.id.Hour1)).setText("No appointment");
                }
                else if (i == 1) {
                    ((Button) findViewById(R.id.Hour2)).setText(occupancyRate);
                }
                else if (i == 2) {
                    ((Button) findViewById(R.id.Hour3)).setText(occupancyRate);
                }
                else if (i == 3) {
                    ((Button) findViewById(R.id.Hour4)).setText(occupancyRate);
                }
                else if (i == 4) {
                    ((Button) findViewById(R.id.Hour5)).setText(occupancyRate);
                }
                else if (i == 5) {
                    ((Button) findViewById(R.id.Hour6)).setText(occupancyRate);
                }
                else if (i == 6) {
                    ((Button) findViewById(R.id.Hour7)).setText(occupancyRate);
                }
                else if (i == 7) {
                    ((Button) findViewById(R.id.Hour8)).setText(occupancyRate);
                }
                else if (i == 8) {
                    ((Button) findViewById(R.id.Hour9)).setText(occupancyRate);
                }
                else if (i == 9) {
                    ((Button) findViewById(R.id.Hour10)).setText(occupancyRate);
                }
                else if (i == 10) {
                    ((Button) findViewById(R.id.Hour11)).setText(occupancyRate);
                }
                else if (i == 11) {
                    ((Button) findViewById(R.id.Hour12)).setText(occupancyRate);
                }
                else if (i == 12) {
                    ((Button) findViewById(R.id.Hour13)).setText(occupancyRate);
                }
                else if (i == 13) {
                    ((Button) findViewById(R.id.Hour14)).setText(occupancyRate);
                }
                else if (i == 14) {
                    ((Button) findViewById(R.id.Hour15)).setText(occupancyRate);
                }
                else if (i == 15) {
                    ((Button) findViewById(R.id.Hour16)).setText(occupancyRate);
                }
                else if (i == 16) {
                    ((Button) findViewById(R.id.Hour17)).setText(occupancyRate);
                }
                else if (i == 17) {
                    ((Button) findViewById(R.id.Hour18)).setText(occupancyRate);
                }
                else if (i == 18) {
                    ((Button) findViewById(R.id.Hour19)).setText(occupancyRate);
                }
                else if (i == 19) {
                    ((Button) findViewById(R.id.Hour20)).setText(occupancyRate);
                }
                else if (i == 20) {
                    ((Button) findViewById(R.id.Hour21)).setText(occupancyRate);
                }
                else if (i == 21) {
                    ((Button) findViewById(R.id.Hour22)).setText(occupancyRate);
                }
                else if (i == 22) {
                    ((Button) findViewById(R.id.Hour23)).setText(occupancyRate);
                }
                else if (i == 23) {
                    ((Button) findViewById(R.id.Hour24)).setText(occupancyRate);
                }
            }
            */
                }


        }
    }