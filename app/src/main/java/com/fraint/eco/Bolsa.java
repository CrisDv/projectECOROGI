package com.fraint.eco;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Bolsa extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycleritemb;
    private RecyclerBolsaAdapter mRecyclerBolsaAdapter;
    private List<itemcarro> itembolsa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_bolsa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycleritemb = findViewById(R.id.contenido_bolsa);
        recycleritemb.setLayoutManager(new LinearLayoutManager(this));

        Conexion cn = new Conexion(getApplicationContext());
        mRecyclerBolsaAdapter = new RecyclerBolsaAdapter(cn.ContenidoBolsa());
        recycleritemb.setAdapter(mRecyclerBolsaAdapter);

        TextView total=findViewById(R.id.totalbolsa);
        total.setText(Integer.toString(cn.sumdatos()));

        TextView domicilio=findViewById(R.id.Pedido_Domicilio);
        domicilio.setOnClickListener(view ->
        {
            interfaz_pago pagarDomicilio=new interfaz_pago();
            Intent intent=new Intent(this, interfaz_pago.class);
            startActivity(intent);
        });
    }





    @Override
    public void onClick(View view) {


    }
}
