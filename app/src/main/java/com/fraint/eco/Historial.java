package com.fraint.eco;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Historial extends AppCompatActivity {

    private List<item_historial> historial = new ArrayList<>();
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

    }

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
