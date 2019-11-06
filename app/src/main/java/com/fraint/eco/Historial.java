package com.fraint.eco;

import android.os.Bundle;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.fraint.eco.Adapters.Expandible_historialAdapter;
import com.fraint.eco.Adapters.item_historial;
import com.fraint.eco.Connections_.Conexionpst;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Historial extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String > listDataHeader=new ArrayList<>();

    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.recyclerHistorial);

        // preparing list data
        prepareListData();

        listAdapter = new Expandible_historialAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener((parent, v, groupPosition, id) -> {
             Toast.makeText(getApplicationContext(),
             "Group Clicked " + listDataHeader.get(groupPosition),
             Toast.LENGTH_SHORT).show();
            return false;
        });

        // Listview Group expanded listener
        /*expListView.setOnGroupExpandListener(groupPosition -> Toast.makeText(getApplicationContext(),
                listDataHeader.get(groupPosition) + " Expanded",
                Toast.LENGTH_SHORT).show());*/


        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(groupPosition -> Toast.makeText(getApplicationContext(),
                listDataHeader.get(groupPosition) + " Collapsed",
                Toast.LENGTH_SHORT).show());

        // Listview on child click listener
        expListView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {

            Toast.makeText(getApplicationContext(), listDataHeader.get(groupPosition) + " : " + listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition), Toast.LENGTH_SHORT).show();
            return false;
        });

        toolbar();
        head();
    }

    private void prepareListData() {




       /* listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data

        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
*/
        
    }

    private void head()
    {

        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        Conexionpst con=new Conexionpst();
        // String consulta_historial="SELECT id, fecha FROM pedido WHERE Client_correo='"+account.getEmail()+"';";
        String consulta_historial="SELECT id, fechadelpedido FROM pedido";

        String header = null;

        try {
            Statement statement=con.conexionbd().createStatement();
            ResultSet rs=statement.executeQuery(consulta_historial);

            while (rs.next())
            {
               // historial.add(new item_historial(rs.getString(1), rs.getString(2)));
                listDataHeader.add(rs.getString(1));
                Toast.makeText(this, rs.getString(1), Toast.LENGTH_LONG).show();
                header=rs.getString(1);
            }
            statement.close();

        }catch (Exception ex)
        {
            System.out.println("ERROR DATOS HISTORIAL"+ex);
        }


       // return header;
    }

  /*private List<item_historial> historial = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recycler_historialAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        recyclerView = findViewById(R.id.recyclerHistorial);

        mHistoryAdapter = new Recycler_historialAdapter(historial);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mHistoryAdapter);

        toolbar();

        DataHistorialPedidos();
    }

    private  void DataHistorialPedidos()
    {
        Toast.makeText(this, "aasd", Toast.LENGTH_LONG).show();
        GoogleSignInAccount account= GoogleSignIn.getLastSignedInAccount(this);
        Conexionpst con=new Conexionpst();
        // String consulta_historial="SELECT id, fecha FROM pedido WHERE Client_correo='"+account.getEmail()+"';";
        String consulta_historial="SELECT id, fechadelpedido FROM pedido WHERE Client_correo='alejoriano33@gmail.com'";

        try {
            Statement statement=con.conexionbd().createStatement();
            ResultSet rs=statement.executeQuery(consulta_historial);

            while (rs.next())
            {
                historial.add(new item_historial(rs.getString(1), rs.getString(2)));
                Toast.makeText(this, rs.getString(1), Toast.LENGTH_LONG).show();
            }
            statement.close();

        }catch (Exception ex)
        {
            System.out.println("ERROR DATOS HISTORIAL"+ex);
        }

    }*/

    private void toolbar()
    {
        Toolbar barra=findViewById(R.id.TituloCategoria);
        TextView titulo=findViewById(R.id.titulobarracategorias);
        titulo.setText("Mis Pedidos");
        titulo.setTextSize(20);
        titulo.setGravity(-2);
        setSupportActionBar(barra);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


}
