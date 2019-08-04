package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lista_Categoria extends AppCompatActivity implements Recycler_productAdapter.OnProductListener {

    private RecyclerView recyclerproducto;
    private Recycler_productAdapter recycler_productAdapter;
    private List<producto_pr>product=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__categoria);

        recyclerproducto=findViewById(R.id.recyclerlistproducto);
        recyclerproducto.setLayoutManager(new LinearLayoutManager(this));

        recycler_productAdapter =new Recycler_productAdapter(mostrarproductos(), this);
        recyclerproducto.setAdapter(recycler_productAdapter);

    }

    public List<producto_pr> mostrarproductos()
    {
        String a="";
        String sqla="SELECT * FROM productos';";
        NavegacionL con=new NavegacionL();

        try
        {
            Statement st =con.conexionbd().createStatement();
            ResultSet rs=st.executeQuery(sqla);

            for(int i=0;i<=100;i++)
            {
                while(rs.next())
                {
                    //List<producto_pr>product=new ArrayList<>();
                    product.add(new producto_pr(rs.getString(2),rs.getString(3), rs.getString(6),R.drawable.ecologo));

                }
            }
            st.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        finally {
            System.out.println("MUESTRAN LOS DATOS");
        }


       /* List<producto_pr> product=new ArrayList<>();
        product.add(new producto_pr("producto 1", "1.000", R.drawable.ecologo));

        product.add(new producto_pr("producto 3", "3.000", R.drawable.ecologo));*/
        return product;
    }

    @Override
    public void onProductClick(int position) {

        final producto_pr pdpropieties=product.get(position);
        Intent intent=new Intent(this, InterfazProducto.class);
        intent.putExtra("nombre", pdpropieties.getNombre());
        intent.putExtra("precio", pdpropieties.getPrecio());
        intent.putExtra("Tipo_del_producto", pdpropieties.getTipo());
        startActivity(intent);
    }
}
