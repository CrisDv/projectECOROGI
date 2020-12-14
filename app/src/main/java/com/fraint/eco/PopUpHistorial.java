package com.fraint.eco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

import com.fraint.eco.Adapters.ItemPopUpAdapter;
import com.fraint.eco.Adapters.Item_PopUpDetalles;
import com.fraint.eco.Connections_.Conexionpst;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PopUpHistorial extends AppCompatActivity {

    private List<Item_PopUpDetalles> PopUpDetalles=new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemPopUpAdapter popUpAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_historial);

        recyclerView=findViewById(R.id.recyclerPopUpHistorial);
        popUpAdapter=new ItemPopUpAdapter(PopUpDetalles);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(popUpAdapter);

        String id=getIntent().getStringExtra("id");
        ConsultarDatos(id);

        DisplayMetrics displaym=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaym);
        int width=displaym.widthPixels;
        int height=displaym.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.8));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.CENTER;
        params.x=0;
        params.y=-20;
        getWindow().setAttributes(params);

    }

    private void ConsultarDatos(String id) {

        Conexionpst con=new Conexionpst();
        String ConsultarDatos="select p.nombre, pp.cantidad_producto from productos p, pedido_producto pp, pedido pe, usuarios u WHERE pe.id="+id+" AND pp.id_pedido=pe.id AND pe.Client_correo=u.correo AND p.product_id=pp.id_producto;";

        try
        {
            Statement statement=con.conexionbd().createStatement();
            ResultSet rs=statement.executeQuery(ConsultarDatos);
            while (rs.next())
            {
                PopUpDetalles.add(new Item_PopUpDetalles(rs.getString(1), rs.getString(2)));
            }
            statement.close();
        }
        catch (Exception e)
        {
            System.out.println("ERROR POPUPHISTORIAL"+e);
        }

    }
}
