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

import java.util.List;

public class UserActivity extends AppCompatActivity {
    TextView nickName, mail;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nickName = findViewById(R.id.UserNick);
        mail =  findViewById(R.id.UserMail);

        // Get user's mail and nick name from LoginActivity and display them here.
        Bundle b=this.getIntent().getExtras();
        final String[] userCred = b.getStringArray("UserCredentials");

        mail.setText(userCred[0]);
        nickName.setText(userCred[2]);
        //Toast.makeText(UserActivity.this, userCred[1] + " " + userCred[2], Toast.LENGTH_LONG).show();

        myDb=new DatabaseHelper(this);

        Button delDbBtn = findViewById(R.id.deleteProfileBtn);
        delDbBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                myDb.deleteUser(userCred[0], userCred[2]);
                Toast.makeText(UserActivity.this, "User Removed!", Toast.LENGTH_LONG).show();

                startActivity(new Intent(UserActivity.this, MainActivity.class));
            }
        });
        //logOutBtn

        Button logoutDbBtn = findViewById(R.id.logOutBtn);
        logoutDbBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mail.setText("");
                nickName.setText("");

                startActivity(new Intent(UserActivity.this, MainActivity.class));
            }
        });

        // List view.
        Button destListBtn = findViewById(R.id.destListBtn);
        destListBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, DestinationsActivity.class));
            }
        });

        Button visitstBtn = findViewById(R.id.visitsBtn);
        visitstBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(UserActivity.this, Visits.class));
            }
        });


        // Google maps view.
        Button destinationsBtn = findViewById(R.id.destBtn);
        destinationsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Bundle b = new Bundle();

                List<String> result = myDb.getDestinations();

                // Number of TOTAL columns.
                int size = result.size();

                // Every record stored here:
                String[] resultArr = new String[size];

                int index = 1;
                for(int i = 0; i < size; i++){
                    resultArr[i] = result.get(index - 1);
                    index++;
                }

                b.putStringArray("Records", resultArr);
                Intent mapsIntent = new Intent(UserActivity.this, MapsActivity.class);
                mapsIntent.putExtras(b);
                startActivity(mapsIntent);
            }
        });



    } // onCreate method
}
