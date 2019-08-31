package com.fraint.eco;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Bolsa extends AppCompatActivity implements Recycler_BolsaAdapter.OnitembagListener {

    private RecyclerView recycleritemb;
    private Recycler_BolsaAdapter mRecyclerBolsaAdapter;
    private List<itemcarro> itembolsa = new ArrayList<>();
    private final String COMMA_SEPERATED = "###,###.###";
    private DecimalFormat decimalFormat = new DecimalFormat(COMMA_SEPERATED);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_bolsa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
     /*   Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        recycleritemb = findViewById(R.id.contenido_bolsa);
        recycleritemb.setLayoutManager(new LinearLayoutManager(this));

        Conexion cn = new Conexion(getApplicationContext());
        mRecyclerBolsaAdapter = new Recycler_BolsaAdapter(cn.ContenidoBolsa(), this);
        recycleritemb.setAdapter(mRecyclerBolsaAdapter);
        toolbar();


        TextView total=findViewById(R.id.totalbolsa);

        total.setText(decimalFormat.format(cn.sumdatos()));

        comprobarbolsa();
    }

    private void toolbar()
    {
        Toolbar barra=findViewById(R.id.TituloCategoria);
        TextView titulo=findViewById(R.id.titulobarracategorias);
        titulo.setText("BOLSITA DE COMPRAS");
        titulo.setTextSize(20);
        titulo.setGravity(-2);
        setSupportActionBar(barra);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void comprobarbolsa()
    {
        TextView total=findViewById(R.id.totalbolsa);
        Button Efectivo=findViewById(R.id.Pedido_Efectivo);
        ImageView empty=findViewById(R.id.emptybag);

        Conexion cn=new Conexion(this);
        if (cn.sumdatos()==0)
        {
            recycleritemb.setBackgroundResource(R.drawable.contorno2);
            empty.setImageResource(R.drawable.vbacia);
            Efectivo.setBackgroundResource(R.drawable.boton_no_disponible);
            Efectivo.setTextColor(Color.BLACK);
            Efectivo.setEnabled(false);
            total.setText(decimalFormat.format(cn.sumdatos()));
        }
        else if (cn.sumdatos()<=20000)
        {
            Efectivo.setEnabled(false);
            Efectivo.setBackgroundResource(R.drawable.boton_no_disponible);
            Efectivo.setTextColor(Color.BLACK);
            Toast.makeText(this, "Debes tener minimo 20.000 pesos en la bolsita", Toast.LENGTH_LONG).show();
            total.setText(decimalFormat.format(cn.sumdatos()));
        }
        else if (cn.sumdatos()>=20001)
        {
            Efectivo.setEnabled(true);
            Efectivo.setOnClickListener(view ->
            {
                Efectivo.setEnabled(false);
                Intent intent=new Intent(this, pago_Efectivo.class);
                startActivity(intent);
            });
            total.setText(decimalFormat.format(cn.sumdatos()));
        }
        cn.close();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onibagClick(int position) {

    }

}
