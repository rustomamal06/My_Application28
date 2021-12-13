package com.example.myapplication28;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SetTimeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
  private Spinner spinner1;
  private Spinner spinner2;
  private Button startbutton;
  private String time;
  private int[] minutes = {25*60*1000, 45*60*1000, 60*60*1000};
  private long selectedTime = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);
        spinner1= findViewById(R.id.timespinner);
        spinner2=findViewById(R.id.categoryspinner);
        startbutton=findViewById(R.id.buttonstart);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.time, android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.category, android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
         spinner1.setOnItemSelectedListener(this);
         spinner2.setAdapter(adapter2);
         spinner2.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            selectedTime = minutes[i];
            String choice =adapterView.getItemAtPosition(i).toString();
            Toast.makeText(getApplicationContext() , choice, Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void Submit(View view) {
        Intent intent= new Intent(this,TimerActivity.class) ;
        intent.putExtra("TIME",selectedTime);
        startActivity(intent);
    }
}