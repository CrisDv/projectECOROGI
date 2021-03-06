package com.fraint.eco.FragmentsList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.fraint.eco.MapsActivity;
import com.fraint.eco.Lista_Categoria;
import com.fraint.eco.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.Objects;

public class frgCategorias extends Fragment{

    FirebaseStorage storage = FirebaseStorage.getInstance();
    final StorageReference storageRef = storage.getReference();
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lcategorias, container, false);


        Button direccion = view.findViewById(R.id.street);
        direccion.setOnClickListener(v -> {

            Intent intent = new Intent(getActivity(), MapsActivity.class);
            Objects.requireNonNull(getActivity()).startActivity(intent);
            // closefragment();

        });


        final ImageView ofertas = view.findViewById(R.id.ofertas);

        ofertas.setOnClickListener(view1 ->
        {
            //load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Combos");
            startActivity(intent);
        });
        final File file;
        try {
            file = File.createTempFile("oferta", "png");
            storageRef.child("Categorias/Combos.png").getFile(file)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        ofertas.setImageBitmap(bitmap);

                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
            e.printStackTrace();
        }
//--------------------------------------------------------------------------------------

        final ImageView cereales = view.findViewById(R.id.categoria);

        cereales.setOnClickListener(view1 -> {

            //load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }

        final ImageView granos = view.findViewById(R.id.categoria2);
        granos.setOnClickListener(view1 -> {
           // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Granos");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView huevos = view.findViewById(R.id.categoria3);
        huevos.setOnClickListener(view1 -> {
          //  load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Huevos y Harina");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView aceite = view.findViewById(R.id.categoria4);
        aceite.setOnClickListener(view1 -> {
            //load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Aceites");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView dulce = view.findViewById(R.id.categoria8);
        dulce.setOnClickListener(view1 -> {
           // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Dulce y Sal");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView aseo = view.findViewById(R.id.categoria11);
        aseo.setOnClickListener(view1 -> {
         //   load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Aseo del Hogar");
            startActivity(intent);
        });
        //----IMAGEN 6
        final File file6;
        try {
          //  load();
            file6 = File.createTempFile("dulce", "png");
            storageRef.child("Categorias/6-Aseo.png").getFile(file6)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file6.getAbsolutePath());
                        aseo.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView cuidado = view.findViewById(R.id.categoria7);
        cuidado.setOnClickListener(view1 -> {
          //  load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Dulceria");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView frutos = view.findViewById(R.id.categoria9);
        frutos.setOnClickListener(view1 -> {

          //  load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Frutos Secos");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            // Log.e(TAG, "Ocurrió un error en la descarga de imágenes");
            e.printStackTrace();
        }


        final ImageView mascota = view.findViewById(R.id.categoria10);
        mascota.setOnClickListener(view1 -> {
           // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Mascotas");
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
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
        }


        final ImageView Lonchera = view.findViewById(R.id.cat_lonchera);
        Lonchera.setOnClickListener(view1 -> {
            // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Lonchera");

            startActivity(intent);
        });
        //----IMAGEN 9
        final File file10;
        try {
            file10 = File.createTempFile("lonchera", "png");
            storageRef.child("Categorias/10-Lonchera.png").getFile(file10)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file10.getAbsolutePath());
                        Lonchera.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
            System.out.println("FRGCATEGORIAS "+e);
        }


        final ImageView Mecato = view.findViewById(R.id.cat_mecato);
        Mecato.setOnClickListener(view1 -> {
            // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Mecato");
            startActivity(intent);
        });
        //----IMAGEN 9
        final File file11;
        try {
            file11 = File.createTempFile("mecato", "png");
            storageRef.child("Categorias/12-Mecato.png").getFile(file11)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file11.getAbsolutePath());
                        Mecato.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
        }

        final ImageView Aseo_p = view.findViewById(R.id.cat_Aseopersonal);
        Aseo_p.setOnClickListener(view1 -> {
            // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Aseo Personal");
            startActivity(intent);
        });
        //----IMAGEN 9
        final File file12;
        try {
            file12 = File.createTempFile("mascotas", "png");
            storageRef.child("Categorias/11-Aseo-personal.png").getFile(file12)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file12.getAbsolutePath());
                        Aseo_p.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
        }

//--------------------------------------------------------------------------
        final ImageView catBebidas = view.findViewById(R.id.cat_bebidas);
        catBebidas.setOnClickListener(view1 -> {
            // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Bebidas");
            startActivity(intent);
        });

        final File file13;
        try {
            file13 = File.createTempFile("Bebidas", "png");
            storageRef.child("Categorias/15-Bebidas.png").getFile(file13)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file13.getAbsolutePath());
                        catBebidas.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
        }
//--------------------------------------------------------------------------
        final ImageView catpastas = view.findViewById(R.id.cat_Pastas);
        catpastas.setOnClickListener(view1 -> {
            // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Pastas");
            startActivity(intent);
        });

        final File file14;
        try {
            file14 = File.createTempFile("Pastas", "png");
            storageRef.child("Categorias/13-Pastas.png").getFile(file14)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file14.getAbsolutePath());
                        catpastas.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
        }
//--------------------------------------------------------------------------
        final ImageView catsopas = view.findViewById(R.id.cat_sopas);
        catsopas.setOnClickListener(view1 -> {
            // load();
            Intent intent = new Intent(getContext(), Lista_Categoria.class);
            intent.putExtra("valor", "Salsas y Sopas");
            startActivity(intent);
        });

        final File file15;
        try {
            file15 = File.createTempFile("Sopas", "png");
            storageRef.child("Categorias/14-Sopas-y-salsas.png").getFile(file15)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(file15.getAbsolutePath());
                        catsopas.setImageBitmap(bitmap);
                    }).addOnFailureListener(e -> {
                //Log.e(TAG, "Ocurrio un error al mostrar la imagen");
                e.printStackTrace();
            });
        } catch (Exception e) {
            //cc
            e.printStackTrace();
        }
        return view;

    }



}

