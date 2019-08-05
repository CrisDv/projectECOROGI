package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

        TextView nombre=findViewById(R.id.Nombre);
        TextView precio=findViewById(R.id.precio);
        TextView preciototal=findViewById(R.id.preciototalproducto);
        EditText cantidad_producto=findViewById(R.id.num_cantidad);
        TextView tipo_producto=findViewById(R.id.product_type);
        ImageView imagepr=findViewById(R.id.imageProduct);
        TextView idpr=findViewById(R.id.id_product);

        String nom, pric, tipo_pr, id;
        Bitmap imgpr;
        id=getIntent().getStringExtra("id_producto");
        nom=getIntent().getStringExtra("nombre");
        pric=getIntent().getStringExtra("precio");
        tipo_pr=getIntent().getStringExtra("Tipo_del_producto");
        imgpr=getIntent().getParcelableExtra("BitImage");

        idpr.setText(id);
        nombre.setText(nom);
        precio.setText(pric);
        tipo_producto.setText(tipo_pr);
        imagepr.setImageBitmap(imgpr);


        Conexion cna=new Conexion(getApplicationContext());
        Button agregar=findViewById(R.id.agregarBolsa);

        cantidad_producto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                int sumatotal, cantidad, preciou;
                cantidad=Integer.parseInt(cantidad_producto.getText().toString());
                preciou=Integer.parseInt(precio.getText().toString());
                sumatotal=cantidad*preciou;
                preciototal.setText(String.valueOf(sumatotal));
            }
        });


        agregar.setOnClickListener(view ->
        {
            if (cantidad_producto.getText().toString()==null||preciototal.getText().toString()==null)
            {
                Toast.makeText(this, "No agregaste una cantidad", Toast.LENGTH_LONG).show();
            }
            else
            {
                cna.AgregarABolsa(Integer.parseInt(idpr.getText().toString()),nombre.getText().toString(),Integer.parseInt(cantidad_producto.getText().toString()), Integer.parseInt(preciototal.getText().toString()), imgpr);
                Toast.makeText(this, "Producto Agregado", Toast.LENGTH_LONG).show();
                Intent intent=new Intent(this, NavegacionL.class);
                startActivity(intent);
            }
        });
    }
}
