package com.example.admin.calandburn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalActivity extends AppCompatActivity {

    // Explicit
    private FoodTABLE objFoodTABLE;
    private ListView foodListView;
    private String searchString;
    private EditText edit_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal);

        foodListView = (ListView) findViewById(R.id.listView);

        connectDataBase();

        //Create ListView
        createListView();


    }   // Main method

    public void clickToReportFromCalFood(View view) {
        Intent toReportFromCalFood = new Intent(this, ReportActivity.class);
        startActivity(toReportFromCalFood);
        finish();
    }

    private void createListView() {

        final String[] strTitle = objFoodTABLE.readAllDataFood(1);
        final String[] strDetail = objFoodTABLE.readAllDataFood(2);
        String[] strIcon = objFoodTABLE.readAllDataFood(3);
        int[] intIcon = new int[strIcon.length];
        for (int i = 0; i < strIcon.length; i++) {

            intIcon[i] = R.drawable.food1;

        }   //for

        MyAdapter objMyAdapter = new MyAdapter(CalActivity.this, strTitle, strDetail, intIcon);
        foodListView.setAdapter(objMyAdapter);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                myAlertDialog(strTitle[i], strDetail[i]);

            }   // event
        });

    }   // createListView

    private void myAlertDialog(final String strFood, final String strFactor) {

        AlertDialog.Builder objBuilder = new AlertDialog.Builder(this);

        DateFormat myDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();
        final String strDate = myDateFormat.format(currentDate);  // strDate

        final EditText objEditText = new EditText(CalActivity.this);
        objEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
        objBuilder.setIcon(R.drawable.food);
        objBuilder.setTitle("วันที่ " + strDate);
        objBuilder.setMessage(strFood + "\n\n" + " 1 หน่วย" + " = " + strFactor + " calorie" + "\n"
                + "ใส่จำนวนบริโภค :");

        objBuilder.setView(objEditText);

        objBuilder.setPositiveButton("เพิ่ม", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                try {

                    String strAmount = objEditText.getText().toString().trim();
                    int intAmount = Integer.parseInt(strAmount);
                    if (intAmount <= 0) {
                        Toast.makeText(CalActivity.this, "กรุณาระบุจำนวนที่มากกว่า 0", Toast.LENGTH_SHORT).show();
                    } else {
                        confirmAlertDialog(strDate, strFood, strFactor, strAmount);
                        dialogInterface.dismiss();
                    }

                } catch (Exception e) {
                    Toast.makeText(CalActivity.this, "กรุณาระบุจำนวน", Toast.LENGTH_SHORT).show();
                }

            }   // event
        });
        objBuilder.show();

    }   // myAlertDialog

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

    }   // confirmAlertDialog

    private void updateCalaryTABLE(String strDate,
                                   String strFood,
                                   String strAmount,
                                   String strAnswer) {

        MyManage myManage = new MyManage(this);
        myManage.addCalary(strDate, strFood, strAmount, strAnswer);
        Toast.makeText(CalActivity.this, "บันทึกรายการ " + strFood + " เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();

    }


    private void connectDataBase() {
        objFoodTABLE = new FoodTABLE(this);
    }

    public void onCalClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }

}   // Main Class
