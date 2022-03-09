package com.example.myapplication28;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class DetailsActivity extends AppCompatActivity {
private TextView fullname;
    private TextView email;
    private TextView age;
    private TextView username;
    private String image;

private FirebaseDatabase database=FirebaseDatabase.getInstance("https://amal-s-project-default-rtdb.europe-west1.firebasedatabase.app/");
   private FirebaseAuth mAuth =FirebaseAuth.getInstance();
   private FirebaseUser user= mAuth.getCurrentUser();
    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;
    //attributes
    private Button buttonCamera, buttonGallery;
    private ImageView imageViewProfile;
private     DatabaseReference myRef = database.getReference("user/" + user.getUid());

    //for picture of camera
    private Bitmap picture;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        fullname=findViewById(R.id.fullname);
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                User u = dataSnapshot.getValue(User.class);
                updateUserData(new User(u.getFullname(), u.getGmail(), u.getUsername(), u.getAge()));
            }
        }


        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
    //gets reference for the design components
    buttonCamera=findViewById(R.id.buttonCamera);
    buttonCamera.setOnClickListener((View.OnClickListener) this);

    buttonGallery=findViewById(R.id.buttonGallery);
    buttonGallery.setOnClickListener((View.OnClickListener) this);

    imageViewProfile=findViewById(R.id.imageViewProfile);


    }
    private void updateUserData(User user){
       fullname.setText(user.getFullname());

    }

    public void saveImage(Bitmap bitmap)
    {
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        image= Base64.encodeToString(arr, Base64.DEFAULT);
    }

    public void onClick(View view) {
        if(view.getId() ==R.id.buttonCamera){
            Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i, CAMERA_REQUEST);
        }else{
            Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i,GALLERY_REQUEST);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== CAMERA_REQUEST){
            if(resultCode==RESULT_OK){
                picture=(Bitmap) data.getExtras().get("data");
                //set image captured to be the new image
                imageViewProfile.setImageBitmap(picture);
                saveImage(picture);
                myRef.se

            }
        }else{
            if(resultCode ==RESULT_OK){
                Uri targetUri = data.getData();
                try {
                    //Decode an input stream into bitmap
                    picture = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    imageViewProfile.setImageBitmap(picture);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

    }
}