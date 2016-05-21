package com.example.admin.calandburn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

public class MyCalendar extends AppCompatActivity {

    //Explicit
    private int[] amountMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private int dayStartAnInt, monthStartAnInt, dayEndAnInt, monthEndAnInt, yearAnInt;
    private String dateStartString, dateEndString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_calendar);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {

                confirmDate(year, month, day);

            }   // on
        });


    }   // Main Method

    private void confirmDate(int year, int month, int day) {

        if ((day - 6) < 0) {

            dayStartAnInt = amountMonth[month - 1] - (6 - day);
            monthStartAnInt = month;

            dayEndAnInt = day;
            monthEndAnInt = month + 1;
            yearAnInt = year;


        } else {

            dayStartAnInt = day - 6;
            monthStartAnInt = month + 1;

            dayEndAnInt = day;
            monthEndAnInt = month + 1;
            yearAnInt = year;

        } // if

        dateStartString = Integer.toString(yearAnInt) + "/" +
                Integer.toString(monthStartAnInt) + "/" +
                Integer.toString(dayStartAnInt);

        dateEndString = Integer.toString(yearAnInt) + "/" +
                Integer.toString(monthEndAnInt) + "/" +
                Integer.toString(dayEndAnInt);


        Log.d("20MayV1", "dateStart ==> " + dateStartString);
        Log.d("20MayV1", "dateEnd ==> " + dateEndString);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.icon_myaccount);
        builder.setCancelable(false);
        builder.setTitle("Confirm Date");
        builder.setMessage("Start ==> " + dateStartString + "\n" +
                "End ==> " + dateEndString);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setupForIntent(dateStartString, dateEndString);
                dialogInterface.dismiss();
            }
        });
        builder.show();


    }   // confirmDate

    private void setupForIntent(String dateStartString, String dateEndString) {

        String[] dateResultStrings = new String[7];
        String[] timeStart = dateStartString.split("/");
        int intTimes = Integer.parseInt(timeStart[2]);

        for (int i=0;i<7;i++) {
            dateResultStrings[i] = timeStart[0] + "/" + "0" + timeStart[1] + "/" +
                    Integer.toString(intTimes);
            intTimes += 1;

            Log.d("20MayV1", "dateResultString " + i + " ==>  " + dateResultStrings[i]);

        }

        Intent intent = new Intent(MyCalendar.this, SevenDay.class);
        intent.putExtra("ResultDate", dateResultStrings);
        startActivity(intent);


    }   // setup

}   // Main Class
