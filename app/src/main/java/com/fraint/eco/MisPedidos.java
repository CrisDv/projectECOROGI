package com.fraint.eco;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MisPedidos extends AppCompatActivity {

    private RecyclerView recycleritmhistoria;
    private Recycler_historialAdapter historialAdapter;
    private List<item_historial> item_historials=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_pedidos);

        recycleritmhistoria=findViewById(R.id.recyclerHistorial);
        recycleritmhistoria.setLayoutManager(new LinearLayoutManager(this));

        Conexion cn =new Conexion(this);
        historialAdapter=new Recycler_historialAdapter(cn.Historial());
        recycleritmhistoria.setAdapter(historialAdapter);
    }
}
