package com.fraint.eco;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Objects;


public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    static final int SignCode = 2;
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "Login";
    private CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        LoginButton FBsesion = findViewById(R.id.FBottom);
        Button GSingIn = findViewById(R.id.GSing);

        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        AppEventsLogger.activateApp(this);
        Log.w(TAG, "Callback  " + callbackManager.toString());

        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Opciones para autenticar
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        Googleapiclient =new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, GSO)
                .build();//Intermedio entre las apis de google y la autenticacion



        FBsesion.setReadPermissions("email");
        FBsesion.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.w(TAG, "Facebook Login Success Token:  " + loginResult.getAccessToken().getToken());
                signInFacebookFirebase(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.w(TAG, "Facebook Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.w(TAG, "Facebook Error");
                error.printStackTrace();
            }
        });

        GSingIn.setOnClickListener(v -> {
            Intent intent =Auth.GoogleSignInApi.getSignInIntent(Googleapiclient);//Selector de cuentas de google
            Googleapiclient.connect();
            startActivityForResult(intent,SignCode);
        });

        initialize();
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

   private void HandleSignInGoogleFireb(GoogleSignInResult resultado)
    {
        if (resultado.isSuccess())
        {
            AuthCredential AuthCredentialm = GoogleAuthProvider.getCredential((resultado.getSignInAccount()).getIdToken(), null);
            FirebaseAuth.signInWithCredential(AuthCredentialm).addOnCompleteListener(this, task -> {
                if (task.isSuccessful())
                {

                    //Toast.makeText(Login.this, "Autenticando Con Google", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this,NavegacionL.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Login.this, "Autenticacion No Exitosa", Toast.LENGTH_SHORT).show();

                }
            });
        }
        else
        {
            Toast.makeText(Login.this, "Autenticacion no exitosa 2", Toast.LENGTH_SHORT).show();
        }

        System.out.println(resultado.getStatus());

    }

    private void signInFacebookFirebase(AccessToken accessToken){
        AuthCredential authCredential = FacebookAuthProvider.getCredential(accessToken.getToken());

        FirebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()){
                Toast.makeText(Login.this, "Facebook Authentication Success", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Login.this, NavegacionL.class);
                startActivity(i);
                // finish();
            }else{
                Toast.makeText(Login.this, "Facebook Authentication Unsuccess", Toast.LENGTH_SHORT).show();
            }
        });
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

}