package com.example.myapplication28;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUPActivity extends AppCompatActivity {
    private static final String TAG = "FIREBASE" ;
    private EditText editTextfullname,editTextUsername,editTextGmail,editTextAge,editTextPassword;
    private Button buttonSubmit;
    private FirebaseAuth mAuth;
    private FirebaseAuth maFirebaseAuth=FirebaseAuth.getInstance();
    //write a message to the dataBase
    // gets the root of the real time database in the FB console
    private FirebaseDatabase database = FirebaseDatabase.getInstance("https://amal-s-project-default-rtdb.europe-west1.firebasedatabase.app/");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        editTextfullname=findViewById(R.id.editTextfullname);
        editTextUsername=findViewById(R.id.editTextUsername);
        editTextGmail=findViewById(R.id.editTextGmail);
        editTextAge=findViewById(R.id.editTextAge);
        editTextPassword=findViewById(R.id.editTextPassword);
        buttonSubmit=findViewById(R.id.buttonSubmit);
        mAuth = FirebaseAuth.getInstance();//gets the instance of the firebase connected to the project
    }

    public void Submit(View view) {
        signup(editTextGmail.getText().toString(),editTextPassword.getText().toString());

    }

    private void signup (String email,String password)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            DatabaseReference myRef=database.getReference("profiles/"+user.getUid());
                            String key=myRef.push().getKey();
                            User u1=new User(editTextfullname.getText().toString(),email,editTextUsername.getText().toString(),editTextAge.getText().toString());
                            u1.setKey(key);
                            myRef=database.getReference("profiles/"+user.getUid()+"/"+key);
                            myRef.setValue(u1);

                            Intent i=new Intent(SignUPActivity.this,MainActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUPActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

}