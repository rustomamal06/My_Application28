package com.example.myapplication28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
    }
    public void onClick1(View view) {
        Intent intent= new Intent(this,ArrayListActivity.class) ;
        startActivity(intent);
    }

    public void onClick2(View view) {
        Intent intent= new Intent(this,ArrayListActivity.class) ;
        startActivity(intent);
    }

    public void onClick3(View view) {
        Intent intent= new Intent(this,ArrayListActivity.class) ;
        startActivity(intent);
    }
}