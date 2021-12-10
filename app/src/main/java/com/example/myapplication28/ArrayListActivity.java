package com.example.myapplication28;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ArrayListActivity extends AppCompatActivity {

    //the object of the view -design
    private ListView myListView;
    //the object for the adapter connecting the data to the view
    private CustomAdapter myAdapter;
    //the object containing the items to be displayed - Data
    private ArrayList<Item> list;

  //Get instance of Authentication Project in FB console
  private   FirebaseAuth maFirebaseAuth=FirebaseAuth.getInstance();
  //Gets the root of the Real Time Database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://amal-s-project-default-rtdb.europe-west1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_list);
        // Write a message to the database

        String UID=maFirebaseAuth.getUid();
        DatabaseReference myRef = database.getReference("users");
        //adds an item to the firebase under the referenced specified

        myRef.push().setValue(new Item("This is my first item",R.drawable.background,true,50));
        list = new ArrayList<>();
        list.add(new Item("This is my firstItem",R.drawable.mybackground,true,50));
        list.add(new Item("This is my secondItem",R.drawable.background,true,50));
        list.add(new Item("This is my thirdItem",R.drawable.background,true,50));
        list.add(new Item("This is my forthItem",R.drawable.background,true,50));
        list.add(new Item("This is my fifthItem",R.drawable.background,true,50));

        //reference to the list view so it can programmed
        myListView = findViewById(R.id.myListView);
        //connect adapter with data
        myAdapter = new CustomAdapter(this, R.layout.assignment_row, list);
        //connect adapter with view
        myListView.setAdapter(myAdapter);
        //connects click listener to items in the list
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Toast.makeText(getApplicationContext(),"Item:"+list.get(i),Toast.LENGTH_LONG).show();
            }
        });
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                list.remove(i);
                 myAdapter.notifyDataSetChanged();
                return false;
            }
        });
          myRef.addValueEventListener(new ValueEventListener() {
              @Override
              public void onDataChange(@NonNull DataSnapshot snapshot) {
                  for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                      Item item1=dataSnapshot.getValue(Item.class);
                      list.add(item1);
                      myAdapter.notifyDataSetChanged();
                  }
              }

              @Override
              public void onCancelled(@NonNull DatabaseError error) {

              }
          });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.settings_menu:
                Toast.makeText(this,"Settings",Toast.LENGTH_LONG).show();
                break;
            case R.id.exit_Menu :
                //closeApplication();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}