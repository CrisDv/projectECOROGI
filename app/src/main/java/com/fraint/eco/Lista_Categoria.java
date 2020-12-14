package com.fraint.eco;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fraint.eco.Adapters.Recycler_productAdapter;
import com.fraint.eco.Adapters.item_producto;
import com.fraint.eco.Connections_.Conexionpst;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Lista_Categoria extends AppCompatActivity implements Recycler_productAdapter.OnProductListener {

    private RecyclerView recyclerproducto;
    private Recycler_productAdapter recycler_productAdapter;
    private List<item_producto>product=new ArrayList<>();
    LinearLayout loadlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista__categoria);
        recyclerproducto=findViewById(R.id.recyclerlistproducto);
        recyclerproducto.setLayoutManager(new LinearLayoutManager(this));

        toolbar();

        recycler_productAdapter =new Recycler_productAdapter(null, Lista_Categoria.this);
        recyclerproducto.setAdapter(recycler_productAdapter);

        CargarImagen cargarImagen=new CargarImagen();
        cargarImagen.execute();

        recycler_productAdapter.showShimmer=true;

        loadlayout=findViewById(R.id.loadcontentproduct);
        loadlayout.setVisibility(View.GONE);

        recyclerproducto.setHasFixedSize(true);
        recyclerproducto.setItemViewCacheSize(20);
        recyclerproducto.setDrawingCacheEnabled(true);
        recyclerproducto.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
    }

  @Override public void onProductClick(int position) {

         final item_producto pdpropieties=product.get(position);
        Intent intent=new Intent(this, InterfazProducto.class);
        intent.putExtra("id_producto", pdpropieties.getId_product());
        intent.putExtra("nombre", pdpropieties.getNombre());
        intent.putExtra("precio", pdpropieties.getPrecio());
        intent.putExtra("Tipo_del_producto", pdpropieties.getTipo());
        intent.putExtra("BitImage", pdpropieties.getImgproduct());
        startActivity(intent);
    }

    public Uri foto(int id)
    {
        String sql="SELECT urlimagen FROM productos WHERE product_id="+id+";";
        Conexionpst post=new Conexionpst();
        Uri ImagenURL=null;
        try
        {
            Statement st =post.conexionbd().createStatement();
            ResultSet rs=st.executeQuery(sql);

            while (rs.next())
            {

                ImagenURL=Uri.parse(rs.getString(1));
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return ImagenURL;
    }

    private class CargarImagen extends AsyncTask<Void, Void, List<item_producto>>
    {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<item_producto> doInBackground(Void... voids) {
            String categoria=getIntent().getStringExtra("valor");
            String sqla="SELECT product_id, nombre, preciou, tipo_unidadm, disponible FROM productos WHERE tipo_categoria='"+categoria+"' ORDER BY nombre;";
            String sqle="SELECT * FROM productos";


            Conexionpst post=new Conexionpst();
            try
            {
                Statement st =post.conexionbd().createStatement();
                ResultSet rs=st.executeQuery(sqla);


                for(int i=0;i<=49;i++)
                {
                    while(rs.next())
                    {

                        product.add(new item_producto(rs.getString(1),rs.getString(2), Float.parseFloat(rs.getString(3)), rs.getString(4), foto(Integer.parseInt(rs.getString(1))), rs.getBoolean(5)));
                    }
                }
                st.close();
            }
            catch (Exception e)
            {
                Toast.makeText(Lista_Categoria.this, "Sin Productos por el momento", Toast.LENGTH_LONG).show();
                System.out.println(e);
            }
            finally {
                System.out.println("MUESTRAN LOS DATOS");
            }
            return product;
        }

        @Override
        protected void onPostExecute(List<item_producto> itempr) {
            super.onPostExecute(itempr);
            loadlayout.setVisibility(View.GONE);
            recycler_productAdapter =new Recycler_productAdapter(itempr, Lista_Categoria.this);
            recyclerproducto.setAdapter(recycler_productAdapter);
            recycler_productAdapter.showShimmer=false;
        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
