package com.fraint.eco;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.text.Layout;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.PreparedStatement;
import java.util.Objects;

public class P_InterfazUsuario extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private TextView mTextMessage;
    private int MY_LOCATION_REQUEST;
    final Handler mhandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(this);

        Toolbar tlbar= findViewById(R.id.toolnarNavInterfaz);
        setSupportActionBar(tlbar);
        getSupportActionBar();

        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.NavViewInterfaz, new frgCategorias()).commit();

        LinearLayout viewLoad=(LinearLayout) findViewById(R.id.loadcontentview);
        viewLoad.setVisibility(View.GONE);
        bdperfile();
        showAlert();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager fmanager =getSupportFragmentManager();

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fmanager.beginTransaction().replace(R.id.NavViewInterfaz, new frgCategorias()).addToBackStack(null).commit();

                break;
            case R.id.navigation_notifications:
                fmanager.beginTransaction().replace(R.id.NavViewInterfaz, new frgPerfil()).addToBackStack(null).commit();

                break;
        }
        return true;
    }


    private void showAlert() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Objects.requireNonNull(locationManager).isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    !=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST);
            }

        }
        else
        {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Enable Location")
                    .setMessage("Su ubicación esta desactivada.por favor active su ubicación " +
                            "usa esta app")
                    .setPositiveButton("Configuración de ubicación", (paramDialogInterface, paramInt) -> {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    })
                    .setNegativeButton("Cancelar", (paramDialogInterface, paramInt) -> {
                    });
            dialog.show();
        }
    }

    private void bdperfile()
    {
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        Conexion con=new Conexion(this);
        Conexionpst post=new Conexionpst();
        try
        {
            PreparedStatement pst=post.conexionbd().prepareStatement("INSERT INTO usuarios (correo, nombre) VALUES ('"
                    +account.getEmail()+"', '"+
                    account.getDisplayName()+"')");
            pst.executeQuery();
            Toast.makeText(this, "CONSULTA AGREGADA", Toast.LENGTH_LONG).show();
            SQLiteDatabase bs=con.getWritableDatabase();
            bs.execSQL("INSERT INTO usuario (correo) VALUES '"+account.getEmail()+"';");
            con.close();
            pst.close();
        }
        catch (Exception e)
        {
            /* Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();*/
            System.out.println(e.getLocalizedMessage());
        }
    }


    private void toolbar()
    {
        Toolbar barra=findViewById(R.id.TituloCategoria);

        setSupportActionBar(barra);
        //getSupportActionBar().setTitle(categoria.toUpperCase());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void agregar_historial(String id_ped, String fecha)
    {
        Conexion con=new Conexion(this);
        SQLiteDatabase bd=con.getWritableDatabase();
        EnviarDatos en=new EnviarDatos();

        bd.execSQL("INSERT INTO historial VALUES ('"+String.valueOf(en.idpedidos())+"', '"+fecha+"')");
        bd.close();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_p, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.carritoshop:
                Intent intent = new Intent(this, Bolsa.class);// bolsa
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hilo_producto()
    {

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
