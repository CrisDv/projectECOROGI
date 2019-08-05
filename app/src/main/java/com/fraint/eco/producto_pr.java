package com.fraint.eco;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.InputStream;

public class producto_pr {
    private String id_product;
    private String Nombre;
    private String precio;
    private String tipo;
    private Bitmap imgproduct;

    public producto_pr(String id_product, String nombre, String precio,String tipo, Bitmap imgproduct) {
        this.id_product=id_product;
        Nombre = nombre;
        this.precio = precio;
        this.tipo=tipo;
        this.imgproduct = imgproduct;
    }

    public producto_pr() {

    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
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
