package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.DecimalFormat;
import android.os.Bundle;
import android.text.style.TtsSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.InputStream;
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
        toolbar();

    }

    public List<producto_pr> mostrarproductos()
    {
        String categoria=getIntent().getStringExtra("valor");
        String sqla="SELECT * FROM productos WHERE tipo_categoria='"+categoria+"';";
        String sqli="SELECT * FROM productos";

        Conexionpst post=new Conexionpst();
        try
        {
            Statement st =post.conexionbd().createStatement();
            ResultSet rs=st.executeQuery(sqla);

            for(int i=0;i<=100;i++)
            {
                while(rs.next())
                {
                    //List<producto_pr>product=new ArrayList<>();
                    product.add(new producto_pr(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(6),foto(Integer.parseInt(rs.getString(1)))));

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
        intent.putExtra("id_producto", pdpropieties.getId_product());
        intent.putExtra("nombre", pdpropieties.getNombre());
        intent.putExtra("precio", pdpropieties.getPrecio());
        intent.putExtra("Tipo_del_producto", pdpropieties.getTipo());
        intent.putExtra("BitImage", pdpropieties.getImgproduct());
        startActivity(intent);
    }

    public Bitmap foto(int id)
    {
        String sql="SELECT img FROM productos WHERE product_id="+id+";";

        InputStream im=null;

        NavegacionL con=new NavegacionL();

        Conexionpst post=new Conexionpst();
        try
        {
            Statement st =post.conexionbd().createStatement();
            ResultSet rs=st.executeQuery(sql);

            while (rs.next())
            {
                im=rs.getBinaryStream(1);
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        Bitmap bpm=BitmapFactory.decodeStream(im);
        return bpm;
    }

    private void toolbar()
    {
        String categoria=getIntent().getStringExtra("valor");
        Toolbar barra=findViewById(R.id.TituloCategoria);
        TextView titulo=findViewById(R.id.titulobarracategorias);
        titulo.setText(categoria.toUpperCase());
        setSupportActionBar(barra);
        //getSupportActionBar().setTitle(categoria.toUpperCase());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navegacion_l, menu);
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
