package com.fraint.eco;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.Connection;
import java.sql.DriverManager;

public class splash extends Activity{

    // Duración en milisegundos que se mostrará el splash
    private final int DURACION_SPLASH = 1000; // 1.5 segundos
    @Override
    public void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTheme(R.style.AppTheme_NoActionBar);

        super.onCreate(savedInstanceState);

        // Tenemos una plantilla llamada splash.xml donde mostraremos la información que queramos (logotipo, etc.)
        setContentView(R.layout.splash);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(splash.this, NavegacionL.class);
            startActivity(intent);
            finish();
        }, DURACION_SPLASH);
    }
}
