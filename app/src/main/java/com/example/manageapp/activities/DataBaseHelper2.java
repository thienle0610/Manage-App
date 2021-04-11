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

public class DataBaseHelper2 extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE2 = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_NAME2 = "CUSTOMER_NAME";
    public static final String COLUMN_CUSTOMER_CATEGORY2 = "CUSTOMER_CATEGORY";
    public static final String COLUMN_CUSTOMER_AMOUNT2 = "CUSTOMER_AMOUNT";
    public static final String COLUMN_CUSTOMER_DAY2 = "CUSTOMER_DAY";
    public static final String COLUMN_CUSTOMER_MONTH2 = "CUSTOMER_MONTH";
    public static final String COLUMN_CUSTOMER_YEAR2 = "CUSTOMER_YEAR";

    public DataBaseHelper2(@Nullable Context context) {
        super(context, "customer.db", null, 1);
        Log.d("test", "DataBaseHelper: it runs");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_CUSTOMER_NAME2 + " TEXT, " + COLUMN_CUSTOMER_CATEGORY2 + " TEXT, " + COLUMN_CUSTOMER_AMOUNT2 + " INT, " + COLUMN_CUSTOMER_DAY2 + " INT, " + COLUMN_CUSTOMER_MONTH2 + " INT, " + COLUMN_CUSTOMER_YEAR2 + " INT)";

    db.execSQL(createTableStatement);
        Log.d("test", "DataBaseHelper: create table");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_NAME2, customerModel.getName());
        cv.put(COLUMN_CUSTOMER_CATEGORY2, customerModel.getCategory());
        cv.put(COLUMN_CUSTOMER_AMOUNT2, customerModel.getAmount());
        cv.put(COLUMN_CUSTOMER_DAY2, customerModel.getDay());
        cv.put(COLUMN_CUSTOMER_MONTH2, customerModel.getMonth());
        cv.put(COLUMN_CUSTOMER_YEAR2, customerModel.getYear());

        long insert = db.insert(CUSTOMER_TABLE2, null, cv);
        if (insert == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public List<CustomerModel> getEveryone()
    {
        List<CustomerModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE2;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst())
        {
            do{
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerCategory = cursor.getString(2);
                int customerAmount = cursor.getInt(3);
                int customerDay = cursor.getInt(4);
                int customerMonth = cursor.getInt(5);
                int customerYear = cursor.getInt(6);

                Log.d("Databashelper", " "+ customerID + " " + customerName + " " +customerCategory + " " + customerAmount + " "+ customerDay + " " + customerMonth + " " + customerYear);

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerCategory, customerAmount, customerDay, customerMonth, customerYear);
                returnList.add(newCustomer);

            } while (cursor.moveToNext());
        }
        else
        {

        }
        cursor.close();
        db.close();
        return returnList;
    }
}
