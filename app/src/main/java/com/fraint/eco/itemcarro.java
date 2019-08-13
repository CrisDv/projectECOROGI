package com.fraint.eco;

import android.graphics.Bitmap;

public class itemcarro {

    int id_producto;
    private String Nombre;
    private int cantidad;
    private int total;
    private Bitmap img;
    private String tipo_unidad;


    public itemcarro() {

    }

    public itemcarro(int id_producto,String nombre, int cantidad, int total, Bitmap img, String tipo_unidad) {
        this.id_producto=id_producto;
        Nombre = nombre;
        this.cantidad = cantidad;
        this.total = total;
        this.img = img;
        this.tipo_unidad=tipo_unidad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getTipo_unidad() {
        return tipo_unidad;
    }

    public void setTipo_unidad(String tipo_unidad) {
        this.tipo_unidad = tipo_unidad;
    }
}
