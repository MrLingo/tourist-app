package com.example.touristapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DescriptionActivity extends AppCompatActivity {

    TextView destTitle, destDescription;
    private LocationManager locationManager;
    private LocationListener locationListener;

    // Create each location by setting it's lat and lon.
    public Location LocationFactory(float lat, float lon) {
        Location destination = new Location("");
        destination.setLatitude(lat);
        destination.setLongitude(lon);

        return destination;
    }

    public boolean calculateDistance(Location myLocation, Location destinationToVisit) {
        float distanceInMeters = myLocation.distanceTo(destinationToVisit);

        if (distanceInMeters <= 5000) {
            System.out.println("Within radius");
            Toast.makeText(DescriptionActivity.this, "Посетено!", Toast.LENGTH_LONG).show();
            return true;
        } else {
            System.out.println("Outside radius");
            Toast.makeText(DescriptionActivity.this, "Извън радиус!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

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

        destTitle.setText(destinationInfo[0]);
        destDescription.setText(destinationInfo[1]);

        // ---------------------------------------------

        // Change destination image from a string.

        Resources res = getResources();
        String mDrawableName = destinationInfo[2];
        int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        img.setImageDrawable(drawable);


        // ---------------------------------------------

        final CheckBox checkBox = findViewById(R.id.checkBox);

        //Add current GPS location:

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    if (ActivityCompat.checkSelfPermission(DescriptionActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(DescriptionActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {

                        @Override
                        public void onLocationChanged(Location location) {

                            // Get lat and lng of the device
                            float latitude = (float) location.getLatitude();
                            float longtitude = (float) location.getLongitude();

                            Location myLocation = LocationFactory(latitude, longtitude);

                            // Get latitude and longitude strings of the clicked destination and parse them into floats.
                            float destLatitude = Float.parseFloat(destinationInfo[3].replace("\"", ""));
                            float destLongitude = Float.parseFloat(destinationInfo[4].replace("\"", ""));

                            //
                            Location destinationToVisit = LocationFactory(destLatitude, destLongitude);
                            boolean isInsideRadius = calculateDistance(myLocation, destinationToVisit);

                            if(isInsideRadius){
                                checkBox.setChecked(true);
                            }
                            else{
                                checkBox.setChecked(false);
                            }

                        } // OnLocationChanged method

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    });
                }
                // If GPS connection cannot be established create hardcoded instance
                else {
                    Location myLocation = LocationFactory(42.131612f, 24.783277f);
                    float destLatitude = Float.parseFloat(destinationInfo[3].replace("\"", ""));
                    float destLongitude = Float.parseFloat(destinationInfo[4].replace("\"", ""));

                    Location destinationToVisit = LocationFactory(destLatitude, destLongitude);
                    boolean isInsideRadius = calculateDistance(myLocation, destinationToVisit);

                    if(isInsideRadius){
                        checkBox.setChecked(true);

                    }
                    else{
                        checkBox.setChecked(false);
                    }
                }
            } //onClick
        }); // onClickCheckBox Listener.

    } // onCreate
}
