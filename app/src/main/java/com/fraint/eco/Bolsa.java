package com.fraint.eco;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Bolsa extends AppCompatActivity implements RecyclerBolsaAdapter.OnitembagListener {

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
        mRecyclerBolsaAdapter = new RecyclerBolsaAdapter(cn.ContenidoBolsa(), this);
        recycleritemb.setAdapter(mRecyclerBolsaAdapter);



        TextView total=findViewById(R.id.totalbolsa);
        total.setText(Integer.toString(cn.sumdatos()));

        TextView domicilio=findViewById(R.id.Pedido_Domicilio);
        domicilio.setOnClickListener(view ->
        {
            Toast.makeText(this,"Funcion no disponible", Toast.LENGTH_LONG).show();
        });

        TextView programar=findViewById(R.id.Programar_Pedido);
        programar.setOnClickListener(view ->
        {
            Toast.makeText(this,"Funcion  no disponible", Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public void onibagClick(int position) {

    }

}
