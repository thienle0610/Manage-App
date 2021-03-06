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

public class ExpenseForm extends AppCompatActivity {

    private static final String TAG = "ExpenseForm";
    DataBaseHelper dataBaseHelper;

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    int day = 0;
    int month = 0;
    int year = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_form);
        ImageView imageBack2 = findViewById(R.id.imageBack2);
        imageBack2.setOnClickListener(v -> onBackPressed());

        Button btn_add;
        EditText et_name, et_cate, et_amount;
        TextView et_date;
        ListView lv_expenseList;


        mDisplayDate = findViewById(R.id.date);
        btn_add = findViewById(R.id.button_add);
        et_name = findViewById(R.id.et_name);
        et_cate = findViewById(R.id.et_cate);
        et_amount = findViewById(R.id.et_amount);
        et_date = findViewById(R.id.date);
        lv_expenseList = findViewById(R.id.list);

        dataBaseHelper = new DataBaseHelper(getApplicationContext());

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customerModel;

                try {
                    customerModel = new CustomerModel(-1, et_name.getText().toString(), et_cate.getText().toString(), (-Integer.parseInt(et_amount.getText().toString())), day, month, year);

                    Toast.makeText(ExpenseForm.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch(Exception e){
                    Toast.makeText(ExpenseForm.this, "Error", Toast.LENGTH_SHORT).show();
                    customerModel = new CustomerModel(-1, "error", "error", 0, 0,0,0);
                }

                boolean success = dataBaseHelper.addOne(customerModel);
                int numBl = dataBaseHelper.getBalance();

                int newnumBL =numBl - Integer.parseInt(et_amount.getText().toString());
                dataBaseHelper.addTwo(newnumBL);

                Toast.makeText(ExpenseForm.this, "Success", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ExpenseForm.this, ExpenseMain.class);
                startActivity(intent);

                finish();
            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(ExpenseForm.this,R.style.CalendarBarTheme, onDateSetListener, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fefcff")));
                dialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int myear, int mmonth, int mday) {
                Log.d(TAG, "onDateSet: date: " + year + "/" + month + "/" + day);
                day = mday;
                month = mmonth;
                year = myear;
                et_date.setText("" + day + "/" + month + "/" + year);
            }
        };



    }


}