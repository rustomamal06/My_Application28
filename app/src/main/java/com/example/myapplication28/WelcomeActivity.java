package com.example.myapplication28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {
    private TextView TextViewWelcome;
    private Button ButtonBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextViewWelcome=findViewById(R.id.TextViewWelcome);
        ButtonBack=findViewById(R.id.ButtonBack);
        String name =getIntent().getStringExtra("name");
        TextViewWelcome.setText("Welcome"+name);
    }
    public void back(View view){
        Intent intent= new Intent(this,MainActivity.class) ;

        startActivity(intent);
    }

}