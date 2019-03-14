package com.fraint.eco;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterList extends RecyclerView.Adapter<AdapterList.ViewHolderlist>{
    @NonNull
    @Override
    public AdapterList.ViewHolderlist onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterList.ViewHolderlist holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolderlist extends RecyclerView.ViewHolder {
        public ViewHolderlist(View itemView) {
            super(itemView);
        }
    }
}
