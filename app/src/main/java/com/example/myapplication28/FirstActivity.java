package com.example.myapplication28;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
    private static final int NOTIFICATION_REMINDER_NIGHT = 1;
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
//this will start the service which in turn will the music
       // Intent musicIntent = new Intent(this, MusicService.class);
      //  startService(musicIntent);
        Intent notifyIntent = new Intent(this,NotificationReciever.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_REMINDER_NIGHT, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000 * 60 * 60 * 24, pendingIntent);
    }

    public void Submit1(View view) {
        Intent intent= new Intent(this,MainActivity.class) ;
        startActivity(intent);
    }

    public void Submit2(View view) {
        Intent intent= new Intent(this,SetTimeActivity.class) ;
        startActivity(intent);
    }

    public void Submit3(View view) {
        Intent intent= new Intent(this,NotesActivity.class) ;
        startActivity(intent);
    }

    public void Submit4(View view) {
        Intent intent= new Intent(this,SlideShowActivity.class) ;
        startActivity(intent);
    }

    public void Submit5(View view) {
        Intent intent= new Intent(this,ArrayListActivity.class) ;
        startActivity(intent);
    }

}