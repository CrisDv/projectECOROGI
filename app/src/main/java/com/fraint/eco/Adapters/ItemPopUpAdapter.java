package com.fraint.eco.Adapters;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fraint.eco.R;

import java.util.List;

public class ItemPopUpAdapter extends RecyclerView.Adapter<ItemPopUpAdapter.ViewHolderH> {

    List<Item_PopUpDetalles> listItem;
    public class ViewHolderH extends RecyclerView.ViewHolder
    {
        TextView nombre, cantidad;
        public ViewHolderH(@NonNull View ItemView)
        {
            super(ItemView);
            nombre=ItemView.findViewById(R.id.NombreDelProductoHistorial);
            cantidad=ItemView.findViewById(R.id.CantidadDelProductoHistorial);
        }
    }

    public ItemPopUpAdapter(List<Item_PopUpDetalles> listItem) {
        this.listItem = listItem;
    }

    @NonNull
    @Override
    public ViewHolderH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popuphistorial, null, false);
        return new ViewHolderH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderH holder, int position) {
        holder.nombre.setText(listItem.get(position).getNombre());
        holder.cantidad.setText(listItem.get(position).getCantidad());

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

}
