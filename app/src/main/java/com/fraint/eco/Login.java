package com.fraint.eco;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


import java.net.Authenticator;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    static final int SignCode = 123;
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button FBsesion =(Button) findViewById(R.id.FBottom);
        Button GSingIn =(Button) findViewById(R.id.GSing);

        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Opciones para autenticar
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()//Opcion del correo
                .build();
        Googleapiclient =new GoogleApiClient.Builder(this) .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, GSO).build();//Intermedio entre las apis de google y la autenticacion

        FBsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(v.getContext(), NavegacionL.class);// clase cuando inicie sesion
                //startActivityForResult(intent, 0);

            }
        });

        GSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =Auth.GoogleSignInApi.getSignInIntent(Googleapiclient);//Selector de cuentas de google
                startActivityForResult(intent,SignCode);
            }
        });

        initialize();
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

    private void HandleSignInGoogleFireb(GoogleSignInResult resultado)
    {
        if (resultado.isSuccess())
        {
            AuthCredential AuthCredentialm = GoogleAuthProvider.getCredential(resultado.getSignInAccount().getIdToken(), null);
            FirebaseAuth.signInWithCredential(AuthCredentialm).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(Login.this, "Autenticando Con Google", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, NavegacionL.class);
                        startActivity(intent);
                        // finish();
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Autenticacion No Exitosa", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else
        {
            Toast.makeText(Login.this, "Sign In Unsuccess", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.addAuthStateListener(AuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.removeAuthStateListener(AuthListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //Metodo si Algo salio mal

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //envia el SignCode como resultado
        if (requestCode==SignCode)
        {
            GoogleSignInResult resultado =  Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            HandleSignInGoogleFireb(resultado);
        }
    }


    //Hasta aqui todo bien :'v

   /*private void handleSingnInResult(GoogleSignInResult resultado) {

        if (resultado.isSuccess())
        {
            goMainScreen();//Lo que hara si el inicio de sesion se logró
        }
        else
        {
            Toast.makeText(this,"No se ha podido iniciar sesion", Toast.LENGTH_LONG).show();
        }
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, NavegacionL.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }*/

}