package com.fraint.eco;

import android.widget.ImageView;

public class producto_pr {
    private String Nombre;
    private String precio;
    private int imgproduct;

    public producto_pr(String nombre, String precio, int imgproduct) {
        Nombre = nombre;
        this.precio = precio;
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

    public int getImgproduct() {
        return imgproduct;
    }

    public void setImgproduct(int imgproduct) {
        this.imgproduct = imgproduct;
    }
}
