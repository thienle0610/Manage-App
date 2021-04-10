package com.example.manageapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.manageapp.R;





public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendNote (View view){
        Intent intent = new Intent(this, NotesMainActivity.class);
        startActivity(intent);
    }

    public void sendTracker (View view){
        Intent intent = new Intent(this, ExpenseMain.class);
        startActivity(intent);
    }



}