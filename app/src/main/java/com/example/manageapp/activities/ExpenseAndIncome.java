package com.example.manageapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.manageapp.R;

public class ExpenseAndIncome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_and_income);

        ImageView imageBack = findViewById(R.id.imageView5);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    public void displayExpense(View view) {
        Intent intent = new Intent(this, ExpenseForm.class);
        startActivity(intent);
    }

    public void displayincome(View view) {
        Intent intent2 = new Intent(this, income.class);
        startActivity(intent2);
    }
}