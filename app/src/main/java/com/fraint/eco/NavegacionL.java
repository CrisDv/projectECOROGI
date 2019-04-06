package com.fraint.eco;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class NavegacionL extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "NavegationL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navegacion_l);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //token para notificaciones
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( NavegacionL.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.e("newToken",newToken);

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

        Googleapiclient =new GoogleApiClient.Builder(this) .enableAutoManage(this, this)
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
        initialize();
        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();

    }


    private void initialize() {

        FirebaseAuth = FirebaseAuth.getInstance();
        AuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getUid());
                    Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getEmail());
                } else {
                    Log.w(TAG, "onAuthStateChanged - signed_out");
                }
            }
        };
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
       /* if (id == R.id.carritoshop) {
            //carrito de compras
            startActivity (new Intent (this, Carrito.class));
            return true;
        }

       */FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction =getFragmentManager().beginTransaction();
       switch (item.getItemId())
       {
           case R.id.carritoshop:
               Intent intent = new Intent(this, Bolsa.class);// clase cuando inicie sesion
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
        switch (item.getItemId())
        {
            case R.id.M_eco:
                manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();
                break;
            case R.id.request:
               manager.beginTransaction().replace(R.id.fragment_container, new FragPedido()).commit();
                break;
            case R.id.gramos:

                break;
            case R.id.nav_manage:

                break;
            case R.id.OUT:
                signOut();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(Googleapiclient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

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

        /*if (LoginManager.getInstance() != null){
            LoginManager.getInstance().logOut();
        }para facebook*/


    }
    private void handleSignInResult(GoogleSignInResult result) {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headviewer = navigationView.getHeaderView(0);
        TextView nombre = (TextView) headviewer.findViewById(R.id.namae);
        ImageView perfil = (ImageView) headviewer.findViewById(R.id.PhotoPf);

        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            nombre.setText(account.getDisplayName()+account.getEmail());
            //idTextView.setText(account.getId());
            Glide.with(this).load(account.getPhotoUrl()).into(perfil);

        } else {
            Intent ListSong = new Intent(getApplicationContext(), Login.class);
            startActivity(ListSong);
        }

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_container, new FragECO()).commit();
    }
}
