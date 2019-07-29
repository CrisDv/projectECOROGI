package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfazProducto extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_producto);
        mostrar();
    }

    static double unidad=0.0;

    private void datos()
    {
        String sql1="SELECT nombre ID";
        String sql2="";
        String sql3="";
        NavegacionL mainbd=new NavegacionL();
        try {
            PreparedStatement st1=mainbd.conexionbd().prepareStatement(sql1);
            PreparedStatement st2=mainbd.conexionbd().prepareStatement(sql2);
            PreparedStatement st3=mainbd.conexionbd().prepareStatement(sql3);



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void mostrar ()
    {
        EditText cantidad=findViewById(R.id.number_item);
        TextView restar = findViewById(R.id.restar_cantidad);
        TextView agregar=findViewById(R.id.add_cantidad);
        //unidad=Integer.parseInt(cantidad.getText());

        //cantidad.setText(0);
        agregar.setOnClickListener(view -> {
            unidad=unidad+0.5;

            cantidad.setText(unidad+"");
            if(unidad>=201)
            {
                Toast.makeText(this, "No puedes agregar mas de 200 Libras", Toast.LENGTH_LONG).show();
                //cantidad.setText(200);
            }

        });

        restar.setOnClickListener(view -> {
            unidad=unidad-0.5;
            cantidad.setText(unidad+"");
            if (unidad<=-0.5)
            {
                cantidad.setText(0);
                //unidad=0;
            }
        });
    }
}
