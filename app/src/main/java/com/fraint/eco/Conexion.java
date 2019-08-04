package com.fraint.eco;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Conexion extends SQLiteOpenHelper {

    private static final String Nombre_BD="name";
    private static final int Version_BD=1;
    private static final String TABLA_CARRO= " CREATE TABLE carrito ( " +
            "id VARCHAR (15) NOT NULL PRIMARY KEY, " +
            "cantidad INT NOT NULL, "+
            "preciopr int NOT NULL,  " +
            "cantidad int NOT NULL,  " +
            "img bytea;";

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
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS"+ TABLA_CARRO);
        sqLiteDatabase.execSQL(TABLA_CARRO);
    }

    public List<itemcarro> ContenidoBolsa()
    {

        SQLiteDatabase bd=getReadableDatabase();
        Cursor cr=bd.rawQuery("SELECT * FROM carrito;", null);

        List<itemcarro> bolsa=new ArrayList<>();

        if (cr.moveToFirst())
        {
            do {
                bolsa.add(new itemcarro(cr.getString(0), cr.getInt(1), cr.getInt(3), R.drawable.ecologo));
            }while (cr.moveToNext());
        }
        return bolsa;

    }

}
