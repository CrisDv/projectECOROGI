package com.fraint.eco;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    static final int SignCode = 5;
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private com.google.firebase.auth.FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "Login";
    Button GSign;
    LoginButton FBSign;
    ImageView load, GNuttonIMG;
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Opciones para autenticar
                                    .requestIdToken(getString(R.string.default_web_client_id))
                                    .requestEmail()
                                    .build();

        Googleapiclient = new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                            .addApi(Auth.GOOGLE_SIGN_IN_API, GSO).build();

        GSign=findViewById(R.id.GSingIng);
        GSign.setOnClickListener(v -> {
            Intent intent =Auth.GoogleSignInApi.getSignInIntent(Googleapiclient);//Selector de cuentas de google
            Googleapiclient.connect();
            startActivityForResult(intent,SignCode);
        });

        load=findViewById(R.id.ImagenCargaLogin);
        load.setVisibility(View.INVISIBLE);
        GNuttonIMG=findViewById(R.id.Gbutton);
        GNuttonIMG.setVisibility(View.VISIBLE);




        /*FBSign=findViewById(R.id.login_buttonFB);
        FBSign.setOnClickListener(view ->
        {
            InicioFB();
        });*/
    }

/*
    private void InicioFB()
    {

        FBSign.setPermissions("email", "public_profile");

        callbackManager= CallbackManager.Factory.create();

        FBSign.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                System.out.println("ONSUCCES");
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError "+exception);
            }
        });

        FirebaseAuth= com.google.firebase.auth.FirebaseAuth.getInstance();
        AuthListener= firebaseAuth -> {
            FirebaseUser user= FirebaseAuth.getCurrentUser();
            if (user!=null)
            {
                Intent i=new Intent(Login.this, P_InterfazUsuario.class);
                startActivity(i);
                //i.putExtra("FBid", Profile.getCurrentProfile().getId());

            }
        };

    }

    private void handleFacebookAccessToken(AccessToken token) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        FirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        System.out.println("Inicio Exitoso");
                        Intent i=new Intent(Login.this, P_InterfazUsuario.class);
                        startActivity(i);
                        cargaInterfazPrincipal cargaInterfazPrincipal=new cargaInterfazPrincipal();
                        cargaInterfazPrincipal.execute();
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        Toast.makeText(Login.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        System.out.println("INICIO FALLIDO");
                    }

                });
    }
    */
    private void HandleSignInGoogleFireb(GoogleSignInResult resultado)
    {
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

        if (resultado.isSuccess())
        {
            AuthCredential AuthCredentialm = GoogleAuthProvider.getCredential((resultado.getSignInAccount()).getIdToken(), null);
            FirebaseAuth.signInWithCredential(AuthCredentialm).addOnCompleteListener(this, task -> {
                if (task.isSuccessful())
                {

                    //Toast.makeText(Login.this, "Autenticando Con Google", Toast.LENGTH_SHORT).show();
                    cargaInterfazPrincipal cargaInterfazPrincipal=new cargaInterfazPrincipal();
                    cargaInterfazPrincipal.execute();

                }
                else
                {
                    Toast.makeText(Login.this, "Autenticacion No Exitosa", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else
        {
            Toast.makeText(Login.this, "Revisa tu conexion", Toast.LENGTH_SHORT).show();
        }

        System.out.println(resultado.getStatus());


    }

    private class cargaInterfazPrincipal extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            GSign.setVisibility(View.INVISIBLE);
            GSign.setEnabled(false);
            GNuttonIMG.setVisibility(View.INVISIBLE);
            load.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(Login.this,P_InterfazUsuario.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        AuthListener = firebaseAuth -> FirebaseAuth.addAuthStateListener(AuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.removeAuthStateListener(AuthListener);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        //envia el SignCode como resultado
        if (requestCode==SignCode)
        {
            GoogleSignInResult resultado =  Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            HandleSignInGoogleFireb(resultado);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
