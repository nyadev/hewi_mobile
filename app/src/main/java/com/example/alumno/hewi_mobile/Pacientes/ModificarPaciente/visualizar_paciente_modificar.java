package com.example.alumno.hewi_mobile.Pacientes.ModificarPaciente;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.BaseDatos.BdAction;
import com.example.alumno.hewi_mobile.Entidades.paciente;
import com.example.alumno.hewi_mobile.Pacientes.menu_pacientes;
import com.example.alumno.hewi_mobile.R;

public class visualizar_paciente_modificar extends Activity {

    private TextView CurpPaciente, NombrePaciente, AppPaciente, ApmPaciente;
    private EditText NombreTutor, AppTutor, ApmTutor;
    private EditText CorreoTutor, CelularTutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_paciente_modificar);

        getActionBar().hide();
        InicializarView();
        AsignarInfo();
    }

    private void InicializarView()
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

    private Boolean ValidarCampos()
    {
        Boolean val_CurpPaciente = CurpPaciente.getText().toString().isEmpty();
        Boolean val_NombrePaciente = NombrePaciente.getText().toString().isEmpty();
        Boolean val_AppPaciente = AppPaciente.getText().toString().isEmpty();
        Boolean val_ApmPaciente = ApmPaciente.getText().toString().isEmpty();
        Boolean val_NombreTutor = NombreTutor.getText().toString().isEmpty();
        Boolean val_AppTutor = AppTutor.getText().toString().isEmpty();
        Boolean val_ApmTutor = ApmTutor.getText().toString().isEmpty();
        Boolean val_CorreoTutor = CorreoTutor.getText().toString().isEmpty();
        Boolean val_CelularTutor = CelularTutor.getText().toString().isEmpty();
        Boolean resultado =
                val_CurpPaciente || val_NombrePaciente || val_AppPaciente || val_ApmPaciente
                || val_NombreTutor || val_AppTutor || val_ApmTutor || val_CorreoTutor || val_CelularTutor;
        return resultado;
    }

    private String [] InicializarVariables()
    {
        String [] Variables = {
                CurpPaciente.getText().toString(),
                NombrePaciente.getText().toString(),
                AppPaciente.getText().toString(),
                ApmPaciente.getText().toString(),
                NombreTutor.getText().toString(),
                AppTutor.getText().toString(),
                ApmTutor.getText().toString(),
                CorreoTutor.getText().toString(),
                CelularTutor.getText().toString()
        };

        return Variables;
    }

    public void Modificar(View view)
    {
        if(!ValidarCampos())
        {
            BDatos conexion = new BDatos(getApplicationContext(), "hewi_2", null, 1);
            BdAction accion = new BdAction((SQLiteDatabase) conexion.getWritableDatabase());

            if(accion.ModificarPaciente(InicializarVariables()))
            {
                Toast.makeText(getApplicationContext(),"Modificado exitosamente" , Toast.LENGTH_SHORT).show();
                Intent cambiar = new Intent(this, menu_pacientes.class);
                cambiar.putExtra("CurpTerapeuta", getIntent().getExtras().getString("CurpTerapeuta"));
                startActivity(cambiar);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Ocurrió un error" , Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Llena todos los campos" , Toast.LENGTH_SHORT).show();
        }
    }
}
