package com.fraint.eco;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.AppBarLayout;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ConfirmacionMAPS extends FragmentActivity implements OnMapReadyCallback{

    private GoogleMap map;
    private int MY_LOCATION_REQUEST;
    private double Lat = 0.0;
    private double Long= 0.0;
    private Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_maps);

        SupportMapFragment mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST);
        }
        else
        {
            ubicacion();
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED &&
                                                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
        {
            ubicacion();
        }
        /*LatLng sydney=new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


    }

    private void marcador(double Lat, double Long)
    {
        LatLng coordenadas=new LatLng(Lat, Long);
        CameraUpdate ubicacion=CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if(marcador == null)
        {
            marcador=map.addMarker(new MarkerOptions().position(coordenadas).title("Direccion"));
            map.animateCamera(ubicacion);
        }
    }

    private void actualizar(Location location)
    {
        if(location!=null)
        {
            Lat =location.getLatitude();
            Long=location.getLongitude();
            marcador(Lat, Long);
        }
    }

    private void ubicacion()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED)
        {
            return;
        }

        LocationManager locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location =locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizar(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        //------------------------------direccion
        TextView direccion=findViewById(R.id.AgregarDireccion);
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion.setText(DirCalle.getAddressLine(0));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            actualizar(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


}
