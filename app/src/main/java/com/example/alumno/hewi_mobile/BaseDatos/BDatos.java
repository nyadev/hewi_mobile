package com.example.alumno.hewi_mobile.BaseDatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.alumno.hewi_mobile.InicioSesion;
import com.example.alumno.hewi_mobile.RegistroUsuario.registro_usuario;

/**
 * Created by alex5 on 29/04/2018.
 */

public class BDatos extends SQLiteOpenHelper {

    private Tablas Objeto_tablas;

    public BDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Objeto_tablas = new Tablas();
        db.execSQL(Objeto_tablas.getTablaTerapeuta());
        db.execSQL(Objeto_tablas.getTablaPaciente());
        db.execSQL(Objeto_tablas.getInsertarTera());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP DATABASE IF EXISTS HEWI");
    }
}
