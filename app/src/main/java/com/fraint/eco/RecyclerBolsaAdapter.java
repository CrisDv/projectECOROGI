package com.fraint.eco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.annotation.Nonnull;

public class RecyclerBolsaAdapter extends RecyclerView.Adapter<RecyclerBolsaAdapter.ViewHolder> {


    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView Nombre, Total, Cantidad;
        ImageView foto;


        public ViewHolder(View itemView)
        {
            super(itemView);
            Nombre=itemView.findViewById(R.id.itemName);
            Total=itemView.findViewById(R.id.TotalComprado);
            Cantidad=itemView.findViewById(R.id.Cantidad_Comprada);
            //foto=itemView<--find id de la imagen del item bolsa
        }

    }

    public List<itemcarro> itemcarroLista;

    public RecyclerBolsaAdapter(List<itemcarro> itemcarroLista)
    {
        this.itemcarroLista=itemcarroLista;
    }

    @Nonnull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcarro, parent, false);

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@Nonnull ViewHolder holder, int posicion)
    {

        holder.Nombre.setText(itemcarroLista.get(posicion).getNombre());
        holder.Total.setText(Integer.toString(itemcarroLista.get(posicion).getTotal()));
        holder.Cantidad.setText(Integer.toString(itemcarroLista.get(posicion).getCantidad()));
    }

    @Override
    public int getItemCount() {
        return itemcarroLista.size();
    }
}
