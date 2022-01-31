package com.example.myapplication28;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter  extends ArrayAdapter<Assigntment> {
    private Context context;
    private int resource;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Assigntment> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;//this is the item row resource,the design for each row
    }
    /*
     getView() method. This view is called when a listItem needs to be created and
    populated with the data.In this method first the View is inflated using the
    LayoutInflator.inflate() method. It is important that you check that if the view
    you are trying to inflate is new or reused
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      View view=convertView;
      if(view==null)
          view= LayoutInflater.from(context).inflate(resource,parent,false);
      Assigntment item =getItem(position);//method from the android studio, not related to Item object
      if(item==null){
          ImageView imageView=view.findViewById(R.id.checkBox);

          Button button = view.findViewById(R.id.buttonstart1);
          TextView textViewDate=view.findViewById(R.id.textViewDate);
          textViewDate.setText(item.getDuedate());
         button.setOnClickListener(new OnClickListener() {
             @Override
             public void onClick(View view) {
                 Toast.makeText(context,"This item was added to shopping cart",Toast.LENGTH_LONG).show();

             }
         });




      }
     return view;
    }

}
