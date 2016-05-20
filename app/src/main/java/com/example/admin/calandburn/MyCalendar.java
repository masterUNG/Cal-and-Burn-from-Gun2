package com.example.admin.calandburn;

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


    }   // confirmDate

}   // Main Class
