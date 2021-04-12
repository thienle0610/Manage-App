package com.example.manageapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manageapp.R;

import java.util.Calendar;

public class Income extends AppCompatActivity {

    Button btn_add2;
    EditText et_name2, et_cate2, et_amount2;
    TextView et_date2;
    ListView lv_incomeList;



    private static final String TAG = "Income";
    DataBaseHelper dataBaseHelper;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    int day = 0;
    int month = 0;
    int year = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        ImageView imageBack3 = findViewById(R.id.imageBack3);
        imageBack3.setOnClickListener(v -> onBackPressed());


        mDisplayDate = findViewById(R.id.date2);
        btn_add2 = findViewById(R.id.button_add2);
        et_name2 = findViewById(R.id.et_name2);
        et_cate2 = findViewById(R.id.et_cate2);
        et_amount2 = findViewById(R.id.et_amount2);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());


        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myear, int mmonth, int mday) {
                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + day);
                day = mday;
                month = mmonth;
                year = myear;
               mDisplayDate.setText("" + day + "/" + month + "/" + year);
            }
        };


    }


    public void submit2(View view) {
        CustomerModel customerModel;

        try {
            customerModel = new CustomerModel(-1, et_name2.getText().toString(), et_cate2.getText().toString(), Integer.parseInt(et_amount2.getText().toString()), day, month, year);

            Toast.makeText(Income.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(Income.this, "Error", Toast.LENGTH_SHORT).show();
            customerModel = new CustomerModel(-1, "error", "error", 0, 0, 0, 0);
        }

        boolean success = dataBaseHelper.addOne(customerModel);
        DataBaseHelper dataBaseHelper2 = new DataBaseHelper(this);
        int numBl = dataBaseHelper2.getBalance();

        int newnumBL =numBl + Integer.parseInt(et_amount2.getText().toString());
        dataBaseHelper.addTwo(newnumBL);
        Toast.makeText(Income.this, "Success", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Income.this, ExpenseMain.class);
        startActivity(intent);

        finish();

    }

    public void date1(View view) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(Income.this,R.style.CalendarBarTheme, onDateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fefcff")));
        dialog.show();
    }
}