package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDateHisFood extends AppCompatActivity {

    private TextView dateTextView, totalkcalTextView;
    private ListView dateListView;
    private CalaryTable objCalaryTable;

    private String strDate1;
    private String[] strDate = null;
    private String[] nameStrings, amountStrings, kcalStrings;
    private double totalkcalADouble = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_date_his_food);

        bindWidter();

        showDate();

        createListDailyPlan();
    } // Main Method

    public void ClickBackShowDateHisFood(View view) {
        Intent backIntent = new Intent(this, HisFoodData.class);
        startActivity(backIntent);
        finish();
    }

    public void backMainMenu(View view) {
        Intent backMainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(backMainMenuIntent);
        finish();
    }

    private void createListDailyPlan() {
        objCalaryTable=new CalaryTable(this);
        try {

            SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
            //สร้าง Cursor ที่ Column date = วันที่ ที่ถูก Intent มา
            Cursor objcursor = objSqLiteDatabase.rawQuery("SELECT * FROM calary_table WHERE Date = " + "'" + strDate1 + "'", null);
            objcursor.moveToFirst();

            nameStrings = new String[objcursor.getCount()];
            strDate = new String[objcursor.getCount()];
            amountStrings = new String[objcursor.getCount()];
            kcalStrings = new String[objcursor.getCount()];

            for (int i = 0; i < objcursor.getCount(); i++) {

                nameStrings[i] = objcursor.getString(objcursor.getColumnIndex("Food"));
                strDate[i] = objcursor.getString(objcursor.getColumnIndex("Date"));
                amountStrings[i] = objcursor.getString(objcursor.getColumnIndex("Amount"));
                kcalStrings[i] = objcursor.getString(objcursor.getColumnIndex("CalFood"));

                totalkcalADouble = totalkcalADouble + Double.parseDouble(kcalStrings[i]);

                objcursor.moveToNext();
            }//for

            objcursor.close();

            totalkcalTextView.setText("รับพลังงานไป  " + Double.toString(totalkcalADouble) + "  kcal");

            HistoryAdapter historyAdapter = new HistoryAdapter(this, nameStrings, amountStrings, kcalStrings);
            dateListView.setAdapter(historyAdapter);


        }catch (Exception e) {
            Log.d("3May", "MyError >>>" + e.toString());
            Toast.makeText(ShowDateHisFood.this, "วันนี้ไม่มีกิจกรรมใดๆ", Toast.LENGTH_LONG).show();
        }
    }



    private void showDate() {
        strDate1 = getIntent().getStringExtra("Date");
        dateTextView.setText(strDate1);
    }

    private void bindWidter() {
        dateTextView = (TextView) findViewById(R.id.textView70);
        dateListView = (ListView) findViewById(R.id.listView5);
        totalkcalTextView = (TextView) findViewById(R.id.textView77);
    }
} // Main Class
