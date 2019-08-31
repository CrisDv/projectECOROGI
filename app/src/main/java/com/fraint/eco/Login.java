package com.fraint.eco;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    static final int SignCode = 5;
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private com.google.firebase.auth.FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "Login";


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

        Button GSign=findViewById(R.id.GSingIng);
        GSign.setOnClickListener(v -> {
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
                    Intent intent = new Intent(Login.this,P_InterfazUsuario.class);
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
            Toast.makeText(Login.this, "Revisa tu conexion", Toast.LENGTH_SHORT).show();
        }

        System.out.println(resultado.getStatus());

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
