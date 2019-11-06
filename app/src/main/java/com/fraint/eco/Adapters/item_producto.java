package com.fraint.eco.Adapters;

import android.graphics.Bitmap;

public class item_producto {
    private String id_product;
    private String Nombre;
    private float  precio;
    private String tipo;
    private Bitmap imgproduct;

    public item_producto(String id_product, String nombre, float precio, String tipo, Bitmap imgproduct) {
        this.id_product=id_product;
        Nombre = nombre;
        this.precio = precio;
        this.tipo=tipo;
        this.imgproduct = imgproduct;
    }

    public item_producto() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Bitmap getImgproduct() {
        return imgproduct;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImgproduct(Bitmap imgproduct) {
        this.imgproduct = imgproduct;
    }


    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }
}
