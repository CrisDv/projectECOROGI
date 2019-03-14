package com.fraint.eco;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.actions.ItemListIntents;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragECO extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_frag_eco, container, false);

        Button direccion = (Button) view.findViewById(R.id.street);
        direccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ConfirmacionMAPS.class);
                getActivity().startActivity(intent);
            }
        });

        carga(view);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    private void carga(View v)
    {
        ImageView cat=(ImageView)v.findViewById(R.id.categoria12);
        Glide.with(this).load("http://ak8.picdn.net/shutterstock/videos/27542833/thumb/10.jpg").into(cat);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Productos.class);
                getActivity().startActivity(intent);
            }
        });
        //FIREBASE STORAGE
        ImageView huevos = (ImageView)v.findViewById(R.id.categoria2);
    }

    private void DataBaseIMG()
    {
        DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
        referencia.child("Artist").child("continuacion");

        //referencia.addValueEventListener()
    }
}
