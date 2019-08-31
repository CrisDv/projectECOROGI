package com.fraint.eco;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class pago_Efectivo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago_efectivo);
        spineroptions();
        datos();
        toolbar();
        Valor();

        Spinner diaspinner=findViewById(R.id.Dia);
        String diatext=diaspinner.getSelectedItem().toString();
        Spinner horaSpinner=findViewById(R.id.Hora);
        String horaText=horaSpinner.getSelectedItem().toString();

        Conexion cn=new Conexion(this);
        Button CONFIRMAR=findViewById(R.id.POR_FIN_CONFIRMAR_ORDEN);
        CONFIRMAR.setOnClickListener(view ->
        {

                if (enviarBD()==true)
                {
                    CONFIRMAR.setEnabled(false);
                    Intent intent=new Intent(this, P_InterfazUsuario.class);
                    startActivity(intent);
                    Toast.makeText(this, "PEDIDO CONFIRMADO", Toast.LENGTH_LONG).show();
                    cn.eliminarbolsa();
                }
                else
                {
                    Toast.makeText(this, "Oops, Tal vez te falto algo por decir", Toast.LENGTH_LONG).show();
                }
        });
    }

    private void datos()
    {
        GoogleSignInAccount acc= GoogleSignIn.getLastSignedInAccount(this);
        TextView DireccionValidada=findViewById(R.id.DireccionEnvio);

        Conexion bdhelper=new Conexion(this);
        EnviarDatos data=new EnviarDatos();

        DireccionValidada.setText(data.direccion(acc.getEmail()));

        SQLiteDatabase bd=bdhelper.getReadableDatabase();
        Cursor cur1=bd.rawQuery("SELECT sum(cantidad) FROM bolsacompra WHERE tipo_producto='g'", null);
        Cursor cur2=bd.rawQuery("SELECT sum(cantidad) FROM bolsacompra WHERE tipo_producto='ml'", null);
        Cursor cur3=bd.rawQuery("SELECT sum(cantidad) FROM bolsacompra WHERE tipo_producto='und'", null);
        int g=0, ml=0, und=0;

        TextView gr=findViewById(R.id.Num_Cantidad_g);
        TextView mil=findViewById(R.id.Num_Cantidad_ml);
        TextView un=findViewById(R.id.Num_Unidades);
        if (cur1.moveToFirst())
        {
            g=cur1.getInt(0);
            gr.setText(String.valueOf(g));
        }

        if (cur2.moveToFirst())
        {
            ml=cur2.getInt(0);
            mil.setText(String.valueOf(ml));
        }

        if (cur3.moveToFirst())
        {
            und=cur3.getInt(0);
            un.setText(String.valueOf(und));
        }
        bdhelper.close();
    }

    private void spineroptions()
    {
        ArrayList<String> DiaEnvio=new ArrayList<>();
        DiaEnvio.add("Dia");
        DiaEnvio.add("Lunes");
        DiaEnvio.add("Martes");
        DiaEnvio.add("Miercoles");
        DiaEnvio.add("Jueves");
        DiaEnvio.add("Viernes");
        DiaEnvio.add("Sabado");
        DiaEnvio.add("Domingo");
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, DiaEnvio);

        Spinner mSpinnerDia;
        mSpinnerDia=findViewById(R.id.Dia);
        mSpinnerDia.setAdapter(adapter);

        ArrayList<String> HoraEnvio=new ArrayList<>();
        HoraEnvio.add("Hora");
        HoraEnvio.add("8:00-10:00");
        HoraEnvio.add("10:00-12:00");
        HoraEnvio.add("12:00-14:00");
        HoraEnvio.add("14:00-16:00");
        HoraEnvio.add("16:00-18:00");

        ArrayAdapter<String> hadapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, HoraEnvio);
        Spinner mSpinnerHora;
        mSpinnerHora=findViewById(R.id.Hora);
        mSpinnerHora.setAdapter(hadapter);


    }

    private void toolbar()
    {
        Toolbar barra=findViewById(R.id.TituloCategoria);
        TextView titulo=findViewById(R.id.titulobarracategorias);
        titulo.setText("PAGO EFECTIVO");
        titulo.setGravity(-2);
        setSupportActionBar(barra);
        //getSupportActionBar().setTitle(categoria.toUpperCase());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Valor()
    {
        final String COMMA_SEPERATED = "###,###.###";
        DecimalFormat decimalFormat = new DecimalFormat(COMMA_SEPERATED);
        Button orden=findViewById(R.id.POR_FIN_CONFIRMAR_ORDEN);
        TextView subtotal=findViewById(R.id.SubTotalCart);
        TextView totalcompra=findViewById(R.id.Pago_Total);
        TextView sub1=findViewById(R.id.subtotalinvisible);
        TextView total1=findViewById(R.id.totalinvisible);

        Conexion cn=new Conexion(this);
        if (cn.sumdatos()<=20000)
        {
            orden.setEnabled(false);
            Toast.makeText(this, "DEBES COMPRAR MINIMO $ 20.000 EN PRODUCTOS", Toast.LENGTH_LONG).show();
            onBackPressed();
        }
        else
        {
            subtotal.setText("$ "+decimalFormat.format(cn.sumdatos()));
            sub1.setText(String.valueOf(cn.sumdatos()));
            int domicilio=2500+cn.sumdatos();
            totalcompra.setText("$ "+decimalFormat.format(domicilio));
            total1.setText(String.valueOf(domicilio));

        }
    }

    private boolean enviarBD()
    {
        EnviarDatos datos=new EnviarDatos();
        GoogleSignInAccount acc= GoogleSignIn.getLastSignedInAccount(this);
        TextView total=findViewById(R.id.totalinvisible);
        EditText tel=findViewById(R.id.CELULAR);
        TextView direccion=findViewById(R.id.DireccionEnvio);

        Spinner diaspinner=findViewById(R.id.Dia);
        String diatext=diaspinner.getSelectedItem().toString();
        Spinner horaSpinner=findViewById(R.id.Hora);
        String horaText=horaSpinner.getSelectedItem().toString();
        Date currentdate= Calendar.getInstance().getTime();
        String fecha=horaText+"/"+diatext;
        String telefonoCel=String.valueOf(tel.getText());
        if (direccion.getText().toString().equals("")||diatext.equals("Dia")||horaText.equals("Hora")||telefonoCel.equals(""))
        {
            Toast.makeText(this, "Oops, revisa tus datos, tal vez algo falta", Toast.LENGTH_LONG).show();
            return false;

        }
        else
        {
            BigInteger bi = BigInteger.valueOf(Long.parseLong(tel.getText().toString()));

            datos.pedido(Integer.parseInt(total.getText().toString()),bi, acc.getEmail(), fecha);
            Conexion cn=new Conexion(this);
            cn.EnviarProductos(acc.getEmail());
            return true;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

}
