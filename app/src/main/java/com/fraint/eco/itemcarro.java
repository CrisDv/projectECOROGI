package com.fraint.eco;

import android.graphics.Bitmap;

public class itemcarro {

    int id_producto;
    private String Nombre;
    private int cantidad;
    private int total;
    private int img;


    public itemcarro() {

    }

    public itemcarro(int id_producto,String nombre, int cantidad, int total, int img) {
        this.id_producto=id_producto;
        Nombre = nombre;
        this.cantidad = cantidad;
        this.total = total;
        this.img = img;
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

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}
