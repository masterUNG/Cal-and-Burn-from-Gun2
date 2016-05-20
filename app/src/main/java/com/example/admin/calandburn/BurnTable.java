package com.example.admin.calandburn;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Admin on 3/5/2559.
 */
public class BurnTable extends AppCompatActivity {

    private SQLiteDatabase readSqLiteDatabase;
    private MyOpenHelper objMyOpenHelper;

    private static final String Table_Burn = "calary_table";
    private static final String Column_Id = "_id";
    private static final String Column_Date = "Date";
    private static final String Column_Exercise = "Exercise";
    private static final String Column_Hour = "Hour";
    private static final String Column_CalBurn = "CalBurn";

    public BurnTable(Context context) {
        objMyOpenHelper = new MyOpenHelper(context);
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();
    } // Constuctor

    public String[] ReadAllCalaryTable(int intColumn) {

        String[] strResult = null;
        Cursor BurnTableCursor = readSqLiteDatabase.query(Table_Burn, new String[]{Column_Id, Column_Date,
                Column_Exercise, Column_Hour, Column_CalBurn}, null, null, null, null, null);

        if (BurnTableCursor != null) {

            strResult = new String[BurnTableCursor.getCount()];
            BurnTableCursor.moveToFirst();

            for (int i = 0; i < BurnTableCursor.getCount(); i++) {

                switch (intColumn) {
                    case 1:
                        strResult[i] = BurnTableCursor.getString(BurnTableCursor.getColumnIndex(Column_Date));
                        break;
                    case 2:
                        strResult[i] = BurnTableCursor.getString(BurnTableCursor.getColumnIndex(Column_Exercise));
                        break;
                    case 3:
                        strResult[i] = BurnTableCursor.getString(BurnTableCursor.getColumnIndex(Column_Hour));
                        break;
                    case 4:
                        strResult[i] = BurnTableCursor.getString(BurnTableCursor.getColumnIndex(Column_CalBurn));
                        break;
                }   // switch

                BurnTableCursor.moveToNext();

            }

        }
        BurnTableCursor.close();

        return strResult;
    }


} // Main Class
