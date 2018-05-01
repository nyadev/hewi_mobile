package com.example.alumno.hewi_mobile.Citas;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.R;

import java.util.ArrayList;

public class visualizar_cita extends Activity {

    private String Fecha, CurpTerapeuta;
    private ListView Lista;
    private ArrayList<String> informacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_cita);
        getActionBar().hide();
        CurpTerapeuta = getIntent().getExtras().getString("CurpTerapeuta");
        Fecha = getIntent().getExtras().getString("Fecha");
        Lista = findViewById(R.id.ListaFecha);
        Consultar();

        ArrayAdapter Selector = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                informacion);
        Lista.setAdapter(Selector);
    }

    private void Consultar()
    {
        BDatos conexion = new BDatos(getApplicationContext(), "hewi_4", null, 1);
        SQLiteDatabase basedatos = conexion.getWritableDatabase();
        Cursor cursor = basedatos.rawQuery("SELECT * FROM CITAS", null);
        informacion = new ArrayList<String>();

        while(cursor.moveToNext())
        {
            if(cursor.getString(1).equals(CurpTerapeuta) && cursor.getString(2).equals(Fecha))
            {
                informacion.add(cursor.getString(0) + " de " +
                cursor.getString(3) + " a " + cursor.getString(4));
            }
        }
    }

}
