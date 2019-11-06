package com.fraint.eco;

import android.content.Context;

import com.fraint.eco.Connections_.Conexion;
import com.fraint.eco.Connections_.Conexionpst;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class EnviarDatos {

    private Context context;

    public EnviarDatos(Context context) {
        this.context = context;
    }

    public void pedido(int total, BigInteger telefono, String correo, String fecha) {
        Conexionpst post = new Conexionpst();

        Conexion cn= new Conexion(context);
        PreparedStatement ps = null;

        Calendar fecha2 = Calendar.getInstance();
        int mes = fecha2.get(Calendar.MONTH) + 1;
        int dia = fecha2.get(Calendar.DAY_OF_MONTH);
        int hora = fecha2.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha2.get(Calendar.MINUTE);

        BigInteger bigid=cn.generarid();
        String fechapedido = String.valueOf(mes) + "/" + String.valueOf(dia) + "/" + String.valueOf(hora) + ":" + String.valueOf(minuto);
        try {
            ps = post.conexionbd().prepareStatement("INSERT INTO pedido VALUES ("+bigid+"," + total + ", '" + fecha + "', 1,'" + correo + "', " + telefono + ", '" + fechapedido + "' )");
            System.out.println("pedido: "+ bigid);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e + "Enviar datos pedido");
        }


    }

    public void pedido_productos(String correo, int id_producto, int cantidad)
    {
        Conexionpst post=new Conexionpst();
        PreparedStatement statement=null;
        Conexion con=new Conexion(context);
        BigInteger bigid=BigInteger.valueOf(con.recogerid());
        try
        {
            Statement st=post.conexionbd().createStatement();
            //ResultSet rs=st.executeQuery("SELECT ID FROM pedido WHERE Client_correo='"+correo+"' ORDER BY fechadelpedido DESC LIMIT 1;");
            ResultSet rs=st.executeQuery("SELECT ID FROM pedido WHERE Client_correo='"+correo+"';");
                    while (rs.next())
                    {
                        statement=post.conexionbd().prepareStatement("INSERT INTO pedido_producto VALUES ("+bigid+", "+id_producto+", "+cantidad+");");
                        statement.execute();
                        System.out.println("pedido_producto: "+bigid);
                    }
                    rs.close();
        }
        catch (SQLException e)
        {
            System.out.println(e+" Enviar datos pedido_productos");
        }


    }

    String direccion(String correo)  {
        Conexionpst post=new Conexionpst();
        Statement statement=null;
        ResultSet rs=null;
        String dir="";
        try {
            statement=post.conexionbd().createStatement();
            rs=statement.executeQuery("SELECT direccion FROM usuarios WHERE correo='"+correo+"';");
            if (rs.next())
            {
                dir=rs.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            return dir;
        }
    }


}
