package com.example.touristapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DestinationsActivity extends AppCompatActivity {
    ArrayList<String> stringArr;
    ArrayAdapter adapter;
    ListView myListView;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destinations);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myDb = new DatabaseHelper(this);

        myListView = findViewById(R.id.listView);

        stringArr = new ArrayList<>();

        final List<String> result = myDb.getDestinations();

        int index = 1;
        for(int i = 0; i < result.size(); i++){
            if(index == result.size() - 7){
               stringArr.add(result.get(index));
               break;
            }
            stringArr.add(result.get(index));
            index+=8;
        }

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DestinationsActivity.this, stringArr.get(position), Toast.LENGTH_LONG).show();
                Bundle b = new Bundle();

                // Final result array
                String[] destArr = new String[3];

                //result.set(position, null);
                if(position == 0){
                    destArr[0] = result.get(1);
                    destArr[1] = result.get(2);
                    destArr[2] = result.get(3);
                }

                //Log.i("DestinationInfo", destArr[0] + " ||| " + destArr[1] + " ||| " + destArr[2]);

                b.putStringArray("destinationInfo", destArr);
                Intent destinationsIntent = new Intent(DestinationsActivity.this, DescriptionActivity.class);
                destinationsIntent.putExtras(b);

                startActivity(destinationsIntent);
            }
        });

        adapter = new ArrayAdapter(DestinationsActivity.this, R.layout.single_list_item, R.id.single_item, stringArr);
        myListView.setAdapter(adapter);

    } // onCreate
}
