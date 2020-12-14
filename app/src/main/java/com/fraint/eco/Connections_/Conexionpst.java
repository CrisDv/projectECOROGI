package com.fraint.eco.Connections_;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.StrictMode;

import androidx.fragment.app.FragmentManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

public class Conexionpst {

    Context context;
    public Connection conexionbd()
    {
        Connection connection =null;
        try
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver").newInstance();
            connection= DriverManager.getConnection("URI", "USER", "PASS");

            //Toast.makeText(this, "Conexion Exitosa", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            // Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        return connection;
    }
}
