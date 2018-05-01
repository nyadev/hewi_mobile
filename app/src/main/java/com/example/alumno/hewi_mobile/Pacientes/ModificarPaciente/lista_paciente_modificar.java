package com.example.alumno.hewi_mobile.Pacientes.ModificarPaciente;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.Entidades.paciente;
import com.example.alumno.hewi_mobile.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class lista_paciente_modificar extends Activity {

    private String CurpTerapeuta;
    private ArrayList <paciente> ArrayPaciente;
    private ArrayList <String> NombrePaciente;
    private ListView ListaPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_paciente_modificar);

        getActionBar().hide();
        CurpTerapeuta = getIntent().getExtras().getString("CurpTerapeuta");
        Log.i("LISTA PACIENTES", "CURP: " + CurpTerapeuta);

        ListaPaciente = findViewById(R.id.ListaPacientes);
        ConsultarPacientes();

        ArrayAdapter Selector = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                NombrePaciente);
        ListaPaciente.setAdapter(Selector);

        ListaPaciente.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                paciente objeto_paciente = ArrayPaciente.get(position);
                Intent actividad = new Intent(lista_paciente_modificar.this, visualizar_paciente_modificar.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("paciente", objeto_paciente);
                actividad.putExtras(bundle);
                actividad.putExtra("CurpTerapeuta", CurpTerapeuta);
                startActivity(actividad);
            }
        });
    }

    public void ConsultarPacientes()
    {
        BDatos conexion = new BDatos(getApplicationContext(), "hewi_2", null, 1);
        SQLiteDatabase basedatos = conexion.getWritableDatabase();
        Cursor cursor = basedatos.rawQuery("SELECT * FROM PACIENTE WHERE CURP_TERAPEUTA = '" + CurpTerapeuta + "'", null);
        ArrayPaciente = new ArrayList<paciente>();
        NombrePaciente = new ArrayList<String>();
        paciente objeto_paciente;

        while(cursor.moveToNext())
        {
            objeto_paciente = new paciente();
            objeto_paciente.setCurp(cursor.getString(0));
            objeto_paciente.setNombre_paciente(cursor.getString(1));
            objeto_paciente.setApp_paciente(cursor.getString(2));
            objeto_paciente.setApm_paciente(cursor.getString(3));
            objeto_paciente.setNombre_tutor(cursor.getString(4));
            objeto_paciente.setApp_tutor(cursor.getString(5));
            objeto_paciente.setApm_tutor(cursor.getString(6));
            objeto_paciente.setCorreo_tutor(cursor.getString(7));
            objeto_paciente.setCelular_tutor(cursor.getString(8));

            ArrayPaciente.add(objeto_paciente);
        }

        for(int i = 0; i < ArrayPaciente.size(); i++)
        {
            NombrePaciente.add((i+1) + ".-" + ArrayPaciente.get(i).getNombre_paciente());
        }
    }
}

