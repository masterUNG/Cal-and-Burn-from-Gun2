package com.example.admin.calandburn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    } // Main Method

    public void backToMainMenuFromSearchActivity(View view) {
        Intent backToMainMenuFromSearchAct = new Intent(this, MainActivity.class);
        startActivity(backToMainMenuFromSearchAct);
        finish();
    }

    public void clickToSearchNameActivity(View view) {
        Intent toSearchNameActivity = new Intent(this, SearchNameActivity.class);
        startActivity(toSearchNameActivity);
        finish();
    }

    public void clickToSearchActivity(View view) {
        Intent toSearchActivityIntent = new Intent(this, BurnActivity.class);
        startActivity(toSearchActivityIntent);
        finish();
    }

    public void clickToSearchExercise(View view) {
        Intent toSearchExerciseIntent = new Intent(this, SearchExercise.class);
        startActivity(toSearchExerciseIntent);
        finish();
    }

} // Main Class
