package com.example.touristapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {
    TextView nickName, mail;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nickName = (TextView) findViewById(R.id.UserNick);
        mail = (TextView) findViewById(R.id.UserMail);

        // Get user's mail and nick name from LoginActivity and display them here.
        Bundle b=this.getIntent().getExtras();
        final String[] userCred = b.getStringArray("UserCredentials");

        mail.setText(userCred[0]);
        nickName.setText(userCred[2]);
        //Toast.makeText(UserActivity.this, userCred[1] + " " + userCred[2], Toast.LENGTH_LONG).show();

        //deleteProfilBtn

        myDb=new DatabaseHelper(this);

        Button delDbBtn = findViewById(R.id.deleteProfileBtn);
        delDbBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // myDb.reCreateTable1();
                myDb.deleteUser(userCred[0], userCred[2]);
                Toast.makeText(UserActivity.this, "User Removed!", Toast.LENGTH_LONG).show();

                // redirect

                startActivity(new Intent(UserActivity.this, MainActivity.class));
            }
        });
        //logOutBtn

        Button logoutDbBtn = findViewById(R.id.logOutBtn);
        logoutDbBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mail.setText("");
                nickName.setText("");
                // redirect

                startActivity(new Intent(UserActivity.this, MainActivity.class));
            }
        });
    } // onCreate method
}
