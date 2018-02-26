package com.example.alumno.hewi_mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private Layout SeccionDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActionBar().hide();

    }

    public void Formulario(View view) {
        Intent Iniciosesion = new Intent(this, InicioSesion.class);
        startActivity(Iniciosesion);
    }
}
