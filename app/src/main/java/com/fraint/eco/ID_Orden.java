package com.fraint.eco;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

public class ID_Orden extends DialogFragment{

    View view;
    TextView NoIdOrden;
    Button ok;

    public void ID_Orden()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_id__orden, container, false);

        NoIdOrden=view.findViewById(R.id.NoOrdenDialog);
        ok=view.findViewById(R.id.okb);
        ok.setOnClickListener(view1 ->
        {
            Intent intent=new Intent(view.getContext(), P_InterfazUsuario.class);
            startActivity(intent);
        });

        return view;
    }

    public void setID(String NoOrden)
    {
        NoIdOrden.setText(NoOrden);
    }

}
