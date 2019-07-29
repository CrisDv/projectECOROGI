package com.fraint.eco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Recycler_product extends RecyclerView.Adapter<Recycler_product.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView Nombre, precio;
        ImageView FotoProducto;;

        public ViewHolder(View itemView)
        {
            super (itemView);
             Nombre=itemView.findViewById(R.id.Product_Name);
             precio=itemView.findViewById(R.id.precioproduct);
             FotoProducto=itemView.findViewById(R.id.imageProduct);
        }
    }

    public List<producto_pr> productolista;

    public Recycler_product(List<producto_pr>productolista)
    {
        this.productolista=productolista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       /* NavegacionL navegacionL= (NavegacionL) new NavegacionL().conexionbd();

        String sql ="SELECT * FROM productos WHERE";*/

        holder.Nombre.setText(productolista.get(position).getNombre());
        holder.precio.setText(productolista.get(position).getPrecio());
        holder.FotoProducto.setImageResource(productolista.get(position).getImgproduct());

    }

    public int getItemCount()
    {
        return productolista.size();
    }
}
