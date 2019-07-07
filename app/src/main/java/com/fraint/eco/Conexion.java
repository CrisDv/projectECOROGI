package com.fraint.eco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexion extends SQLiteOpenHelper {

    private static final String Nombre_BD="name";
    private static final int Version_BD=1;
    private static final String TABLA_CARRO= " CREATE TABLE carrito ( " +
            "id int NOT NULL PRIMARY KEY, " +
            "product_id int NOT NULL,  suma int NOT NULL,  " +
            "gramos int NOT NULL,  " +
            "FOREIGN KEY (product_id) " +
            "REFERENCES productos (product_id));";

    private static  final String TABLA_PRODUCTOS ="CREATE TABLE productos("+
            "    product_id int PRIMARY KEY NOT NULL," +
            "    nombre VARCHAR(45) NOT NULL," +
            "    preciou int NOT NULL," +
            "    descripcion varchar (50) NOT NULL," +
            "    pesokg int NOT NULL);";

    private static final String TABLA_USUARIO = "CREATE TABLE usuario(" +
            "    correo varchar (50) NOT NULL PRIMARY KEY," +
            "    nombre varchar (50) NOT NULL," +
            "    gramos_acumulados int);";

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
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

    public void AgregarAlCarro (String id, int preciou )
    {

    }
}