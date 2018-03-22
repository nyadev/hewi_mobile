package com.example.alumno.hewi_mobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class pagina_principal extends Activity {

    private  String[] ListaContenido;
    private DrawerLayout varDrawerLayout;
    private ListView varDrawerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_principal);

        //Ocultar ActionBar
        getActionBar().hide();


    }
}
