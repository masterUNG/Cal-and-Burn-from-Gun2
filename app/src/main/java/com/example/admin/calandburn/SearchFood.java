package com.example.admin.calandburn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

public class SearchFood extends AppCompatActivity {

    private EditText searchEditText, editText71;
    public String searchString;
    private FoodTABLE objFoodTABLE;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_food);

        //Bird Widget
       // searchEditText = (EditText) findViewById(R.id.editText11);

    } //Main Method

    public void backToMainMenuFromSearchFood(View view) {
        Intent toMainMenuIntent = new Intent((this), MainActivity.class);
        startActivity(toMainMenuIntent);
        finish();
    }

    public void clickToSearchNameFood(View view) {

        Intent SearchNameFoodIntent = new Intent(this, SearchNameFood.class);
        startActivity(SearchNameFoodIntent);
        finish();

    }

    public void clickToFood(View view) {

        Intent foodIntent = new Intent(this, CalActivity.class);
        startActivity(foodIntent);
        finish();

    } // Click To Food

    public void clickToCake(View view) {
        Intent dessertIntent = new Intent(this, SelectCakeFood.class);
        startActivity(dessertIntent);
        finish();

    }

    public void clickToDrink(View view) {
        Intent drinkIntent = new Intent(this, SelectDrinkFood.class);
        startActivity(drinkIntent);
        finish();
    }

  /*  public void clickSearch(View view) {
        SelectAllData(1);
        searchString = searchEditText.getText().toString().trim();
        String test=searchString;
        Intent intent=new Intent(this, SearchShowFood.class);
        intent.putExtra("test",searchEditText.getText().toString());
        startActivity(intent);
        //Log.d("kcal=", kcal);

    } // Click Search\
    public String[][] SelectAllData1() {
        try {
            String[][] arrData = null;
            searchString = searchEditText.getText().toString().trim();
            SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.DATABASE_NAME,
                    MODE_PRIVATE, null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM foodTABLE WHERE namefood LIKE " + "'" + searchString + "%'", null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    arrData = new String[cursor.getCount()][cursor.getColumnCount()];
                    int i= 0;
                    do {
                        arrData[i][1] = (cursor.getString(1));
                        //arrData[i][5] = (cursor.getDouble(5));
                        Log.d("Str=", arrData[i][1]);
                        Log.d("Search=", searchString);
                        i++;
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            return arrData;

        } catch (Exception e) {
            return null;
        }
    } */


} // Main Class
