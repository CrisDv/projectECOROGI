package com.fraint.eco;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ConfirmacionMAPS extends AppCompatActivity implements OnMapReadyCallback{

    private Marker punto1;
    private double Lat=0.0;
    private double Long=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmacion_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void permiso ()
    {


    }

    private void marcador()
    {
        GoogleMap mMap = null;
        LatLng posicion=new LatLng(Lat, Long);
        CameraUpdate PosicionUsuario = CameraUpdateFactory.newLatLngZoom(posicion, 16);

        if (punto1!=null)
        {
            punto1.remove();
        }

        punto1=mMap.addMarker(new MarkerOptions().position(posicion).title("Lugar En El Que Estas"));//poner el marcador
        mMap.animateCamera(PosicionUsuario);//mover la camara de posicion a posicion
    }

    private void ActualizarPosicion(Location location)
    {
        //Obtener latitud y longitud actual
        if(location!=null)
        {
            Lat=location.getLatitude();
            Long=location.getLongitude();
        }
    }

    LocationListener LocalListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            ActualizarPosicion(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miubicacion ()
    {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED)
        {
                return;//permiso de ubicacion
        }

        LocationManager locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location=locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);//Conocer la ultima vez que se localizo
        ActualizarPosicion(location);

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, LocalListener);//Actualizar posicion cada 5 Segundos

    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.

        miubicacion();
       /* LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
    }
}
