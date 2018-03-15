package com.example.alumno.hewi_mobile;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InicioSesion extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        getActionBar().hide();
    }

    public void IniciaSesion(View view) {
        Intent Iniciosesion = new Intent(this, pagina_principal.class);
        startActivity(Iniciosesion);
    }
}
