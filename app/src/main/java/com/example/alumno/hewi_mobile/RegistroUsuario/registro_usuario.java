package com.example.alumno.hewi_mobile.RegistroUsuario;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.BaseDatos.BdAction;
import com.example.alumno.hewi_mobile.InicioSesion;
import com.example.alumno.hewi_mobile.R;

public class registro_usuario extends Activity {

    private EditText Nombre, App, Apm, CurpUsuario, NombreUsuario, PassUno, PassDos;
    private String [] ValoresComponentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);

        getActionBar().hide();
        InicializarComponentes();
    }

    public void InicializarComponentes()
    {
        Nombre = findViewById(R.id.NomUsuario);
        App = findViewById(R.id.AppUsuario);
        Apm = findViewById(R.id.ApmUsuario);
        CurpUsuario = findViewById(R.id.CurpUsuario);
        NombreUsuario = findViewById(R.id.NombreUsuario);
        PassUno = findViewById(R.id.PassUno);
        PassDos = findViewById(R.id.PassDos);
    }

    public Boolean Validar_Campos()
    {
        Boolean val_nombre = Nombre.getText().toString().isEmpty();
        Boolean val_app = App.getText().toString().isEmpty();
        Boolean val_apm = Apm.getText().toString().isEmpty();
        Boolean val_curp = CurpUsuario.getText().toString().isEmpty();
        Boolean val_NombreUsuario = NombreUsuario.getText().toString().isEmpty();
        Boolean val_PassUno = PassUno.getText().toString().isEmpty();
        Boolean val_PassDos = PassDos.getText().toString().isEmpty();

        Boolean resultado =
                val_nombre || val_app  || val_apm || val_curp || val_NombreUsuario
                || val_PassUno || val_PassDos;

        return resultado;
    }

    public void IniciarValores()
    {
        ValoresComponentes = new String[7];
        ValoresComponentes [0] = Nombre.getText().toString();
        ValoresComponentes [1] = App.getText().toString();
        ValoresComponentes [2] = Apm.getText().toString();
        ValoresComponentes [3] = CurpUsuario.getText().toString();
        ValoresComponentes [4] = NombreUsuario.getText().toString();
        ValoresComponentes [5] = PassUno.getText().toString();
        ValoresComponentes [6] = PassDos.getText().toString();
    }

    public void RegistrarUsuario(View view) {

        if(!Validar_Campos())
        {
            IniciarValores();

            if(ValoresComponentes[5].equals(ValoresComponentes[6]))
            {
                if(ValoresComponentes[3].length() == 18)
                {
                    Log.i("MENSAJE", "LLEGAMOS A ESTA ZONA DEL CÓDIGO");
                    BDatos conexion = new BDatos(this, "hewi_2", null, 1);
                    SQLiteDatabase basedatos = conexion.getWritableDatabase();
                    BdAction accion = new BdAction(basedatos);

                    if(accion.RegistrarTerapeuta(ValoresComponentes))
                    {
                        Toast.makeText(getApplicationContext(),"Ya puedes iniciar sesión" , Toast.LENGTH_SHORT).show();
                        Nombre.setText("");
                        App.setText("");
                        Apm.setText("");
                        CurpUsuario.setText("");
                        NombreUsuario.setText("");
                        PassUno.setText("");
                        PassDos.setText("");
                        Intent inicia = new Intent(this, InicioSesion.class);
                        startActivity(inicia);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"El nombre de usuario o el curp ya existen en el " +
                                "sistema" , Toast.LENGTH_SHORT).show();
                        CurpUsuario.setText("");
                        NombreUsuario.setText("");
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"El curp debe ser de 18 dígitos" , Toast.LENGTH_SHORT).show();
                    CurpUsuario.setText("");
                }
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Las contraseñas no coinciden" , Toast.LENGTH_SHORT).show();
                PassUno.setText("");
                PassDos.setText("");
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Llena todos los campos" , Toast.LENGTH_SHORT).show();
        }

    }
}
