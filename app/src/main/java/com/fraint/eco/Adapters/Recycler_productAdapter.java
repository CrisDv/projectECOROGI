package com.fraint.eco.Adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.fraint.eco.R;

import java.util.List;


public class Recycler_productAdapter extends RecyclerView.Adapter<Recycler_productAdapter.ViewHolder> {


    private OnProductListener mOnProductListener;
    public boolean showShimmer;
    int SHIMMER_ITEM=7;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView Nombre, precio, tipo;
        ImageView FotoProducto;
        private Button delete_prod;

        ShimmerFrameLayout shimerframe;

        OnProductListener onProductListener;

        public ViewHolder(View itemView, OnProductListener onProductListener)
        {
            super (itemView);
            shimerframe=itemView.findViewById(R.id.shimmer_item_producto);
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

    public List<item_producto> productolista;

    public Recycler_productAdapter(List<item_producto>productolista, OnProductListener onProductListener)
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

        if (showShimmer)
        {
            holder.shimerframe.startShimmer();
        }
        else
        {
            if(!productolista.get(position).isDisponibilidad())
            {
                System.out.println("EL OBJETO NO ESTA");
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));

            }
            else
            {
                holder.shimerframe.stopShimmer();
                holder.shimerframe.setShimmer(null);
                holder.Nombre.setText(productolista.get(position).getNombre());
                holder.Nombre.setBackgroundColor(Color.WHITE);

                holder.precio.setText(String.valueOf(productolista.get(position).getPrecio()));
                holder.precio.setBackgroundColor(Color.WHITE);

            /*holder.FotoProducto.setImageBitmap(productolista.get(position).getImgproduct());
            holder.FotoProducto.setImageResource(productolista.get(position).getImgproduct());*/
                holder.FotoProducto.setImageURI(productolista.get(position).getImgproduct());
                Glide.with(holder.FotoProducto.getContext()).load(productolista.get(position).getImgproduct()).into(holder.FotoProducto);


                holder.tipo.setText(productolista.get(position).getTipo());
                holder.tipo.setBackgroundColor(Color.WHITE);


                if (productolista.get(position).getTipo().equals("und"))
                {
                    int valueund=(int) productolista.get(position).getPrecio();
                    holder.precio.setText(String.valueOf(valueund));

                }
            }
        }
    }

    public interface OnProductListener{
        void onProductClick(int position);
    }

    public int getItemCount()
    {
        return showShimmer?SHIMMER_ITEM:productolista.size(); //Primero carga los items (SHIMMER) y luego la lista
    }
}
