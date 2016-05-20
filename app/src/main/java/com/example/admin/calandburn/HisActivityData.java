package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HisActivityData extends AppCompatActivity {

    private String[] dateAllStrings, dateStrings; // DateALL เวลาที่ซ้ำได้
    private ListView listView;
    private ArrayList<String> stringArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_his_activity_data);

        listView = (ListView) findViewById(R.id.activityListview);

        readAllSQLiteActivity();
    }

    private void readAllSQLiteActivity() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM burn_table ORDER BY Date DESC", null);
        cursor.moveToFirst();
        int intCount = cursor.getCount();

        dateStrings = new String[intCount];
        String[] activityStrings = new String[intCount];
        String[] amountStrings = new String[intCount];
        String[] calActivityStrings = new String[intCount];

        for (int i = 0; i < intCount; i++) {
            dateStrings[i] = cursor.getString(1);
            cursor.moveToNext();
        }   // For
        cursor.close();

        findDate(dateStrings);

        // Create ListView
        CreatListView();

    }

    private void CreatListView() {
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,stringArrayList);
        listView.setAdapter(stringArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String strDateClick = stringArrayList.get(i);
                Log.d("3May", "Date >>> " + strDateClick);

                Intent objIntent = new Intent(HisActivityData.this, ShowDateHisActivity.class);
                objIntent.putExtra("Date", strDateClick);
                startActivity(objIntent);
                finish();

            }   // event
        });
    }

    private String[] findDate(String[] dateStrings) {
        stringArrayList = new ArrayList<String>();
        for (int i = 0; i < dateStrings.length; i++) {
            stringArrayList.add(dateStrings[i]);

        }   // For
        Object[] objects = stringArrayList.toArray();
        for (Object myObj : objects) {
            if (stringArrayList.indexOf(myObj) != stringArrayList.lastIndexOf(myObj)) {
                stringArrayList.remove(stringArrayList.lastIndexOf(myObj));
            } // if

        }   // For myObj



        return new String[0];
    }

    public void backMainMenuFromHisActivity(View view) {
        Intent backMainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(backMainMenuIntent);
        finish();
    }
}
