package com.example.touristapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button registerButton;
    EditText mailInput, nickInput, passInput, repeatPassInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        myDb = new DatabaseHelper(this);

        mailInput = (EditText) findViewById(R.id.regMailInput);
        nickInput = (EditText) findViewById(R.id.regNickInput);
        passInput = (EditText) findViewById(R.id.regPassInput);
        repeatPassInput = (EditText) findViewById(R.id.repeatPassInput);
        registerButton = (Button) findViewById(R.id.regBtn);

        AddData();

    } // onCreate method


    public boolean validateForm(String mail, String nick, String pass, String repeatPass){
         if(mail.matches("") || nick.matches("") || pass.matches("") || repeatPass.matches("")){
             return false;
         }
         else if(!pass.equals(repeatPass)){
             return  false;
         }
         else{
             return true;
         }
    }


    public void AddData(){
        registerButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String mail = mailInput.getText().toString();
                        String nick = nickInput.getText().toString();
                        String pass1 = passInput.getText().toString();
                        String pass2 = repeatPassInput.getText().toString();

                        if(validateForm(mail, nick, pass1, pass2)){
                            boolean isInserted = myDb.addUser(mail, pass1, nick);
                            if(isInserted){
                                Toast.makeText(RegisterActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(RegisterActivity.this, "Data not inserted, please submit the form correctly", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "Empty fields or passwords don't match!", Toast.LENGTH_LONG).show();
                        }
                    } // OnClick method
                } // onClickListener
        );
    } // AddData method
} // class
