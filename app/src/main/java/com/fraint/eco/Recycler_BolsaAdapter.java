package com.fraint.eco;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recycler_BolsaAdapter extends RecyclerView.Adapter<Recycler_BolsaAdapter.ViewHolder> {

    private OnitembagListener mOnitembagListener;
    public static class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener
    {
        private TextView Nombre, Total, Cantidad, tipou;
        ImageView foto;
        Context context;
        private ImageView borraritem;

        OnitembagListener onitembagListener;

        public ViewHolder(View itemView, OnitembagListener onitembagListener)
        {
            super(itemView);
            context=itemView.getContext();
            Nombre=itemView.findViewById(R.id.itemName);
            Total=itemView.findViewById(R.id.TotalComprado);
            Cantidad=itemView.findViewById(R.id.Cantidad_Comprada);
            tipou=itemView.findViewById(R.id.tipo_cantidad);
            foto=itemView.findViewById(R.id.imageProduct);
            borraritem=itemView.findViewById(R.id.delete_product_from_bag);

            this.onitembagListener=onitembagListener;
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            onitembagListener.onibagClick(getAdapterPosition());
        }

    }

    public List<itemcarro> itemcarroLista;

    public Recycler_BolsaAdapter(List<itemcarro> itemcarroLista, OnitembagListener onitembagListener)
    {
        this.mOnitembagListener=onitembagListener;
        this.itemcarroLista=itemcarroLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carro, parent, false);

        ViewHolder viewHolder=new ViewHolder(view, mOnitembagListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int posicion)
    {

        holder.Nombre.setText(itemcarroLista.get(posicion).getNombre());
        holder.Total.setText(Integer.toString(itemcarroLista.get(posicion).getTotal()));
        holder.Cantidad.setText(Integer.toString(itemcarroLista.get(posicion).getCantidad()));
        holder.tipou.setText(itemcarroLista.get(posicion).getTipo_unidad());
        holder.foto.setImageBitmap(itemcarroLista.get(posicion).getImg());

        Context con=holder.context;

        holder.borraritem.setOnClickListener(view ->
        {
            itemcarroLista.remove(posicion);
            notifyItemRemoved(posicion);
            notifyItemRangeChanged(posicion, itemcarroLista.size());
            Conexion nn=new Conexion(con);
            nn.eliminarproducto(holder.Nombre.getText().toString());
            System.out.println("borrado x2");
            System.out.println("BORRADO");
        });
    }


    public interface OnitembagListener{
        void onibagClick(int position);
    }

    @Override
    public int getItemCount() {
        return itemcarroLista.size();
    }
}
