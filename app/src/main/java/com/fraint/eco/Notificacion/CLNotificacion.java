package com.fraint.eco.Notificacion;

public class CLNotificacion {

    private String ID;
    private String titulo;
    private String descripcion;
    private String Descuento;

    public CLNotificacion(String ID, String titulo, String descripcion, String descuento) {
        this.ID = ID;
        this.titulo = titulo;
        this.descripcion = descripcion;
        Descuento = descuento;
    }

    public CLNotificacion() {

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescuento() {
        return Descuento;
    }

    public void setDescuento(String descuento) {
        Descuento = descuento;
    }

}
