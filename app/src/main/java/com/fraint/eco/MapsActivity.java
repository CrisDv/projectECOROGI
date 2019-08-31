package com.fraint.eco;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private int MY_LOCATION_REQUEST;
    private LocationManager locationManager;
    private Marker marcador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        ubicacion();
        spineroptions();
        /*LatLng sydney=new LatLng(-34, 151);
        map.addMarker(new MarkerOptions().position(sydney).title("Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/


    }

    private void marcador(double Lat, double Long) {
        LatLng coordenadas = new LatLng(Lat, Long);
        CameraUpdate ubicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador == null) {
            marcador = map.addMarker(new MarkerOptions().position(coordenadas).title("Direccion"));
            map.animateCamera(ubicacion);
        }

    }

    double Lat = 0.0;
    double Long = 0.0;

    private void actualizar(Location location) {

        if (location != null) {
            Lat = location.getLatitude();
            Long = location.getLongitude();
            marcador(Lat, Long);
        }
    }

    private void ubicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);// PASO DE GPS_PROVIDER A PASSIVE_PROVIDER
        actualizar(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        //------------------------------direccion

        EditText direccion = findViewById(R.id.AgregarDireccion);
        if (location.getLatitude() != 0.0 && location.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        location.getLatitude(), location.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    direccion.setText(DirCalle.getAddressLine(0));
                    String Confirmar_Direccion =DirCalle.getAddressLine(0);
                    Botones();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void Botones()
    {

        Conexion dbhelper=new Conexion(getBaseContext());
        Button BTN_CONFIRM=findViewById(R.id.ConfirmarDireccion);

        EditText apartamento = findViewById(R.id.NoApartamento);
        EditText torre = findViewById(R.id.NoTorre);

        BTN_CONFIRM.setOnClickListener(view -> {


            GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
            EditText direccion=findViewById(R.id.AgregarDireccion);

            Conexionpst post=new Conexionpst();
            PreparedStatement statement= null;
            try {
               if (apartamento.getText().toString().equals("") && torre.getText().toString().equals(""))
               {
                   statement = post.conexionbd().prepareStatement("UPDATE usuarios SET direccion='"+direccion.getText()+"' WHERE correo='"+account.getEmail()+"'");
               }
               else
               {
                   statement = post.conexionbd().prepareStatement("UPDATE usuarios SET direccion='"+direccion.getText()+" T: "+torre.getText()+" APT :"+apartamento.getText()+"'WHERE correo='"+account.getEmail()+"'");
               }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try
            {
                SQLiteDatabase db=dbhelper.getWritableDatabase();


                db.execSQL("UPDATE usuario SET direccion ='"+direccion.getText()+"' WHERE correo='"+account.getEmail()+"'");
                Toast.makeText(this, "Tu Direccion será \n'"+direccion.getText()+"'", Toast.LENGTH_LONG).show();


                Intent intent=new Intent(this, P_InterfazUsuario.class);
                startActivity(intent);
                finish();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
        });

    }

    private void spineroptions()
    {
        ArrayList<String> agregar=new ArrayList<>();
        agregar.add("Casa");
        agregar.add("Apartamento");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, agregar);

        Spinner mSpinner;
        mSpinner=findViewById(R.id.opcion);

        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // String elemento= (String) mSpinner.getAdapter().getItem(i);
                EditText apartamento = findViewById(R.id.NoApartamento);
                EditText torre = findViewById(R.id.NoTorre);

                apartamento.setVisibility(View.INVISIBLE);
                torre.setVisibility(View.INVISIBLE);

                if (mSpinner.getSelectedItemPosition()==1)
                {
                    torre.setVisibility(View.VISIBLE);
                    apartamento.setVisibility(View.VISIBLE);
                }
                else if(mSpinner.getSelectedItemPosition()==1)
                {
                    torre.setVisibility(View.INVISIBLE);
                    apartamento.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
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

    @Override
    protected void onPause() {
        locationManager.removeUpdates(locationListener);
        super.onPause();
    }
}
