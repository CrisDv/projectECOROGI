package com.fraint.eco;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexionpst {

    public Connection conexionbd()
    {
        Connection connection =null;
        try
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("org.postgresql.Driver").newInstance();
            connection= DriverManager.getConnection("jdbc:postgresql://3.13.99.76:5432/MOVIL","mastercr","ECOMARKETAPPTEST");

            //Toast.makeText(this, "Conexion Exitosa", Toast.LENGTH_LONG).show();
        }
        catch (Exception e)
        {
            // Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
        return connection;
    }
}
