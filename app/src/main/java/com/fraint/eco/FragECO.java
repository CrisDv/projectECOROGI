package com.fraint.eco;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.Objects;

import static com.firebase.ui.auth.ui.email.RegisterEmailFragment.TAG;

public class FragECO extends Fragment implements View.OnClickListener{

    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_frag_eco, container, false);
        Button direccion = view.findViewById(R.id.street);
        direccion.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), ConfirmacionMAPS.class);
            Objects.requireNonNull(getActivity()).startActivity(intent);
            // closefragment();

        });


        final ImageView ofertas= view.findViewById(R.id.ofertas);

        final File file;
        try {
            file = File.createTempFile("oferta", "png");
            storageRef.child("Categorias/0-Ofertas.png").getFile(file)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        ofertas.setImageBitmap(bitmap);

                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }
//--------------------------------------------------------------------------------------

        final ImageView cereales= view.findViewById(R.id.categoria);

        cereales.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "cereales");
            startActivity(intent);
        });



//--------------------------------------------------------------------------------------
        //----IMAGEN 1
        final File file1;
        try {
            file1 = File.createTempFile("cereales", "png");
            storageRef.child("Categorias/1-Cereales.png").getFile(file1)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
                        cereales.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }

        final ImageView granos= view.findViewById(R.id.categoria2);
        granos.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "granos");
            startActivity(intent);
        });
        //----IMAGEN 2
        final File file2;
        try {
            file2 = File.createTempFile("granos", "png");
            storageRef.child("Categorias/2-Granos.png").getFile(file2)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                        granos.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView huevos= view.findViewById(R.id.categoria3);
        huevos.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "huevosyharina");
            startActivity(intent);
        });
        //----IMAGEN 3
        final File file3;
        try {
            file3 = File.createTempFile("huevos", "png");
            storageRef.child("Categorias/3-Huevos-y-Harina.png").getFile(file3)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file3.getAbsolutePath());
                        huevos.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView aceite= view.findViewById(R.id.categoria4);
        aceite.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "aceites");
            startActivity(intent);
        });
        //----IMAGEN 4
        final File file4;
        try {
            file4 = File.createTempFile("cereales", "png");
            storageRef.child("Categorias/4-Aceite.png").getFile(file4)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file4.getAbsolutePath());
                        aceite.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView dulce= view.findViewById(R.id.categoria8);
        dulce.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "dulces");
            startActivity(intent);
        });
        //----IMAGEN 5
        final File file5;
        try {
            file5 = File.createTempFile("dulce", "png");
            storageRef.child("Categorias/5-Dulce-y-sal.png").getFile(file5)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file5.getAbsolutePath());
                        dulce.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView aseo= view.findViewById(R.id.categoria11);
        aseo.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "aseo");
            startActivity(intent);
        });
        //----IMAGEN 6
        final File file6;
        try {
            file6 = File.createTempFile("dulce", "png");
            storageRef.child("Categorias/6-Aseo.png").getFile(file6)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file6.getAbsolutePath());
                        aseo.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView cuidado= view.findViewById(R.id.categoria7);
        cuidado.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "cuidadopersonal");
            startActivity(intent);
        });
        //----IMAGEN 7
        final File file7;
        try {
            file7 = File.createTempFile("cudados", "png");
            storageRef.child("Categorias/7-Dulceria.png").getFile(file7)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file7.getAbsolutePath());
                        cuidado.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView frutos= view.findViewById(R.id.categoria9);
        frutos.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "frutos");
            startActivity(intent);
        });
        //----IMAGEN 8
        final File file8;
        try {
            file8 = File.createTempFile("cereales", "png");
            storageRef.child("Categorias/8-Frutos.png").getFile(file8)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file8.getAbsolutePath());
                        frutos.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView mascota= view.findViewById(R.id.categoria10);
        mascota.setOnClickListener(view1 -> {
            Intent intent=new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "mascotas");
            startActivity(intent);
        });
        //----IMAGEN 9
        final File file9;
        try {
            file9 = File.createTempFile("mascotas", "png");
            storageRef.child("Categorias/9-Mascotas.png").getFile(file9)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file9.getAbsolutePath());
                        mascota.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        return view;
    }

    private void closefragment() {
        getActivity().onBackPressed();
    }

    //CATEGORIA 1
    final Runnable imagen_a= () -> {

    };

    @Override
    public void onClick(View view) {

    }


}
