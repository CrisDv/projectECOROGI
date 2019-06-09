package com.fraint.eco;

import android.annotation.SuppressLint;
import android.net.wifi.p2p.WifiP2pManager;
import android.view.Menu;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;
import static com.google.common.collect.ComparisonChain.start;

public class Conexion {

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void psql()
    {

        Thread sqlThread = new Thread() {
            public void run() {
                try {
                    Class.forName("org.postgresql.Driver");
                    // "jdbc:postgresql://IP:PUERTO/DB", "USER", "PASSWORD");
                    // Si estás utilizando el emulador de android y tenes el PostgreSQL en tu misma PC no utilizar 127.0.0.1 o localhost como IP, utilizar 10.0.2.2
                    Connection conn = DriverManager.getConnection(
                            "jdbc:postgresql://3.13.87.79:5432/client", "mastercr", "ECOMARKETAPPTEST");

                    try
                    {
                        String sql="SELECT * FROM prueba0";
                        Statement st=conn.createStatement();
                        ResultSet rs =st.executeQuery(sql);
                        while(rs.next())
                        {
                            String id = rs.getString(1);
                        }
                    }
                } catch (SQLException se) {
                    System.out.println("oops! No se puede conectar. Error: " + se.toString());
                } catch (ClassNotFoundException e) {
                    System.out.println("oops! No se encuentra la clase. Error: " + e.getMessage());
                }
            }
        };
    }
}
