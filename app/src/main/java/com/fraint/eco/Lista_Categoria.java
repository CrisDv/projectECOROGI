package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lista_Categoria extends AppCompatActivity {

    private RecyclerView recyclerproducto;
    private Recycler_product recycler_product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__categoria);

        recyclerproducto=findViewById(R.id.recyclerlistproducto);
        recyclerproducto.setLayoutManager(new LinearLayoutManager(this));

        recycler_product=new Recycler_product(mostrarproductos());
        recyclerproducto.setAdapter(recycler_product);

    }

    public List<producto_pr> mostrarproductos()
    {
       /* FragECO catego=new FragECO();
        String sql="SELECT * FROM productos WHERE categoria='"+catego.condicioncategoria()+"'";
        NavegacionL nl= (NavegacionL) new NavegacionL().conexionbd();*/

        List<producto_pr> product=new ArrayList<>();
        product.add(new producto_pr("producto 1", "1.000", R.drawable.ecologo));

        product.add(new producto_pr("producto 3", "3.000", R.drawable.ecologo));


        return product;
    }

}
