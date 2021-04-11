package com.example.manageapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.manageapp.R;

public class ExpenseMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_main);

    }

    public void displayTracker (View view){
        Intent intent = new Intent(this, ExpenseAndIncome.class);
        startActivity(intent);
    }
    public void turnHome (View view){
        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);
    }

}