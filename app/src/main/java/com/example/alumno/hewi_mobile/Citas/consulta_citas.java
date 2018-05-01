package com.example.alumno.hewi_mobile.Citas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;

import com.example.alumno.hewi_mobile.R;

import java.util.ArrayList;

public class consulta_citas extends Activity {

    private DatePicker datepicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_citas);
        getActionBar().hide();
        datepicker = findViewById(R.id.fechaselector);
    }

    public void ConsultarFecha(View view) {
        int [] date_int = {datepicker.getDayOfMonth(), datepicker.getMonth() + 1, datepicker.getYear()};
        String date_string = date_int[0] + "/" + date_int[1] + "/" + date_int[2];
        Intent cambiar = new Intent(this, visualizar_cita.class);
        cambiar.putExtra("Fecha", date_string);
        cambiar.putExtra("CurpTerapeuta", getIntent().getExtras().getString("CurpTerapeuta"));
        startActivity(cambiar);
    }
}
