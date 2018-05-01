package com.example.alumno.hewi_mobile.Citas;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TabHost;
import android.widget.TimePicker;

import com.example.alumno.hewi_mobile.R;

import java.util.Calendar;
import java.util.Date;

public class citas extends Activity {

    private DatePicker datepicker;
    private TimePicker timepicker;
    private String CurpTerapeuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        //Ocultamos la toolbar
        getActionBar().hide();
        CurpTerapeuta = getIntent().getExtras().getString("CurpTerapeuta");
    }

    public void RegistrarCita(View view) {
        Intent registrar = new Intent(this, registro_citas.class);
        registrar.putExtra("CurpTerapeuta", CurpTerapeuta);
        startActivity(registrar);
        finish();
    }

    public void ConsultaCita(View view)
    {
        Intent consulta = new Intent(this, consulta_citas.class);
        consulta.putExtra("CurpTerapeuta", CurpTerapeuta);
        startActivity(consulta);
        finish();
    }
}
