package com.example.alumno.hewi_mobile.Pacientes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.alumno.hewi_mobile.R;

public class menu_pacientes extends Activity {

    private String CurpTerapeuta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pacientes);

        getActionBar().hide();
        CurpTerapeuta = getIntent().getExtras().getString("CurpTerapeuta");
        Log.i("CURP DEL TERAPEUTA", CurpTerapeuta);
    }


    public void RegistraPaciente(View view)
    {
        Intent registra = new Intent(this, registra_paciente.class);
        registra.putExtra("CurpTerapeuta", CurpTerapeuta);
        startActivity(registra);
    }
}
