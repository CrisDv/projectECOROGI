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
    private OnItemHistorialListener itemHistorialListener;
    public class ViewHolderDatos extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView idproducto, fecha;
        OnItemHistorialListener onitemHistorialListener;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);

            idproducto=itemView.findViewById(R.id.PedidoNo);
            fecha=itemView.findViewById(R.id.fecha);

            this.onitemHistorialListener=itemHistorialListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemHistorialListener.onItemListener(getAdapterPosition());
        }
    }
    public Recycler_historialAdapter(List<item_historial> listdatos, OnItemHistorialListener itemHistorialListener) {

        this.listdatos = listdatos;
        this.itemHistorialListener=itemHistorialListener;
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

    public interface OnItemHistorialListener
    {
        void onItemListener(int position);
    }

}
