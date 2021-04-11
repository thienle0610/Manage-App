package com.example.manageapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manageapp.R;

public class ExpenseAndIncome extends AppCompatActivity {
    TextView balance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_and_income);
        balance = findViewById(R.id.numAvailableTxt);
        ImageView imageBack = findViewById(R.id.imageView5);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        DataBaseHelper blance = new DataBaseHelper(this);
        int amountBl = blance.getBalance();
        balance.setText("$" + amountBl);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DataBaseHelper blance = new DataBaseHelper(this);
        int amountBl = blance.getBalance();
        balance.setText("$" + amountBl);
    }

    public void displayExpense(View view) {
        Intent intent = new Intent(this, ExpenseForm.class);
        startActivity(intent);
    }

    public void displayincome(View view) {
        Intent intent = new Intent(this, Income.class);
        startActivity(intent);
    }
}