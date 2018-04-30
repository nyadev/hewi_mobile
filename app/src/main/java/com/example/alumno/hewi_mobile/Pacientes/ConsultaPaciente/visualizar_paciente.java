package com.example.alumno.hewi_mobile.Pacientes.ConsultaPaciente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.Entidades.paciente;
import com.example.alumno.hewi_mobile.Pacientes.menu_pacientes;
import com.example.alumno.hewi_mobile.R;

public class visualizar_paciente extends Activity {

    private TextView CurpPaciente, NombrePaciente, AppPaciente, ApmPaciente;
    private TextView NombreTutor, AppTutor, ApmTutor, CorreoTutor, CelularTutor;
    private String Curppaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_paciente);

        getActionBar().hide();
        InicializarView();
        AsignarInfo();
    }

    public void InicializarView()
    {
        CurpPaciente = findViewById(R.id.CurpPaciente);
        NombrePaciente = findViewById(R.id.NombrePaciente);
        AppPaciente = findViewById(R.id.AppPaciente);
        ApmPaciente = findViewById(R.id.ApmPaciente);
        NombreTutor = findViewById(R.id.NomTutor);
        AppTutor = findViewById(R.id.AppTutor);
        ApmTutor = findViewById(R.id.ApmTutor);
        CorreoTutor = findViewById(R.id.CorreoTutor);
        CelularTutor = findViewById(R.id.CelularTutor);
    }

    public void AsignarInfo()
    {
        Bundle RecibirObjeto = getIntent().getExtras();
        paciente objeto_paciente = null;

        if(RecibirObjeto != null)
        {
            objeto_paciente = (paciente) RecibirObjeto.getSerializable("paciente");

            CurpPaciente.setText(objeto_paciente.getCurp());
            Curppaciente = objeto_paciente.getCurp();
            NombrePaciente.setText(objeto_paciente.getNombre_paciente());
            AppPaciente.setText(objeto_paciente.getApp_paciente());
            ApmPaciente.setText(objeto_paciente.getApm_paciente());
            NombreTutor.setText(objeto_paciente.getNombre_tutor());
            AppTutor.setText(objeto_paciente.getApp_tutor());
            ApmTutor.setText(objeto_paciente.getApm_tutor());
            CorreoTutor.setText(objeto_paciente.getCorreo_tutor());
            CelularTutor.setText(objeto_paciente.getCelular_tutor());
        }
    }

    public void Eliminar(View view)
    {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("¡CUIDADO!");
        dialogo1.setMessage("¿ Desea eliminar a este paciente ?");
        dialogo1.setCancelable(false);
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                aceptar();
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                cancelar();
            }
        });
        dialogo1.show();
    }

    public void aceptar() {
        BDatos conexion = new BDatos(getApplicationContext(), "hewi_2", null, 1);
        SQLiteDatabase basedatos = conexion.getWritableDatabase();
        basedatos.delete("PACIENTE", "CURP = '" + Curppaciente + "'", null);

        Toast.makeText(getApplicationContext(),"Usuario eliminado" ,Toast.LENGTH_SHORT).show();

        Intent cambiar = new Intent(this, menu_pacientes.class);
        cambiar.putExtra("CurpTerapeuta", getIntent().getExtras().getString("CurpTerapeuta"));
        startActivity(cambiar);
        finish();

    }

    public void cancelar() {
        finish();
    }
}
