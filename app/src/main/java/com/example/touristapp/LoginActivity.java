package com.example.touristapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText mailEditText, passEditText;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        mailEditText = (EditText) findViewById(R.id.logMailInput);
        passEditText = (EditText) findViewById(R.id.logPassInput);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        PullData();

    } // onCreate method

    // ress[0] = mail, ress[1] = pass, ress[2] = nick name
    public boolean validateLogin(String[] result, String mail, String password){
        //Toast.makeText(LoginActivity.this, result[2], Toast.LENGTH_LONG).show();
          if(mail.matches("") || password.matches("")){
              return false;
          }
          else if( mail.equals(result[0]) || mail.equals(result[2]) && password.equals(result[1])){
              return true;
          }
          else{
              return false;
          }
    }

    public void PullData(){
        loginBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mailField = mailEditText.getText().toString();
                        String passField = passEditText.getText().toString();
                        String[] ress = myDb.pullUserInfo();

                        // Check if there is such user.
                        if(ress[0].equals("Not such user!")){
                            Toast.makeText(LoginActivity.this, "Not such user! \n", Toast.LENGTH_LONG).show();
                        }
                        else{
                            if(validateLogin(ress, mailField, passField)){
                                // redirect to user page
                                //Intent loginIntent = new Intent(LoginActivity.this, UserActivity.class);
                                //loginIntent.putExtra("User credentials", ress);
                                Bundle b=new Bundle();
                                b.putStringArray("UserCredentials", ress);   //new String[]{value1, value2});
                                Intent loginIntent=new Intent(LoginActivity.this, UserActivity.class);
                                loginIntent.putExtras(b);
                                startActivity(loginIntent);
                                Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Something went wrong! Try again. \n", Toast.LENGTH_LONG).show();
                            }
                            //Toast.makeText(LoginActivity.this, ress[0] + " " + ress[1] + " " + ress[2] + "\n", Toast.LENGTH_LONG).show();
                        }
                    } // onClick
                } // OnClickListener
        );
    } // PullData

} // Class
