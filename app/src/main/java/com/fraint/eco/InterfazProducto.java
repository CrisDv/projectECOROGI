package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InterfazProducto extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_producto);

        TextView nombre=findViewById(R.id.Nombre);
        TextView precio=findViewById(R.id.precio);
        EditText cantidad_producto=findViewById(R.id.num_cantidad);
        TextView tipo_producto=findViewById(R.id.product_type);

        String nom, pric, tipo_pr;
        nom=getIntent().getStringExtra("nombre");
        pric=getIntent().getStringExtra("precio");
        tipo_pr=getIntent().getStringExtra("Tipo_del_producto");

        nombre.setText(nom);
        precio.setText(pric);
        tipo_producto.setText(tipo_pr);
    }


    @Override
    public void onClick(View view) {
        Button agregar=findViewById(R.id.agregarBolsa);

        

    }
}
