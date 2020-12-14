package com.fraint.eco.Adapters;

public class Item_PopUpDetalles {
    String nombre;
    String Cantidad;

    public Item_PopUpDetalles(String nombre, String cantidad) {
        this.nombre = nombre;
        Cantidad = cantidad;
    }

    public Item_PopUpDetalles() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return Cantidad;
    }

    public void setCantidad(String cantidad) {
        Cantidad = cantidad;
    }
}
