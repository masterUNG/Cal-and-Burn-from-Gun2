package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ShowDateHisActivity extends AppCompatActivity {

    private TextView dateTextView, totalkcalTextView;
    private ListView dateListView;
    private BurnTable objBurnTable;
    private String strDate1;
    private String[] strDate = null;
    private String[] nameStrings, hourStrings, kcalStrings;
    private double totalkcalADouble = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_date_his);

        bindWidter();

        showDate();

        createListDailyPlan();

    }

    public void ClickBackShowDateHisActivity(View view) {
        Intent backIntent = new Intent(this, HisActivityData.class);
        startActivity(backIntent);
        finish();
    }

    public void backMainMenu(View view) {
        Intent backMainMenuIntent = new Intent(this, MainActivity.class);
        startActivity(backMainMenuIntent);
        finish();
    }

    private void createListDailyPlan() {
        objBurnTable = new BurnTable(this);
        try {

            SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);
            //สร้าง Cursor ที่ Column date = วันที่ ที่ถูก Intent มา
            Cursor objcursor = objSqLiteDatabase.rawQuery("SELECT * FROM burn_table WHERE Date = " + "'" + strDate1 + "'", null);
            objcursor.moveToFirst();

            nameStrings = new String[objcursor.getCount()];
            strDate = new String[objcursor.getCount()];
            hourStrings = new String[objcursor.getCount()];
            kcalStrings = new String[objcursor.getCount()];

            for (int i = 0; i < objcursor.getCount(); i++) {

                nameStrings[i] = objcursor.getString(objcursor.getColumnIndex("Exercise"));
                strDate[i] = objcursor.getString(objcursor.getColumnIndex("Date"));
                hourStrings[i] = objcursor.getString(objcursor.getColumnIndex("Hour"));
                kcalStrings[i] = objcursor.getString(objcursor.getColumnIndex("CalBurn"));

                totalkcalADouble = totalkcalADouble + Double.parseDouble(kcalStrings[i]);

                objcursor.moveToNext();
            }//for

            objcursor.close();

            totalkcalTextView.setText("เผาผลาญไป  " + Double.toString(totalkcalADouble) + "  kcal");

            HistoryAdapter historyAdapter = new HistoryAdapter(this, nameStrings, hourStrings, kcalStrings);
            dateListView.setAdapter(historyAdapter);


        }catch (Exception e) {
            Log.d("3May", "MyError >>>" + e.toString());
            Toast.makeText(ShowDateHisActivity.this, "วันนี้ไม่มีกิจกรรมใดๆ", Toast.LENGTH_LONG).show();
        }
    }

    private void showDate() {
        strDate1 = getIntent().getStringExtra("Date");
        dateTextView.setText(strDate1);
    }

    private void bindWidter() {

        dateTextView = (TextView) findViewById(R.id.textView87);
        dateListView = (ListView) findViewById(R.id.listView9);
        totalkcalTextView = (TextView) findViewById(R.id.textView88);

    }
}
