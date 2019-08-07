package com.fraint.eco;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

public class NavegacionL extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "NavegationL";
    private int MY_LOCATION_REQUEST;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_l);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //token para notificaciones
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(NavegacionL.this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken", newToken);

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Opciones para autenticar
                .requestEmail()//Opcion del correo
                .build();

        Googleapiclient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, GSO).build();

        //------------------------------------------------------------------------------------------
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //-------------------HEADER-----------------------------------------------------------------
        View headv = navigationView.getHeaderView(0);
        navigationView.setBackgroundColor(getResources().getColor(R.color.Unknow));

        //------------------------------------------------------------------------------------------
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------------------------------------------------------------------

       /* Button boton =(Button)findViewById(R.id.street);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ConfirmacionMAPS.class);// clase cuando inicie sesion
                startActivityForResult(intent, 0);
            }
        });*/
        //------------------------------------------------------------------------------------------
        ImageView wpp = findViewById(R.id.WPPcontact);
        wpp.setOnClickListener(view -> contactos());

        /*ImageView FB = findViewById(R.id.FBcontact);
        FB.setOnClickListener(view -> );*/

        TextView OUT = findViewById(R.id.OUT);
        OUT.setOnClickListener(view -> signOut());

        initialize();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();

        handleSignInResult();

    }


    private void initialize() {

        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        AuthListener = firebaseAuth -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getUid());
                Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getEmail());
            } else {
                Log.w(TAG, "onAuthStateChanged - signed_out");
            }
        };
    }


    public void agregar(GoogleSignInAccount account)
    {

        Conexionpst post=new Conexionpst();
        splash sp=new splash();
        try
        {
            PreparedStatement pst=post.conexionbd().prepareStatement("INSERT INTO usuarios VALUES ('"
                                                                        +account.getEmail()+"', '"+
                                                                         account.getDisplayName()+"', '', 00)");
            pst.executeQuery();
            Toast.makeText(this, "CONSULTA AGREGADA", Toast.LENGTH_LONG).show();
            pst.close();
        }
        catch (Exception e)
        {
           /* Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();*/
            System.out.println(e.getLocalizedMessage());
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegacion_l, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        Bolsa bl=new Bolsa();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        switch (item.getItemId()) {
            case R.id.carritoshop:

                    Intent intent = new Intent(this, Bolsa.class);// bolsa
                    startActivity(intent);

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager manager = getSupportFragmentManager();
        //int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.M_eco:
                manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();
                break;
            case R.id.request:
                manager.beginTransaction().replace(R.id.fragment_container, new FragPedido()).commit();
                break;
            case R.id.gramos:

                break;
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void contactos()
    {
        Uri uriWPP=Uri.parse("https://wa.me/573005222012");
        Intent intent =new Intent(Intent.ACTION_VIEW, uriWPP);

        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
        boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
        if (isIntentSafe)
        {
            startActivity(intent);
        }

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(Googleapiclient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(this::handleSignInResult);
        }
    }*/


    private void signOut(){
        FirebaseAuth.signOut();
        if (Auth.GoogleSignInApi != null){
            Auth.GoogleSignInApi.signOut(Googleapiclient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(@NonNull Status status) {
                    if (status.isSuccess()){
                        Intent i = new Intent(NavegacionL.this, Login.class);
                        startActivity(i);
                        finish();
                    }else {
                        Toast.makeText(NavegacionL.this, "Error in Google Sign Out", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (LoginManager.getInstance() != null){
            LoginManager.getInstance().logOut();
        }


    }


    private void handleSignInResult() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headviewer = navigationView.getHeaderView(0);
        TextView nombre = headviewer.findViewById(R.id.namae);
        ImageView perfil = headviewer.findViewById(R.id.PhotoPf);


       // if (result.isSuccess()) {

            GoogleSignInAccount account =GoogleSignIn.getLastSignedInAccount(this);

            nombre.setText(account.getDisplayName()+account.getEmail());
            //idTextView.setText(account.getId());
            Glide.with(this).load(account.getPhotoUrl()).into(perfil);
            showAlert();

       /* } else {
            Intent ListSong = new Intent(getApplicationContext(), Login.class);
            startActivity(ListSong);

        }*/
      //  GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        agregar(account);

    }

    private void showAlert() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Objects.requireNonNull(locationManager).isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    !=PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_REQUEST);
            }
            else
            {
                // Toast.makeText(this, "Ubicacion Activada", Toast.LENGTH_LONG).show();
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();
    }
}
