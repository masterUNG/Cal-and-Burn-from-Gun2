package com.example.admin.calandburn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SearchNameFood extends AppCompatActivity {

    private FoodTABLE objFoodTABLE;
    private ListView foodListView;
    private String searchString;
    private EditText searchFoodEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_name_food);

        foodListView = (ListView) findViewById(R.id.listView6);

        connectDataBase();

        createListView();

        searchFoodEditText = (EditText) findViewById(R.id.editText11);
        searchFoodEditText.addTextChangedListener(new TextWatcher() {
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

    public void clickToReportFromSearchFood(View view) {
        Intent toReportFromSearchFood = new Intent(this, ReportActivity.class);
        startActivity(toReportFromSearchFood);
        finish();
    }

    public void backToMainMenu(View view) {
        Intent backToMainMenu = new Intent(this, MainActivity.class);
        startActivity(backToMainMenu);
        finish();
    }

    private String[] SelectAllData() {
        try {
            String[] strResult = null;
            searchString = searchFoodEditText.getText().toString().trim();
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                    MODE_PRIVATE, null);
            Log.d("search=", searchString);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM foodTABLE WHERE namefood LIKE " + "'" + searchString + "%' ORDER BY namefood", null);

            strResult = new String[cursor.getCount()];
            String[] strTitle = new String[cursor.getCount()];
            String[] strDetail = new String[cursor.getCount()];
            String[] strIcon = new String[cursor.getCount()];
            int[] intIcon = new int[cursor.getCount()];

            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                strResult[i] = cursor.getString(cursor.getColumnIndex(cursor.getColumnName(1)));
                Log.d("Str=", strResult[i]);
                strTitle[i] = cursor.getString(cursor.getColumnIndex("namefood"));
                strDetail[i] = cursor.getString(cursor.getColumnIndex("calfood"));
                strIcon[i] = cursor.getString(cursor.getColumnIndex("aboutfood"));
                cursor.moveToNext();
            }

            cursor.close();
            Log.d("search6=", searchString);

            for (int i = 0; i < strIcon.length; i++) {
                if (strIcon[i].equals("1")) {
                    intIcon[i] = R.drawable.food1;
                } else if (strIcon[i].equals("2")) {
                    intIcon[i] = R.drawable.cake;
                } else {
                    intIcon[i] = R.drawable.drink;
                }
            }

            MyAdapter objMyAdapter = new MyAdapter(SearchNameFood.this, strTitle, strDetail, intIcon);
            foodListView.setAdapter(objMyAdapter);

            return strResult;
        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(this, "ไม่พบข้อมูล", "ไม่มีข้อมูลนี้ในฐานข้อมูล");
        }

        return new String[0];
    }

    private void createListView() {

        final String[] strTitle = objFoodTABLE.readAllDataFood(1);
        final String[] strDetail = objFoodTABLE.readAllDataFood(2);
        String[] strIcon = objFoodTABLE.readAllDataFood(3);
        int[] intIcon = new int[strIcon.length];
        for (int i = 0; i < strIcon.length; i++) {

            intIcon[i] = R.drawable.food;

        }   //for

      /*  MyAdapter objMyAdapter = new MyAdapter(SearchNameFood.this, strTitle, strDetail, intIcon);
        foodListView.setAdapter(objMyAdapter);*/

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                myAlertDialog(strTitle[i], strDetail[i]);

            }   // event
        });
    }

    private void myAlertDialog(final String strFood, final String strFactor) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);

        DateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();
        final String strDate = myDateFormat.format(currentDate);  // strDate

        final EditText objEditText = new EditText(SearchNameFood.this);
        objEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        objBuilder.setIcon(R.drawable.record);
        objBuilder.setTitle("วันที่ " + strDate);
        objBuilder.setMessage(strFood + "\n\n" + " 1 หน่วย" + " = " + strFactor +" calorie" +"\n"
                + "ใส่จำนวนบริโภค :");

        objBuilder.setView(objEditText);

        objBuilder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {

                    String strAmount = objEditText.getText().toString().trim();
                    int intAmount= Integer.parseInt(strAmount);
                    if (intAmount <=0) {
                        Toast.makeText(SearchNameFood.this, "กรุณาระบุจำนวนที่มากกว่า 0", Toast.LENGTH_SHORT).show();
                    } else {
                        confirmAlertDialog(strDate, strFood, strFactor, strAmount);
                        dialogInterface.dismiss();
                    }

                } catch (Exception e) {
                    Toast.makeText(SearchNameFood.this, "กรุณาระบุจำนวน", Toast.LENGTH_SHORT).show();
                }

            }   // event
        });
        objBuilder.show();
    }

    private void confirmAlertDialog(final String strDate, final String strFood, String strFactor, final String strAmount) {

        double douFactor = Double.parseDouble(strFactor);
        double douAmount = Double.parseDouble(strAmount);
        double douAnswer = douFactor * douAmount;
        final String strAnswer = Double.toString(douAnswer);

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);
        objBuilder.setTitle(strFood);
        objBuilder.setMessage(strFactor + " x " + strAmount + " = " + strAnswer + " calorie");
        objBuilder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                updateCalaryTABLE(strDate, strFood, strAmount, strAnswer);

                dialogInterface.dismiss();
            }
        });
        objBuilder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        objBuilder.show();

    }

    private void updateCalaryTABLE(String strDate, String strFood, String strAmount, String strAnswer) {

        MyManage myManage = new MyManage(this);
        myManage.addCalary(strDate, strFood, strAmount, strAnswer);
        Toast.makeText(SearchNameFood.this, "บันทึกรายการ " + strFood + " เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
    }

    private void connectDataBase() {
        objFoodTABLE = new FoodTABLE(this);
    }
} // Main Class
