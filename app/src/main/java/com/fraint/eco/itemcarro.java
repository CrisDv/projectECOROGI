package com.fraint.eco;

public class itemcarro {

    private String Nombre;
    private int cantidad;
    private int total;
    private int img;


    public itemcarro() {

    }

    public itemcarro(String nombre, int cantidad, int total, int img) {
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
}
