package com.example.manageapp.activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Balance extends SQLiteOpenHelper {
    public static final String BALANCE_TABLE = "BALANCE_TABLE";
    public static final String COLUMN_CUSTOMER_AMOUNT2 = "CUSTOMER_AMOUNT2";


    public Balance(@Nullable Context context) {
        super(context, "customer.db", null, 1);
        Log.d("test", "DataBaseHelper: it runs");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + BALANCE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
               COLUMN_CUSTOMER_AMOUNT2 + " INT)";

        db.execSQL(createTableStatement);
        Log.d("test", "DataBaseHelper: create table");
        SQLiteDatabase bl = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_CUSTOMER_AMOUNT2, 0);


        long insert = bl.insert(BALANCE_TABLE, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addTwo(int amount){
        SQLiteDatabase bl = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        cv.put(COLUMN_CUSTOMER_AMOUNT2, amount);


        long insert = bl.insert(BALANCE_TABLE, null, cv);

        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public int getBalance()
    {

        String queryString = "SELECT * FROM " + BALANCE_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);
        int customerAmount = 0;
        if (cursor.moveToFirst())
        {

                int customerID = cursor.getInt(0);

                customerAmount = cursor.getInt(1);

        }
        else
        {

        }
        cursor.close();
        db.close();
        return customerAmount;
    }
}