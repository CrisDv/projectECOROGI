package com.fraint.eco;

import android.annotation.SuppressLint;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.actions.ItemListIntents;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

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

        ImageView imgo =(ImageView)view.findViewById(R.id.ofertas);
        carga(view);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    private void carga(View v)
    {
        StorageReference mStorageImage=FirebaseStorage.getInstance().getReference().child("Categorias").child("2-Granos.png");
        mStorageImage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               // Glide.with(v).load(account.getPhotoUrl()).into(perfil);
            }
        });
    }

}
