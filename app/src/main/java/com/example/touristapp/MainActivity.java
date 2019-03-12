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
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import static com.example.touristapp.DatabaseHelper.DATABASE_NAME;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private LocationManager locationManager;
    private LocationListener locationListener;
    DatabaseHelper myDb;

    // Create each location by setting it's lat and lon. ( Eventually move to 'Destination Screen' activity later. )
    public Location LocationFactory(float lat, float lon){
        Location destination = new Location("");
        destination.setLatitude(lat);
        destination.setLongitude(lon);

        return destination;
    }

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


        /*
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
        */


        // Refactor: put extra info ( result from above ) and in destinationActivity run loop and generate the destinations on the map.
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


        // ( Eventually move to 'Destination Screen' activity later. )
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


       //Add current GPS location:
       locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                    // Get lat and lng of the device
                    float latitude = (float) location.getLatitude();
                    float longtitude = (float) location.getLongitude();

                    Location myLocation = LocationFactory(latitude, longtitude );

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
        }

        /*
        Functionality in "Destination screen activity" for checking wheter location is in the desirable radius:

        float distanceInMeters = myLocation.distanceTo(clickedLocation);

        if( distanceInMeters <= 5000 ){
            Log.i(TAG, "Within distance");
        }
        else{
            Log.i(TAG, "Out of radius");
        }
       */
    } // onCreate Method
}  // Class
