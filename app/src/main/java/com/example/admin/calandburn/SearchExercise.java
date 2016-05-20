package com.example.admin.calandburn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SearchExercise extends AppCompatActivity {


    private ActivityTABLE objActivityTABLE;
    private ListView actListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_exercise);

        actListView = (ListView) findViewById(R.id.listView8);

        connectDataBase();

        createListView();
    }

    private void createListView() {
        final String[] strNameAct = objActivityTABLE.readAllDataExercise(1);
        final String[] strBurnAct = objActivityTABLE.readAllDataExercise(2);
        String[] strAboutAct = objActivityTABLE.readAllDataExercise(3);

        int[] intIcon = new int[strAboutAct.length];

        for (int i = 0; i < strAboutAct.length; i++) {
            intIcon[i] = R.drawable.burn1;
        } //for

        MyActList objMyActList = new MyActList(SearchExercise.this, strNameAct,
                strBurnAct, intIcon);
        actListView.setAdapter(objMyActList);

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

    } // myAlertDialog

    private void connectDataBase() {
        objActivityTABLE = new ActivityTABLE(this);
    }

    public void clickToReportFromSearchExercise(View view) {
        Intent toReportIntent = new Intent(this, ReportActivity.class);
        startActivity(toReportIntent);
        finish();
    }

    public void backMainMenuSearchExercise(View view) {
        Intent toReportIntent = new Intent(this, MainActivity.class);
        startActivity(toReportIntent);
        finish();
    }

} // Main Class
