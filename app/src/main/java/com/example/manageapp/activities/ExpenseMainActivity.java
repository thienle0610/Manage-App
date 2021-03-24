package com.example.manageapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.manageapp.R;

public class ExpenseMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_main);
    }

    public void sendTracker(View v){
        Intent in = new Intent(this, ExpenseMainActivity.class);
        startActivity(in);
    }

    public void displayTracker(View view) {
        Intent in = new Intent(this, ExpenseAndIncome.class);
        startActivity(in);
    }
}