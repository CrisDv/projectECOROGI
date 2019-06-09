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
import android.os.Handler;
import android.support.annotation.FractionRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.actions.ItemListIntents;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.Objects;

import static com.firebase.ui.auth.ui.email.RegisterEmailFragment.TAG;

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
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });

        final ImageView ofertas=(ImageView)view.findViewById(R.id.ofertas);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReference();
        final File file;
        try {
            file = File.createTempFile("oferta", "png");
            storageRef.child("Categorias/0-Ofertas.png").getFile(file)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                            ofertas.setImageBitmap(bitmap);

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }
        final ImageView cereales=(ImageView)view.findViewById(R.id.categoria);
        //----IMAGEN 1
        final File file1;
        try {
            file1 = File.createTempFile("cereales", "png");
            storageRef.child("Categorias/1-Cereales.png").getFile(file1)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
                            cereales.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }

        final ImageView granos=(ImageView)view.findViewById(R.id.categoria2);
        //----IMAGEN 2
        final File file2;
        try {
            file2 = File.createTempFile("granos", "png");
            storageRef.child("Categorias/2-Granos.png").getFile(file2)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                            granos.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView huevos=(ImageView)view.findViewById(R.id.categoria3);
        //----IMAGEN 3
        final File file3;
        try {
            file3 = File.createTempFile("huevos", "png");
            storageRef.child("Categorias/3-Huevos-y-Harina.png").getFile(file3)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file3.getAbsolutePath());
                            huevos.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView aceite=(ImageView)view.findViewById(R.id.categoria4);
        //----IMAGEN 4
        final File file4;
        try {
            file4 = File.createTempFile("cereales", "png");
            storageRef.child("Categorias/4-Aceite.png").getFile(file4)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file4.getAbsolutePath());
                            aceite.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView dulce=(ImageView)view.findViewById(R.id.categoria8);
        //----IMAGEN 5
        final File file5;
        try {
            file5 = File.createTempFile("dulce", "png");
            storageRef.child("Categorias/5-Dulces-y-Sal.png").getFile(file5)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file5.getAbsolutePath());
                            dulce.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView aseo=(ImageView)view.findViewById(R.id.categoria11);
        //----IMAGEN 6
        final File file6;
        try {
            file6 = File.createTempFile("dulce", "png");
            storageRef.child("Categorias/6-Aseo.png").getFile(file6)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file6.getAbsolutePath());
                            aseo.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView cuidado=(ImageView)view.findViewById(R.id.categoria7);
        //----IMAGEN 7
        final File file7;
        try {
            file7 = File.createTempFile("cudados", "png");
            storageRef.child("Categorias/7-Cuidado.png").getFile(file7)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file7.getAbsolutePath());
                            cuidado.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView frutos=(ImageView)view.findViewById(R.id.categoria9);
        //----IMAGEN 8
        final File file8;
        try {
            file8 = File.createTempFile("cereales", "png");
            storageRef.child("Categorias/8-Frutos.png").getFile(file8)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file8.getAbsolutePath());
                            frutos.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView mascota=(ImageView)view.findViewById(R.id.categoria10);
        //----IMAGEN 9
        final File file9;
        try {
            file9 = File.createTempFile("mascotas", "png");
            storageRef.child("Categorias/9-Mascotas.png").getFile(file9)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(file9.getAbsolutePath());
                            mascota.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                    e.printStackTrace();
                }
            });
        }catch (Exception e){
            Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }

        return view;
    }

    private void carga()
    {
        int DURACION_IMAHEN= 200;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                for (int i=1;i<=10;i++)
                {

                }
            }
        },DURACION_IMAHEN);
    }

    @Override
    public void onClick(View view) {


    }
}
