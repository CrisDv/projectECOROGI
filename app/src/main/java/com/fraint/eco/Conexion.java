package com.fraint.eco;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Conexion extends SQLiteOpenHelper {

    private static final String Nombre_BD="name";
    private static final int Version_BD=1;
    private static final String TABLA_CARRO= " CREATE TABLE bolsacompra" +
            "(" +
            "id_producto INT NOT NULL,"+
            "    nombrepr VARCHAR (20) PRIMARY KEY NOT NULL," +
            "    cantidad INT NOT NULL," +
            "    suma INT NOT NULL," +
            "    img blob" +
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

    public Conexion(Context context) {
        super(context, Nombre_BD, null, Version_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_PRODUCTOS);
        sqLiteDatabase.execSQL(TABLA_USUARIO);
        sqLiteDatabase.execSQL(TABLA_CARRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLA_CARRO);
       //sqLiteDatabase.execSQL(TABLA_CARRO);
    }

    public void AgregarABolsa(int id_produc, String nombre, int cantidad, int sumacomprada, Bitmap imc)
    {
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        imc.compress(Bitmap.CompressFormat.PNG, 0, bos);
        byte [] barray=bos.toByteArray();
        SQLiteDatabase bdagregar=getWritableDatabase();
        if (bdagregar!=null)
        {
            bdagregar.execSQL("INSERT INTO bolsacompra (id_producto, nombrepr, cantidad, suma) VALUES ("+id_produc+",'"+nombre+"', "+cantidad+", "+sumacomprada+");");

            bdagregar.close();
        }
    }

    public List<itemcarro> ContenidoBolsa()
    {

        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr=bd.rawQuery("SELECT * FROM bolsacompra;", null);

        List<itemcarro> bolsa=new ArrayList<>();


            if (cr.moveToFirst())
            {
                do {
                    bolsa.add(new itemcarro(cr.getInt(0),cr.getString(1), cr.getInt(2), cr.getInt(3), R.drawable.ecologo));//para la imagen, recibir un int
                }while (cr.moveToNext());
            }

        return bolsa;
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
        return total;
    }

    public Bitmap foto(int id)
    {
        SQLiteDatabase bd=getReadableDatabase();
        String sql="";
        Cursor cr=bd.rawQuery("SELECT img FROM bolsacompra WHERE id_producto="+id+";", null);

            byte[] im=cr.getBlob(4);
            Bitmap bpm=BitmapFactory.decodeByteArray(im, 0, im.length);

        return bpm;
    }
}
