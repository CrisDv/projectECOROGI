package com.fraint.eco;

import java.math.BigInteger;

public class item_historial {

    String idproducto;
    String fecha;

    public item_historial(String idproducto, String fecha) {
        this.idproducto = idproducto;
        this.fecha = fecha;
    }

    public item_historial() {
    }

    public String getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(String idproducto) {
        this.idproducto = idproducto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
