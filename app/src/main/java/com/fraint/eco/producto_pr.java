package com.fraint.eco;

import android.widget.ImageView;

public class producto_pr {
    private String Nombre;
    private String precio;
    private String tipo;
    private int imgproduct;

    public producto_pr(String nombre, String precio,String tipo, int imgproduct) {
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

    public int getImgproduct() {
        return imgproduct;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setImgproduct(int imgproduct) {
        this.imgproduct = imgproduct;
    }


}
