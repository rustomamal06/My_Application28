package com.example.myapplication28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
   private Button buttonLogin, buttonSignUp, buttonSetTime, buttonProfile, buttonassignment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        buttonLogin=findViewById(R.id.buttonLogin);
        buttonSignUp=findViewById(R.id.buttonSignUp);
        buttonSetTime=findViewById(R.id.buttonSetTime);
        buttonProfile=findViewById(R.id.buttonProfile);
        buttonassignment=findViewById(R.id.buttonassignment);
    }

    public void Submit1(View view) {
        Intent intent= new Intent(this,MainActivity.class) ;
        startActivity(intent);
    }

    public void Submit2(View view) {
        Intent intent= new Intent(this,SignUPActivity.class) ;
        startActivity(intent);
    }

    public void Submit3(View view) {
        Intent intent= new Intent(this,SetTimeActivity.class) ;
        startActivity(intent);
    }

    public void Submit4(View view) {
        Intent intent= new Intent(this,ProfileActivity.class) ;
        startActivity(intent);
    }

    public void Submit5(View view) {
        Intent intent= new Intent(this,ArrayListActivity.class) ;
        startActivity(intent);
    }
}