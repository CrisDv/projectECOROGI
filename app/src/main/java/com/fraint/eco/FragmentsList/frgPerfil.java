package com.fraint.eco.FragmentsList;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.fraint.eco.Historial;
import com.fraint.eco.Login;
import com.fraint.eco.P_InterfazUsuario;
import com.fraint.eco.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;


public class    frgPerfil extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    private static GoogleApiClient Googleapiclient;//API para autenticar inicio con Google (intermediario)
    static final int SignCode = 5;
    private com.google.firebase.auth.FirebaseAuth FirebaseAuth;
    private com.google.firebase.auth.FirebaseAuth.AuthStateListener AuthListener;
    private static final String TAG = "frgPerfil";

    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_lperfil, container, false);

        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)//Opciones para autenticar
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        Googleapiclient = new GoogleApiClient.Builder(view.getContext()).enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, GSO).build();

        P_InterfazUsuario red=new P_InterfazUsuario();
        ImageView wpp = view.findViewById(R.id.WPPcontact);
        wpp.setOnClickListener(view -> redes(1));

        ImageView FB = view.findViewById(R.id.FBcontact);
        FB.setOnClickListener(view -> redes(2));

        ImageView IG=view.findViewById(R.id.IGcontact);
        IG.setOnClickListener(view -> redes(3));

        Button pedidos=view.findViewById(R.id.PerfilPedidos);
        pedidos.setOnClickListener(view1 ->
        {
            //Toast.makeText(view.getContext(), "Pronto En Funcionamiento", Toast.LENGTH_LONG).show();
            Intent intent=new Intent(getContext(), Historial.class );
            startActivity(intent);
        });

        Button direcciones=view.findViewById(R.id.PerfilDirecciones);
        direcciones.setOnClickListener(view1 ->
        {
            Toast.makeText(view.getContext(), "Pronto En Funcionamiento", Toast.LENGTH_LONG).show();
        });

        Button gramos=view.findViewById(R.id.PerfilGramos);
        gramos.setOnClickListener(view1 ->
        {
            Toast.makeText(view.getContext(), "Pronto En Funcionamiento", Toast.LENGTH_LONG).show();
        });
        initialize();
        UIPerfil();
        return view;
    }


    private void UIPerfil()
    {
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(view.getContext());

        ImageView fperfil=view.findViewById(R.id.foto_perfil);
        try
        {
            if (account.getPhotoUrl()!=null)
            {
                Glide.with(this).load(account.getPhotoUrl()).into(fperfil);
            }
            else
            {
                Glide.with(this).load("https://www.sumarecursos.com/wp-content/uploads/2016/12/silueta-gris.png").into(fperfil);
            }
            TextView nombre=view.findViewById(R.id.User_name);
            if (account.getDisplayName()!=null)
            {
                nombre.setText(account.getDisplayName());
            }

        }
        catch (Exception e)
        {
            System.out.println("ERROR "+e);
        }
        Button sali=view.findViewById(R.id.cerrar);
        sali.setOnClickListener(view1 ->
        {
            signOut();
        });


    }



    public void redes(int i)
    {

        switch (i)
        {
            case 1:
                Uri uriWPP=Uri.parse("https://wa.me/573005222012");
                Intent intent =new Intent(Intent.ACTION_VIEW, uriWPP);

                PackageManager packageManager = getActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
                boolean isIntentSafe = activities.size() > 0;

// Start an activity if it's safe
                if (isIntentSafe)
                {
                    startActivity(intent);
                }
                break;
            case 2:
                Uri uriFB=Uri.parse("https://www.facebook.com/ecorogi");
                Intent intent1=new Intent(Intent.ACTION_VIEW, uriFB);
                PackageManager pk1=getActivity().getPackageManager();
                List<ResolveInfo> activities1=pk1.queryIntentActivities(intent1,0);
                boolean isIntent1Safe=activities1.size()>0;
                if (isIntent1Safe)
                {
                    startActivity(intent1);
                }
                break;

            case 3:

                Uri uriING=Uri.parse("https://www.instagram.com/ecorogi");
                Intent intent2=new Intent(Intent.ACTION_VIEW, uriING);
                PackageManager pk2=getActivity().getPackageManager();
                List<ResolveInfo> activities2=pk2.queryIntentActivities(intent2, 0);
                boolean isIntent2Safe=activities2.size()>0;
                if (isIntent2Safe)
                {
                    startActivity(intent2);
                }
                break;
        }
    }

    private void signOut(){
        FirebaseAuth.signOut();
        if (Auth.GoogleSignInApi != null){
            Auth.GoogleSignInApi.signOut(Googleapiclient).setResultCallback(status -> {
                if (status.isSuccess()){
                    Intent i = new Intent(view.getContext(), Login.class);
                    startActivity(i);
                    getActivity().finish();
                }else {
                    Toast.makeText(view.getContext(), "Error al Cerrar Sesion", Toast.LENGTH_SHORT).show();
                }
            });
        }

        deleteAppData();
        if (LoginManager.getInstance() != null){
            LoginManager.getInstance().logOut();
        }
    }

    private void initialize() {

        FirebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        AuthListener = firebaseAuth -> {
            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser != null) {
                Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getUid());
                Log.w(TAG, "onAuthStateChanged - signed_in" + firebaseUser.getEmail());
            } else {
                Log.w(TAG, "onAuthStateChanged - signed_out");
            }
        };
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onStart() {
        super.onStart();
        AuthListener = firebaseAuth -> FirebaseAuth.addAuthStateListener(AuthListener);

    }

    @Override
    public void onStop() {
        super.onStop();
        FirebaseAuth.removeAuthStateListener(AuthListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        Googleapiclient.stopAutoManage(getActivity());
        Googleapiclient.disconnect();
    }

    private void deleteAppData()
    {
        try
        { // clearing app data
            String packageName = view.getContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
