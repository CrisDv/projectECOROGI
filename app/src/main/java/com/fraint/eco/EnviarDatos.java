package com.fraint.eco;

import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

public class EnviarDatos {

    public BigInteger idpedidos()
    {
        BigInteger rbigid;

        long id1=(long) (Math.random()*100000)+1;
        Calendar fecha = Calendar.getInstance();
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int minuti=fecha.get(Calendar.MINUTE);
        int segundo=fecha.get(Calendar.SECOND);

        int anio=fecha.get(Calendar.YEAR);

        String idpedido5= String.valueOf(id1) +String.valueOf(mes)+ String.valueOf(dia) + String.valueOf(minuti) + String.valueOf(segundo);
        P_InterfazUsuario p_interfazUsuario=new P_InterfazUsuario();
        String tiempo= anio +"/"+ mes +"/"+ dia;

        rbigid=BigInteger.valueOf(Long.parseLong(idpedido5));

        /*Historial h=new Historial();
        h.DataHistorialPedidos(idpedido5, tiempo);*/

        return rbigid;
    }

    public void pedido(int total, BigInteger telefono, String correo, String fecha)
    {
        Conexionpst post=new Conexionpst();
        PreparedStatement ps=null;

        Calendar fecha2=Calendar.getInstance();
        int mes=fecha2.get(Calendar.MONTH)+1;
        int dia=fecha2.get(Calendar.DAY_OF_MONTH);
        int hora=fecha2.get(Calendar.HOUR_OF_DAY);
        int minuto=fecha2.get(Calendar.MINUTE);
        String fechapedido=String.valueOf(mes)+"/"+String.valueOf(dia)+"/"+String.valueOf(hora)+":"+String.valueOf(minuto);
        try
        {
            ps=post.conexionbd().prepareStatement("INSERT INTO pedido VALUES ("+idpedidos()+","+total+", '"+fecha+"', 1,'"+correo+"', "+telefono+", '"+fechapedido+"' )");
            ps.execute();
            ps.close();
        }
        catch (SQLException e)
        {
            System.out.println(e+"Enviar datos pedido");
        }

    }

    public void pedido_productos(String correo, int id_producto, int cantidad)
    {
        Conexionpst post=new Conexionpst();
        PreparedStatement statement=null;

        try
        {
            Statement st=post.conexionbd().createStatement();
            ResultSet rs=st.executeQuery("SELECT ID FROM pedido WHERE Client_correo='"+correo+"' ORDER BY fechadelpedido DESC LIMIT 1;");
                    while (rs.next())
                    {
                        statement=post.conexionbd().prepareStatement("INSERT INTO pedido_producto VALUES ("+BigInteger.valueOf(Long.parseLong(rs.getString(1)))+", "+id_producto+", "+cantidad+");");
                        statement.execute();
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
