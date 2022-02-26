package com.example.myapplication28;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener , DialogInterface.OnClickListener{
    private static final String TAG = "FIREBASE" ;
    private EditText editTextEmail,editTextPassword;
    private Button buttonLogin,buttonSignUP;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextEmail=findViewById(R.id. editTextEmail);
        editTextPassword=findViewById(R.id.editTextPassword);
        buttonLogin=findViewById(R.id.buttonLogin);
        //sets the required button to respond to long click,otherwise it won't
        buttonLogin.setOnLongClickListener(this);
        buttonSignUP=findViewById(R.id.buttonSignUP);
        //Returns a reference to the instance of the project firebase
        mAuth = FirebaseAuth.getInstance();
        SharedPreferences sp=getSharedPreferences("settings",MODE_PRIVATE);
        String email=sp.getString("email","");
        String password=sp.getString("password","");

        if(!email.equals("")&&!password.equals(""))
        {
            editTextEmail.setText(email);
            editTextPassword.setText(password);
        }
    }
    public void login(View view){
        Intent intent= new Intent(this,IntroActivity.class) ;
        if(!editTextEmail.getText().toString().equals("")&&editTextEmail.getText().toString().contains("@")&&editTextEmail.getText().toString().contains("."))
        {
            //saving email and password of user in a local file for future use
            //create sp
            SharedPreferences sp=getSharedPreferences("settings",MODE_PRIVATE);

            //open editor for editing
            SharedPreferences.Editor editor=sp.edit();

            //write the wanted settings
            editor.putString("email",editTextEmail.getText().toString());
            editor.putString("password",editTextPassword.getText().toString());

            //save and close file
            editor.commit();

            intent.putExtra("name", editTextEmail.getText().toString());

            login(editTextEmail.getText().toString(), editTextPassword.getText().toString());

        }
    }

    public void SignUP(View view) {
        Intent intent1= new Intent(this,SignUPActivity.class) ;
        startActivity(intent1);
    }

    @Override
    public boolean onLongClick(View view) {
        editTextEmail.setText("");
        editTextPassword.setText("");
        return true;
    }

    public void onBackPressed()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you sure?");
        builder.setCancelable(false);
        builder.setPositiveButton("YES",this);
        builder.setNegativeButton("NO",this);
        AlertDialog dialog= builder.create();
        dialog.show();
    }
    public void onClick(DialogInterface dialog, int which)
    {
     if(which==dialog.BUTTON_POSITIVE)
     {
         super.onBackPressed();
         dialog.cancel();
     }
     if(which==dialog.BUTTON_NEGATIVE)
     {
         dialog.cancel();
     }

    }
public void login(String email,String password)
{
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                      Intent i =new Intent(MainActivity.this,CreateNoteActivity.class);
                      startActivity(i);
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();

                    }

                    // ...
                }
            });
}
}