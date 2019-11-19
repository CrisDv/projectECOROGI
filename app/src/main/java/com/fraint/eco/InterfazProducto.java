package com.fraint.eco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.fraint.eco.Connections_.Conexion;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class InterfazProducto extends AppCompatActivity {

   int sumatotal;
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
        TextView produnidad=findViewById(R.id.producto_unidad);
        TextView temp=findViewById(R.id.temp_price);
        preciototal.setText("0");

        cantidad_producto.setInputType(InputType.TYPE_CLASS_NUMBER);
        String nom, tipo_pr, id, unidad;
        Uri imgpr;
        float pric;
        id=getIntent().getStringExtra("id_producto");
        nom=getIntent().getStringExtra("nombre");
        pric=getIntent().getFloatExtra("precio", 0);
        tipo_pr=getIntent().getStringExtra("Tipo_del_producto");
        imgpr=getIntent().getParcelableExtra("BitImage");
        unidad=getIntent().getStringExtra("Tipo_del_producto");

        idpr.setText(id);
        nombre.setText(nom);
        precio.setText(String.valueOf(pric));
        tipo_producto.setText(tipo_pr);
        produnidad.setText(unidad);
        //imagepr.setImageBitmap(imgpr);
        Glide.with(this).load(imgpr).into(imagepr);

        if (unidad.equals("und"))
        {
            int preciodeunidad=(int) pric;
            precio.setText(String.valueOf(preciodeunidad));

        }

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

                try
                {
                    final String separador = "###,###.##";
                    DecimalFormat decimalFormat = new DecimalFormat(separador);
                    NumberFormat nf=NumberFormat.getInstance();

                    float cantidad;
                    float preciou;
                    int sumatotal=0;
                    cantidad=Float.parseFloat(cantidad_producto.getText().toString());
                    //preciou=Integer.parseInt(precio.getText().toString());
                    preciou=  nf.parse(precio.getText().toString()).floatValue();
                    sumatotal= (int) (cantidad*preciou);

                   preciototal.setText(decimalFormat.format(sumatotal));
                   temp.setText(String.valueOf(sumatotal));
                }
                catch (Exception nx)
                {
                    System.out.println(nx+" number error");
                    preciototal.setText("");
                }

            }
        });


        agregar.setOnClickListener(view ->
        {
            if (cantidad_producto.getText().toString()==null  ||preciototal.getText().toString()=="0")
            {
                Toast.makeText(this, "No agregaste una cantidad", Toast.LENGTH_LONG).show();
            }
            else
            {
                try
                {
                    cna.AgregarABolsa(Integer.parseInt(idpr.getText().toString()),nombre.getText().toString(),Integer.parseInt(cantidad_producto.getText().toString()), temp.getText().toString(), imgpr, produnidad.getText().toString());
                    Toast.makeText(this, "Producto Agregado", Toast.LENGTH_LONG).show();
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Puede que ya hayas agregado este producto", Toast.LENGTH_LONG).show();
                    System.out.println(e+"INTERFAZPRODUCTO");
                }
               /* Intent intent=new Intent(this, NavegacionL.class);
                startActivity(intent);*/
               onBackPressed();
            }
        });

        toolbar();
    }

    private void toolbar()
    {
        Toolbar barra=findViewById(R.id.TituloCategoria);

        setSupportActionBar(barra);
        //getSupportActionBar().setTitle(categoria.toUpperCase());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_p, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.carritoshop:
                Intent intent = new Intent(this, Bolsa.class);// bolsa
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
