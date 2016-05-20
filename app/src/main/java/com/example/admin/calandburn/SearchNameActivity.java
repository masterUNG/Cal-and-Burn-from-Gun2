package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

public class SearchNameActivity extends AppCompatActivity {

    private ActivityTABLE objActivityTABLE;
    private ListView actListView;
    private String searchString;
    private EditText searchActivityEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name_exercise);

        actListView = (ListView) findViewById(R.id.listView7);

        connectDataBase();

        createListView();

        searchActivityEditText = (EditText) findViewById(R.id.editText12);
        searchActivityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SelectAllData();
            }
        });

    } // Main Method

    public void backMainMenuFromSearchNameActivity (View view) {
        Intent backMainMenuFromSearchNameActivity = new Intent(this, MainActivity.class);
        startActivity(backMainMenuFromSearchNameActivity);
        finish();
    }

    public void clickToReportFromSearchNameActivity(View view) {
        Intent toReportIntent = new Intent(this, ReportActivity.class);
        startActivity(toReportIntent);
        finish();
    }

    private String[] SelectAllData() {
        try {
            String[] strResult = null;
            searchString = searchActivityEditText.getText().toString().trim();
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                    MODE_PRIVATE, null);
            Log.d("search=", searchString);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM activityTABLE WHERE nameact LIKE " + "'" + searchString + "%' ORDER BY nameact", null);

            strResult = new String[cursor.getCount()];
            String[] strTitle = new String[cursor.getCount()];
            String[] strDetail = new String[cursor.getCount()];
            String[] strIcon = new String[cursor.getCount()];
            int[] intIcon = new int[cursor.getCount()];

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                strResult[i] = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
                Log.d("Str=", strResult[i]);
                strTitle[i] = cursor.getString(cursor.getColumnIndex("nameact"));
                strDetail[i] = cursor.getString(cursor.getColumnIndex("burnact"));
                strIcon[i] = cursor.getString(cursor.getColumnIndex("aboutact"));
                cursor.moveToNext();
            }

            cursor.close();
            Log.d("search6=", searchString);

            for (int i = 0; i < strIcon.length; i++) {
                if (strIcon[i].equals(" 1")) {
                    intIcon[i] = R.drawable.burn1;
                } else {
                    intIcon[i] = R.drawable.burn2;
                }
            }

            MyAdapter objMyAdapter = new MyAdapter(SearchNameActivity.this, strTitle, strDetail, intIcon);
            actListView.setAdapter(objMyAdapter);

            return strResult;
        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "ไม่พบข้อมูล", "ไม่มีข้อมูลนี้ในฐานข้อมูล");
        }

        return new String[0];
    }

    private void createListView() {
        final String[] strNameAct = objActivityTABLE.readAllDataActivity(1);
        final String[] strBurnAct = objActivityTABLE.readAllDataActivity(2);
        String[] strIcon = objActivityTABLE.readAllDataActivity(3);

        int[] intIcon = new int[strIcon.length];
        for (int i = 0; i < strIcon.length; i++) {
            if (strIcon[i].equals(" 1")) {
                intIcon[i] = R.drawable.burn1;
            } else {
                intIcon[i] = R.drawable.burn2;
            }   // if
        } //for

        /*MyActList objMyActList = new MyActList(SearchNameActivity.this, strNameAct,
                strBurnAct, intIcon);
        actListView.setAdapter(objMyActList);*/

        actListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                myAlertDialog(strNameAct[position], strBurnAct[position]);

            } // Event
        });

    }

    private void myAlertDialog(final String strAct, final String strFactor) {
        Intent intent = new Intent(this, UpdateBurnTABLE.class);
        intent.putExtra("Act", strAct);
        intent.putExtra("Factor", strFactor);
        startActivity(intent);
    }

    private void connectDataBase() {
        objActivityTABLE = new ActivityTABLE(this);
    }
} // Main Class
