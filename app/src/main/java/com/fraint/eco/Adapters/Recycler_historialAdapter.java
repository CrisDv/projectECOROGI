package com.fraint.eco.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fraint.eco.R;

import java.util.List;

public class Recycler_historialAdapter extends RecyclerView.Adapter<Recycler_historialAdapter.ViewHolderDatos> {

    List<item_historial> listdatos;
    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView idproducto, fecha;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            idproducto=itemView.findViewById(R.id.PedidoNo);
            fecha=itemView.findViewById(R.id.fecha);
        }

    }
    public Recycler_historialAdapter(List<item_historial> listdatos) {
        this.listdatos = listdatos;
    }

    @NonNull
    @Override
    public Recycler_historialAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, null, false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_historialAdapter.ViewHolderDatos holder, int position) {

        holder.idproducto.setText("ID: "+listdatos.get(position).getIdproducto());
        holder.fecha.setText(listdatos.get(position).getFecha());

    }

    @Override
    public int getItemCount() {
        return listdatos.size();
    }


}
