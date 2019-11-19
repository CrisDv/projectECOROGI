package com.fraint.eco.Adapters;

import android.graphics.Bitmap;
import android.net.Uri;

public class item_carro {

    int id_producto;
    private String Nombre;
    private int cantidad;
    private int total;
    private Uri img;
    private String tipo_unidad;


    public item_carro() {

    }

    public item_carro(int id_producto, String nombre, int cantidad, int total, Uri img, String tipo_unidad) {
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

    public Uri getImg() {
        return img;
    }

    public void setImg(Uri img) {
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
