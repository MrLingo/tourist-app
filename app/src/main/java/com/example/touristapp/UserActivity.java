package com.example.touristapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserActivity extends AppCompatActivity {
    TextView nickName, mail;

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
        String[] userCred = b.getStringArray("UserCredentials");

        mail.setText(userCred[0]);
        nickName.setText(userCred[2]);
        Toast.makeText(UserActivity.this, userCred[0] + " " + userCred[2], Toast.LENGTH_LONG).show();

    } // onCreate method
}
