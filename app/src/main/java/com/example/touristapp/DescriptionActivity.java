package com.example.touristapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    TextView destTitle, destDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageView img = findViewById(R.id.destImg);
        destTitle = findViewById(R.id.destinationTitle);
        destDescription = findViewById(R.id.descriptionSection);

        // Get destination Title & Description.
        Bundle b = this.getIntent().getExtras();
        final String[] destinationInfo = b.getStringArray("destinationInfo");

        destDescription.setMovementMethod(new ScrollingMovementMethod());

        //Log.i("DestinationInfo", destinationInfo[1]);

        destTitle.setText(destinationInfo[0]);
        destDescription.setText(destinationInfo[1]);


        CheckBox checkBox = findViewById( R.id.checkBox );
        /*
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    // perform logic
                }


            }
        }); // onCheck button listener

        */

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Check distance between current GPS Location and the destination.

                if(((CompoundButton) view).isChecked()){
                    System.out.println("Checked");
                } else {
                    System.out.println("Un-Checked");
                }
            }
        });

    } // onCreate
}
