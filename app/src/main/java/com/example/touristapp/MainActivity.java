package com.example.touristapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.touristapp.DatabaseHelper.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database
        myDb = new DatabaseHelper(this);

        // Buttons onclick events
        Button loginBtn = findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        Button regBtn = findViewById(R.id.regBtn);
        regBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });


        Button newDbBtn = findViewById(R.id.newDbBtn);
        newDbBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // myDb.reCreateTable1();
                myDb.emptyTables();
                Toast.makeText(MainActivity.this, "Database dropped!", Toast.LENGTH_LONG).show();
            }
        });


        Button destListBtn = findViewById(R.id.destListBtn);
        destListBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DestinationsActivity.class));
            }
        });



        Button destBtn = findViewById(R.id.pullDestBtn);
        destBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                List<String> result = myDb.getDestinations();
                //Log.i("TAG", result.get(1) +  " " + result.get(9)); // През 8 индекса достъпваме еднакви колони (e.g. Title of destinations)

                // Number of TOTAL columns.
                int size = result.size();
                //Log.i("TAG", Integer.toString(size)); // 16

                // Every record stored here:
                String[] resultArr = new String[size];

                int index = 1;
                for(int i = 0; i < size; i++){
                    //Log.i("TAG", result.get(index - 1) + " \n");
                    resultArr[i] = result.get(index - 1);
                    index++;
                }

                for(int j = 0; j < resultArr.length; j++){
                    Log.i("resultArr", resultArr[j]);
                }

            } // onClick
        });


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
                Intent mapsIntent = new Intent(MainActivity.this, MapsActivity.class);
                mapsIntent.putExtras(b);
                startActivity(mapsIntent);
            }
        });

    } // onCreate Method

}  // Class
