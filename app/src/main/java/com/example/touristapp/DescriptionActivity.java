package com.example.touristapp;

import android.Manifest;
import android.content.pm.PackageManager;
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

public class DescriptionActivity extends AppCompatActivity {

    TextView destTitle, destDescription;
    private LocationManager locationManager;
    private LocationListener locationListener;

    // Create each location by setting it's lat and lon. ( Eventually move to 'Destination Screen' activity later. )
    public Location LocationFactory(float lat, float lon) {
        Location destination = new Location("");
        destination.setLatitude(lat);
        destination.setLongitude(lon);

        return destination;
    }

    public boolean calculateDistance(Location myLocation, Location destinationToVisit) {
        float distanceInMeters = myLocation.distanceTo(destinationToVisit);

        if (distanceInMeters <= 5000) {
            //Log.i("Within radius!", "Within distance");
            System.out.println("Within radius");
            return true;
        } else {
            //Log.i("Outside radius!", "Out of radius");
            System.out.println("Outside radius");
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

        //Log.i("DestinationInfo", destinationInfo[1]);

        destTitle.setText(destinationInfo[0]);
        destDescription.setText(destinationInfo[1]);

        final CheckBox checkBox = findViewById(R.id.checkBox);

        // ( Eventually move to 'Destination Screen' activity later. )
        /*
        Location carevec = LocationFactory(43.084030f, 25.652586f );
        Location chydniMostove = LocationFactory(41.819929f, 24.581748f );
        Location yagodinskaPeshtera = LocationFactory(41.628984f, 24.329589f );
        Location vruhSnejanka = LocationFactory(41.638506f, 24.675594f );
        Location belogradchishkiSkali = LocationFactory(43.623361f, 22.677964f );
        Location peshteraLedenika = LocationFactory(43.204703f, 23.493687f );
        Location pametnikHristoBotev = LocationFactory(43.798045f, 23.677926f);
        Location myzeiParahodRadecki = LocationFactory(43.799125f, 23.676921f );
        Location rezervatKaliakra = LocationFactory(43.361190f, 28.465788f );
        Location perperikon = LocationFactory(41.718126f, 25.468954f );
        Location vruhMysala = LocationFactory(42.180021f, 23.585167f);
        Location vruhShipka = LocationFactory(42.748281f, 25.321387f );
        Location peshteraSnejanka = LocationFactory(42.004459f, 24.278645f );
        Location antichenTeatur = LocationFactory(42.147109f, 24.751005f );
        Location asenovaKrepost = LocationFactory(41.987020f, 24.873552f );
        Location bachkovskiManastir = LocationFactory(41.942380f, 24.849340f );
        Location rezervatSreburna = LocationFactory(44.115654f, 27.071807f );
        Location madarskiKonnik = LocationFactory(43.277708f, 27.118949f );
        Location sedemteRilskiEzera = LocationFactory(42.203413f, 23.319871f );
        Location aleksandurNevski = LocationFactory(42.696000f, 23.332879f );
         */

        //Add current GPS location:

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

                    System.out.println("Here ?");

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
                            float destLatitude = Float.parseFloat(destinationInfo[2].replace("\"", ""));
                            float destLongitude = Float.parseFloat(destinationInfo[3].replace("\"", ""));

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
                    float destLatitude = Float.parseFloat(destinationInfo[2].replace("\"", ""));
                    float destLongitude = Float.parseFloat(destinationInfo[3].replace("\"", ""));

                    Location destinationToVisit = LocationFactory(destLatitude, destLongitude);
                    boolean isInsideRadius = calculateDistance(myLocation, destinationToVisit);

                    if(isInsideRadius){
                        checkBox.setChecked(true);
                    }
                    else{
                        checkBox.setChecked(false);
                    }

                }


                /*
                if(((CompoundButton) view).isChecked()){
                    System.out.println("Checked");
                } else {
                    System.out.println("Un-Checked");
                }
                */
            } //onClick
        }); // onClickCheckBox Listener.

    } // onCreate
}
