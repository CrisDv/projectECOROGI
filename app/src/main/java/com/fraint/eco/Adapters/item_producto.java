package com.fraint.eco.Adapters;

import android.graphics.Bitmap;
import android.net.Uri;

public class item_producto {
    private String id_product;
    private String Nombre;
    private float  precio;
    private String tipo;
    private Uri imgproduct;
    private boolean disponibilidad;

    public item_producto(String id_product, String nombre, float precio, String tipo, Uri imgproduct, boolean disponibilidad) {
        this.id_product=id_product;
        Nombre = nombre;
        this.precio = precio;
        this.tipo=tipo;
        this.imgproduct = imgproduct;
        this.disponibilidad=disponibilidad;
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

    public Uri getImgproduct() {
        return imgproduct;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImgproduct(Uri imgproduct) {
        this.imgproduct = imgproduct;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}
