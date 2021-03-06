package com.fraint.eco.Connections_;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;

import com.fraint.eco.EnviarDatos;
import com.fraint.eco.Lista_Categoria;
import com.fraint.eco.P_InterfazUsuario;
import com.fraint.eco.Adapters.item_carro;
import com.fraint.eco.Adapters.item_historial;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Conexion extends SQLiteOpenHelper {

    private static final String Nombre_BD="name";
    private static final int Version_BD=1;

    private static final String TABLA_CARRO= " CREATE TABLE bolsacompra" +
            "(" +
            "   id_producto INT NOT NULL,"+
            "    nombrepr VARCHAR (20) PRIMARY KEY NOT NULL," +
            "    cantidad INT NOT NULL," +
            "    suma INT NOT NULL," +
            "    img VARCHAR(300)," +
            "    tipo_producto VARCHAR (5)"+
            ");";

    private static  final String TABLA_PRODUCTOS ="CREATE TABLE productos("+
            "    product_id int PRIMARY KEY NOT NULL," +
            "    nombre VARCHAR(45) NOT NULL," +
            "    preciou int NOT NULL," +
            "    descripcion varchar (50) NOT NULL," +
            "    pesokg int NOT NULL);";

    private static final String TABLA_USUARIO = "CREATE TABLE usuario(" +
            "    correo varchar (50) NOT NULL PRIMARY KEY," +
            "    nombre varchar (50) NOT NULL," +
            "    direccion varchar(50),"+
            "    gramos_acumulados int);";

    private static final String TABLA_DIRECCIONES="CREATE TABLE direccionUser"+
            "("+
            "direccion VARCHAR (50) NOT NULL,"+
            "iduser varchar(25),"+
            "PRIMARY KEY (direccion, iduser),"+
            "FOREIGN KEY (iduser) REFERENCES usuario(correo)"+
            ");";

    private static final String HISTORIAL_TABLE="CREATE TABLE historial" +
            "(" +
            "ID INTEGER"+
            ");";

    private Context context;

    public Conexion(Context context) {
        super(context, Nombre_BD, null, Version_BD);

        this.context=context;
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_PRODUCTOS);
        sqLiteDatabase.execSQL(TABLA_USUARIO);
        sqLiteDatabase.execSQL(TABLA_CARRO);
        sqLiteDatabase.execSQL(TABLA_DIRECCIONES);
        sqLiteDatabase.execSQL(HISTORIAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLA_CARRO);
        sqLiteDatabase.execSQL(TABLA_CARRO);
        onCreate(sqLiteDatabase);
    }

    public void AgregarABolsa(int id_produc, String nombre, int cantidad, String sumacomprada, Uri imc, String tipoUnidad)
    {
       /* ByteArrayOutputStream bos=new ByteArrayOutputStream();
        imc.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte [] barray=bos.toByteArray();*/
       String imgn=imc.toString();

        SQLiteDatabase bdagregar=getWritableDatabase();
        if (bdagregar!=null)
        {
            bdagregar.execSQL("INSERT INTO bolsacompra (id_producto, nombrepr, cantidad, suma, tipo_producto, img) VALUES ("+id_produc+",'"+nombre+"', "+cantidad+", "+sumacomprada+",'"+tipoUnidad+"','"+imgn+"');");

            bdagregar.close();
        }
    }

    public List<item_carro> ContenidoBolsa()
    {

        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr=bd.rawQuery("SELECT * FROM bolsacompra;", null);

        List<item_carro> bolsa=new ArrayList<>();

        Lista_Categoria lc=new Lista_Categoria();

        if (cr.moveToFirst())
        {
            do {
                bolsa.add(new item_carro(cr.getInt(0),cr.getString(1), cr.getInt(2), cr.getInt(3), lc.foto(cr.getInt(0)), cr.getString(5)));//para la imagen, recibir un int
            }while (cr.moveToNext());
        }

        bd.close();
        return bolsa;
    }


    public List<item_historial> Historial()
    {
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr=bd.rawQuery("SELECT * FROM historial;", null);
        List<item_historial> historials=new ArrayList<>();

        if (cr.moveToFirst())
        {
            do {
                historials.add(new item_historial(cr.getString(0), cr.getString(1)));
            }while (cr.moveToNext());
        }
        bd.close();

        return historials;
    }

    public void EnviarProductos(String correo)
    {

        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr=bd.rawQuery("SELECT id_producto, cantidad FROM bolsacompra", null);


        EnviarDatos ed=new EnviarDatos(context);

        while (cr.moveToNext())
        {
            ed.pedido_productos(correo, cr.getInt(0), cr.getInt(1));
        }
        bd.close();
    }

    public int sumdatos()
    {
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr = bd.rawQuery("SELECT sum(suma) FROM bolsacompra;", null);
        int total=0;

        if (cr.moveToFirst())
        {
            total=cr.getInt(0);
        }
        else
        {
            total=0;
        }
        bd.close();
        return total;
    }

    public void eliminarproducto(String nombre)
    {
        SQLiteDatabase bd=getWritableDatabase();
        bd.delete("bolsacompra", "nombrepr='"+nombre+"'", null);
        System.out.println(nombre);
        bd.close();
    }

    public void eliminarbolsa()
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL("DROP TABLE IF EXISTS bolsacompra");
        database.execSQL(TABLA_CARRO);
        database.close();
    }

    public BigInteger generarid()
    {
        BigInteger BigId;
        long id1=(long) (Math.random()*100000)+1;
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int minuti=fecha.get(Calendar.MINUTE);
        int segundo=fecha.get(Calendar.SECOND);

        int anio=fecha.get(Calendar.YEAR);


        String idpedido5= String.valueOf(id1) +String.valueOf(mes)+ String.valueOf(dia) + String.valueOf(minuti) + String.valueOf(segundo);
        P_InterfazUsuario p_interfazUsuario=new P_InterfazUsuario();
        String tiempo= anio +"/"+ mes +"/"+ dia;

        BigId=BigInteger.valueOf(Long.parseLong(idpedido5));

        /*Historial h=new Historial();
        h.DataHistorialPedidos(idpedido5, tiempo);*/

        SQLiteDatabase bd=getWritableDatabase();
        bd.execSQL("DROP TABLE IF EXISTS historial");
        bd.execSQL(HISTORIAL_TABLE);
        bd.execSQL("INSERT INTO historial(ID) VALUES('"+BigId+"')");

        bd.close();
        return BigId;
    }

    public long recogerid()
    {
        long bigid = 0;
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr=bd.rawQuery("SELECT ID from historial;", null);

        if (cr.moveToFirst())
        {
            bigid=cr.getLong(0);

        }
        cr.close();
        bd.close();
        return bigid;

    }
}
