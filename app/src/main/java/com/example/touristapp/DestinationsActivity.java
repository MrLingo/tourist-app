package com.example.touristapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

        List<String> result = myDb.getDestinations();

        int index = 1;
        for(int i = 0; i < result.size(); i++){
            if(index == result.size() - 7){
               stringArr.add(result.get(index));
               break;
            }
            stringArr.add(result.get(index));
            index+=8;
        }

        adapter = new ArrayAdapter(DestinationsActivity.this, R.layout.single_list_item, R.id.single_item, stringArr);
        myListView.setAdapter(adapter);

    } // onCreate

}
