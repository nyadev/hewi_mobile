package com.example.alumno.hewi_mobile.Citas;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.BaseDatos.BdAction;
import com.example.alumno.hewi_mobile.R;

import java.util.Calendar;

public class registro_citas extends Activity {

    private EditText CurpPaciente;
    private DatePicker datepicker;
    private TimePicker TiempoInicio, TiempoFin;
    private String CurpTerapeuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_citas);

        getActionBar().hide();
        CurpTerapeuta = getIntent().getExtras().getString("CurpTerapeuta");
        InicializarViews();
    }

    private void InicializarViews()
    {
        CurpPaciente = findViewById(R.id.CurpPaciente);
        datepicker = findViewById(R.id.fechaselector);
        TiempoInicio = findViewById(R.id.TiempoInicio);
        TiempoFin = findViewById(R.id.TiempoFin);

        //Para el Date Picker
        datepicker = findViewById(R.id.fechaselector);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        datepicker.setMinDate(calendar.getTimeInMillis());
    }

    private Boolean validarCampos()
    {
        Boolean resultado;
        Boolean val_CurpPaciente = CurpPaciente.getText().toString().isEmpty();
        Boolean val_CurpLength = CurpPaciente.getText().toString().length() != 18;
        resultado = val_CurpPaciente || val_CurpLength;
        return resultado;
    }

    private String [] ObtenerDatos()
    {
        String [] valores = new String[5];
        //Para obtener la fecha
        int [] date_int = {datepicker.getDayOfMonth(), datepicker.getMonth() + 1, datepicker.getYear()};
        String date_string = date_int[0] + "/" + date_int[1] + "/" + date_int[2];
        //Para obtener la hora de inicio
        int [] time1_int = {TiempoInicio.getCurrentHour(), TiempoInicio.getCurrentMinute()};
        String time1_string = time1_int[0] + ":" + time1_int[1];
        //Para obtener la hora de fin
        int [] time2_int = {TiempoFin.getCurrentHour(), TiempoFin.getCurrentMinute()};
        String time2_string = time2_int[0] + ":" + time2_int[1];
        //Insertamos los valores finales
        valores[0] = CurpPaciente.getText().toString();
        valores[1] = CurpTerapeuta;
        valores[2] = date_string;
        valores[3] = time1_string;
        valores[4] = time2_string;

        return valores;
    }

    public void RegistrarCita(View view) {

        if(!validarCampos())
        {
            BDatos conexion = new BDatos(this, "hewi_4", null,1);
            SQLiteDatabase basedatos = conexion.getWritableDatabase();
            BdAction accion = new BdAction(basedatos);

            if(accion.RegistraCita(ObtenerDatos()))
            {
                Toast.makeText(getApplicationContext(),"Registro exitoso",
                        Toast.LENGTH_SHORT).show();
                CurpPaciente.setText("");
                Intent cambiar = new Intent(this, citas.class);
                cambiar.putExtra("CurpTerapeuta", CurpTerapeuta);
                startActivity(cambiar);
                finish();
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Ocurrió un error con el registro",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Algún dato es incorrecto" , Toast.LENGTH_SHORT).show();

        }

    }
}
