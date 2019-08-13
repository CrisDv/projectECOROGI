package com.fraint.eco;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Recycler_productAdapter extends RecyclerView.Adapter<Recycler_productAdapter.ViewHolder> {

    private OnProductListener mOnProductListener;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView Nombre, precio, tipo;
        ImageView FotoProducto;
        private Button delete_prod;

        OnProductListener onProductListener;

        public ViewHolder(View itemView, OnProductListener onProductListener)
        {
            super (itemView);
             Nombre=itemView.findViewById(R.id.Product_Name);
             precio=itemView.findViewById(R.id.precioproduct);
             FotoProducto=itemView.findViewById(R.id.imageProduct);
             tipo=itemView.findViewById(R.id.Unidad);

             this.onProductListener=onProductListener;
             itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            onProductListener.onProductClick(getAdapterPosition());
        }
    }

    public List<producto_pr> productolista;

    public Recycler_productAdapter(List<producto_pr>productolista, OnProductListener onProductListener)
    {
        this.productolista=productolista;
        this.mOnProductListener=onProductListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.item_producto, parent, false);

        ViewHolder viewHolder=new ViewHolder(view, mOnProductListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.Nombre.setText(productolista.get(position).getNombre());
        holder.precio.setText(String.valueOf(productolista.get(position).getPrecio()));
        holder.FotoProducto.setImageBitmap(productolista.get(position).getImgproduct());
        holder.tipo.setText(productolista.get(position).getTipo());

    }

    public interface OnProductListener{
        void onProductClick(int position);
    }

    public int getItemCount()
    {
        return productolista.size();
    }
}
