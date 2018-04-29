package com.example.alumno.hewi_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alumno.hewi_mobile.Pacientes.menu_pacientes;

import java.util.List;

public class pagina_principal extends Activity {

    private String[] ListaContenido;
    private DrawerLayout varDrawerLayout;
    private ListView varDrawerList;
    private String CurpUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        //Ocultar ActionBar
        getActionBar().hide();
        CurpUsuario = getIntent().getExtras().getString("CurpTerapeuta");
        Log.i("CURP DEL TERAPEUTA", CurpUsuario);

    }

    public void Citas(View view) {
        Intent Citas = new Intent(this, citas.class);
        startActivity(Citas);
    }

    //MÉTODO PARA IR CON LOS PACIENTES
    public void IrPaciente(View view)
    {
        Intent paciente = new Intent(this, menu_pacientes.class);
        paciente.putExtra("CurpTerapeuta", CurpUsuario);
        startActivity(paciente);
    }

    //MÉTODO CREADO PARA CERRAR SESIÓN
    public void CerrarSesion(View view) {
        Intent Cerrarsesion = new Intent(this, InicioSesion.class);
        startActivity(Cerrarsesion);
        //Quitamos esta actividad de la pila de actividades
        finish();
    }
}
