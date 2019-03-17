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
import java.util.List;

import static android.media.CamcorderProfile.get;

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

        mailEditText = (EditText) findViewById(R.id.logMailInput);
        passEditText = (EditText) findViewById(R.id.logPassInput);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        myDb = new DatabaseHelper(this);

        PullData();

    } // onCreate method

    public boolean validateLogin(List<String> result, String mail, String password){
          if(result.size() == 0){
              Toast.makeText(LoginActivity.this, "Няма такъв потребител!" , Toast.LENGTH_LONG).show();
              return false;
          }
          else if(mail.matches("") || password.matches("")){
              return false;
          }
          else if( mail.equals(result.get(0)) || mail.equals(result.get(2)) && password.equals(result.get(1))){
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
                        List<String> ress = myDb.pullUserInfo();

                            if(validateLogin(ress, mailField, passField)){
                                Bundle b = new Bundle();
                                String[] columnsArr = new String[3];

                                // Transfering values from the list to String array:
                                String col1 = ress.get(0);
                                String col2 = ress.get(1);
                                String col3 = ress.get(2);

                                columnsArr[0] = col1;
                                columnsArr[1] = col2;
                                columnsArr[2] = col3;

                                b.putStringArray("UserCredentials", columnsArr);

                                // Redirect to user page
                                Intent loginIntent = new Intent(LoginActivity.this, UserActivity.class);
                                loginIntent.putExtras(b);
                                startActivity(loginIntent);

                                Toast.makeText(LoginActivity.this, "Успешен логин!", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(LoginActivity.this, "Некоректно попълнена форма! Моля опитайте отново. \n", Toast.LENGTH_LONG).show();
                            }

                    } // onClick
                } // OnClickListener
        );
    } // PullData

} // Class
