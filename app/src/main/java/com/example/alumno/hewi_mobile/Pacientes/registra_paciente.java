package com.example.alumno.hewi_mobile.Pacientes;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.BaseDatos.BdAction;
import com.example.alumno.hewi_mobile.R;

public class registra_paciente extends Activity {

    private EditText NombrePaciente, AppPaciente, ApmPaciente;
    private EditText NombreTutor, AppTutor, ApmTutor;
    private EditText CorreoTutor, CelularTutor;
    private EditText CurpPaciente;
    private String CurpTerapeuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registra_paciente);

        getActionBar().hide();
        InicializarView();
        CurpTerapeuta = getIntent().getExtras().getString("CurpTerapeuta");
        Log.i("CURP DEL TERAPEUTA", CurpTerapeuta);
    }

    public void InicializarView()
    {
        NombrePaciente = findViewById(R.id.NomUsuario);
        AppPaciente = findViewById(R.id.AppUsuario);
        ApmPaciente = findViewById(R.id.ApmUsuario);
        NombreTutor = findViewById(R.id.NomTutor);
        AppTutor = findViewById(R.id.AppTutor);
        ApmTutor = findViewById(R.id.ApmTutor);
        CorreoTutor = findViewById(R.id.CorreoTutor);
        CelularTutor = findViewById(R.id.CelularTutor);
        CurpPaciente = findViewById(R.id.CurpPaciente);
    }

    public Boolean ValidarCampos()
    {
        Boolean val_NombrePaciente = NombrePaciente.getText().toString().isEmpty();
        Boolean val_AppPaciente = AppPaciente.getText().toString().isEmpty();
        Boolean val_ApmPaciente = ApmPaciente.getText().toString().isEmpty();
        Boolean val_NombreTutor = NombreTutor.getText().toString().isEmpty();
        Boolean val_AppTutor = AppTutor.getText().toString().isEmpty();
        Boolean val_ApmTutor = ApmTutor.getText().toString().isEmpty();
        Boolean val_CorreoTutor = CorreoTutor.getText().toString().isEmpty();
        Boolean val_CelularTutor = CelularTutor.getText().toString().isEmpty();
        Boolean val_CurpPaciente = CurpPaciente.getText().toString().isEmpty();

        Boolean resultado = val_NombrePaciente || val_AppPaciente || val_ApmPaciente || val_NombreTutor
                || val_AppTutor || val_ApmTutor || val_CorreoTutor || val_CelularTutor || val_CurpPaciente;

        return resultado;
    }

    public void VaciarCampos()
    {
        NombrePaciente.setText("");
        AppPaciente.setText("");
        ApmPaciente.setText("");
        NombreTutor.setText("");
        AppTutor.setText("");
        ApmTutor.setText("");
        CorreoTutor.setText("");
        CelularTutor.setText("");
        CurpPaciente.setText("");
    }

    public String [] AsignarValores()
    {
        String [] Campos = {
                CurpPaciente.getText().toString(),
                NombrePaciente.getText().toString(),
                AppPaciente.getText().toString(),
                ApmPaciente.getText().toString(),
                NombreTutor.getText().toString(),
                AppTutor.getText().toString(),
                ApmTutor.getText().toString(),
                CorreoTutor.getText().toString(),
                CelularTutor.getText().toString(),
                CurpTerapeuta
        };

        return Campos;
    }

    public void Registro(View view) {
        if(!ValidarCampos())
        {
            BDatos conexion = new BDatos(this, "hewi_2", null, 1);
            SQLiteDatabase basedatos = conexion.getWritableDatabase();
            BdAction accion = new BdAction(basedatos);
            if(accion.RegistrarPaciente(AsignarValores()))
            {
                Toast.makeText(getApplicationContext(),"Paciente registrado",
                        Toast.LENGTH_SHORT).show();
                VaciarCampos();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Ya existe un paciente con " +
                        "esa información" , Toast.LENGTH_SHORT).show();
                VaciarCampos();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Llena todos los campos" , Toast.LENGTH_SHORT).show();
        }
    }
}
