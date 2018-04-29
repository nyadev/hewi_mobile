package com.example.alumno.hewi_mobile;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alumno.hewi_mobile.BaseDatos.BDatos;
import com.example.alumno.hewi_mobile.BaseDatos.BdAction;
import com.example.alumno.hewi_mobile.RegistroUsuario.registro_usuario;

public class InicioSesion extends Activity {

    private EditText NombreUsuario, PassUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        getActionBar().hide();

        //Inicializamos
        NombreUsuario = findViewById(R.id.nombreUsuario);
        PassUsuario = findViewById(R.id.passUsuario);
    }

    public void Registrar(View view)
    {
        Intent registrar = new Intent (this, registro_usuario.class);
        startActivity(registrar);
    }

    public void IniciaSesion(View view) {
        Boolean validacion_NombreUsario = NombreUsuario.getText().toString().isEmpty();
        Boolean validacion_PassUsuario = PassUsuario.getText().toString().isEmpty();
        Boolean validacion_campos = validacion_NombreUsario || validacion_PassUsuario;
        String valor_NombreUsuario = "";
        String valor_PassUsuario = "";
        Boolean validacion_existencia;

        if(!validacion_campos)
        {
            valor_NombreUsuario = NombreUsuario.getText().toString();
            valor_PassUsuario = PassUsuario.getText().toString();
            BDatos conexion = new BDatos(this, "hewi_2", null, 1);
            SQLiteDatabase basedatos = conexion.getWritableDatabase();
            BdAction accion = new BdAction(basedatos);

            validacion_existencia = accion.InicioSesion(valor_NombreUsuario, valor_PassUsuario);

            if(validacion_existencia)
            {
                NombreUsuario.setText("");
                PassUsuario.setText("");
                Intent inicia = new Intent(this, pagina_principal.class);
                inicia.putExtra("CurpTerapeuta", accion.ObtenerCurp(valor_NombreUsuario));
                startActivity(inicia);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Usuario o contraseña incorrecto" , Toast.LENGTH_SHORT).show();
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Llena todos los campos" , Toast.LENGTH_SHORT).show();
        }
    }
}
