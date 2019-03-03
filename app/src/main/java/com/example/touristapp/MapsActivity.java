package com.example.touristapp;

import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    } // onCreate method

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 7.0f;

        // Add a markers of every destination and move the camera

        // Царевец
        LatLng carevec = new LatLng(43.084030f, 25.652586f);
        mMap.addMarker(new MarkerOptions().position(carevec).title("Царевец")).showInfoWindow();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carevec, zoomLevel));

        // Чудни мостове
        LatLng chydniMostove = new LatLng(41.819929f, 24.581748f);
        mMap.addMarker(new MarkerOptions().position(chydniMostove).title("„Чудните Мостове“")).showInfoWindow();

        // Ягодинска Пещера
        LatLng yagodinskaPeshtera = new LatLng(41.628984f, 24.329589f);
        mMap.addMarker(new MarkerOptions().position(yagodinskaPeshtera).title("Ягодинска Пещера")).showInfoWindow();

        // Връх Снежанка
        LatLng vruhSnejanka = new LatLng(41.638506f, 24.675594f);
        mMap.addMarker(new MarkerOptions().position(vruhSnejanka).title("Връх Снежанка")).showInfoWindow();

        // Белоградчишки скали
        LatLng belogradchiskiSkali = new LatLng(43.623361f, 22.677964f);
        mMap.addMarker(new MarkerOptions().position(belogradchiskiSkali).title("Белоградчишки Скали")).showInfoWindow();

        // Пещера „Леденика“
        LatLng peshteraLedenika = new LatLng(43.204703f, 23.493687f);
        mMap.addMarker(new MarkerOptions().position(peshteraLedenika).title("Пещера „Леденика“")).showInfoWindow();

        // Паметник На Христо Ботев И Неговата Чета
        LatLng pametneikHristoBotev = new LatLng(43.798045f, 23.677926f);
        mMap.addMarker(new MarkerOptions().position(pametneikHristoBotev).title("Паметник На Христо Ботев И Неговата Чета")).showInfoWindow();

        // Национален Музей "Параход Радецки"
        LatLng myzeiParahodRadecki = new LatLng(43.799125f, 23.676921f);
        mMap.addMarker(new MarkerOptions().position(myzeiParahodRadecki).title("Национален Музей 'Параход Радецки'")).showInfoWindow();

        // Археологически Резерват „Калиакра”
        LatLng rezervatKaliakra = new LatLng(43.361190f, 28.465788f);
        mMap.addMarker(new MarkerOptions().position(rezervatKaliakra).title("Археологически Резерват „Калиакра”")).showInfoWindow();

        // Перперикон
        LatLng perperikon = new LatLng(41.718126f, 25.468954f);
        mMap.addMarker(new MarkerOptions().position(perperikon).title("Перперикон")).showInfoWindow();

        // Вр. Мусала (2925 М.) - Рила
        LatLng vruhMysala = new LatLng(42.180021f, 23.585167f);
        mMap.addMarker(new MarkerOptions().position(vruhMysala).title("Вр. Мусала (2925 М.) - Рила")).showInfoWindow();

        // Връх Шипка – Национален Парк-Музей „Шипка“ - Паметник На Свободата
        LatLng vruhShipka = new LatLng(42.748281f, 25.321387f);
        mMap.addMarker(new MarkerOptions().position(vruhShipka).title("Връх Шипка – Национален Парк-Музей „Шипка“ - Паметник На Свободата")).showInfoWindow();

        // Пещера – Пещера „Снежанка“ (Дължина: 145 М)
        LatLng peshteraSnejanka = new LatLng(42.004459f, 24.278645f);
        mMap.addMarker(new MarkerOptions().position(peshteraSnejanka).title("Пещера – Пещера „Снежанка“ (Дължина: 145 М)")).showInfoWindow();

        // Античен Театър
        LatLng antichenTeatur = new LatLng(42.147109f, 24.751005f);
        mMap.addMarker(new MarkerOptions().position(antichenTeatur).title("Античен Театър")).showInfoWindow();

        // Асенова Крепост
        LatLng asenovaKrepost = new LatLng(41.987020f, 24.873552f);
        mMap.addMarker(new MarkerOptions().position(asenovaKrepost).title("Асенова Крепост")).showInfoWindow();

        // Бачковски Манастир
        LatLng bachkovskiManastir = new LatLng(41.942380f, 24.849340f);
        mMap.addMarker(new MarkerOptions().position(bachkovskiManastir).title("Бачковски Манастир")).showInfoWindow();

        // Резерват „Сребърна“
        LatLng rezervatSreburna = new LatLng(44.115654f, 27.071807f);
        mMap.addMarker(new MarkerOptions().position(rezervatSreburna).title("Резерват „Сребърна“")).showInfoWindow();

        // Мадарски Конник
        LatLng madarskiKonnik = new LatLng(43.277708f, 27.118949f);
        mMap.addMarker(new MarkerOptions().position(madarskiKonnik).title("Мадарски Конник")).showInfoWindow();

        // Седемте Рилски езера
        LatLng sedemteRilskiEzera = new LatLng(42.203413f, 23.319871f);
        mMap.addMarker(new MarkerOptions().position(sedemteRilskiEzera).title("Седемте Рилски езера")).showInfoWindow();

        //Храм-Паметник „Александър Невски“
        LatLng aleksandurNevski = new LatLng(42.696000f, 23.332879f);
        mMap.addMarker(new MarkerOptions().position(aleksandurNevski).title("Храм-Паметник „Александър Невски“")).showInfoWindow();

    } // onMapReady
} // Class

