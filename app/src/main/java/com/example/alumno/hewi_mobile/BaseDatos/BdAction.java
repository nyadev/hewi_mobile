package com.example.alumno.hewi_mobile.BaseDatos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by alex5 on 29/04/2018.
 */

public class BdAction {

    private SQLiteDatabase basedatos;

    public BdAction (SQLiteDatabase sql)
    {
        basedatos = sql;
    }

    //=====================INICIAR SESIÓN===========================================================
    public Boolean InicioSesion (String NombreUsuario, String PassUsuario)
    {

        Boolean resultado = false;
        Cursor cursor = basedatos.rawQuery("SELECT NOMBRE_USUARIO, PASS FROM TERAPEUTA", null);

        while (cursor.moveToNext())
        {
            if(NombreUsuario.equals(cursor.getString(0))
                    && PassUsuario.equals(cursor.getString(1)))
            {
                Log.i("NOMBRE USUARIO", cursor.getString(0));
                Log.i("CONTRASEÑA", cursor.getString(1));
                resultado = true;
            }
        }

        return resultado;
    }

    //======================REGISTRAR TERAPEUTA=====================================================
    public Boolean RegistrarTerapeuta (String [] valores)
    {
        Boolean verificacion = false;

        if(!VerificarTerapeuta(valores[3], valores[4]))
        {
            ContentValues values = new ContentValues();
            values.put("CURP", valores[3]);
            values.put("NOMBRE", valores[0]);
            values.put("APP", valores[1]);
            values.put("APM", valores[2]);
            values.put("NOMBRE_USUARIO", valores[4]);
            values.put("PASS", valores[5]);
            basedatos.insert("TERAPEUTA", null, values);
            verificacion = true;
        }

        return verificacion;
    }

    //==============================VERIFICAR EXISTENCIA TERAPEUTA==================================
    public Boolean VerificarTerapeuta(String Curp, String NombreUsuario)
    {
        Boolean resultado = false;
        Cursor cursor = basedatos.rawQuery("SELECT CURP, NOMBRE_USUARIO FROM TERAPEUTA", null);

        while (cursor.moveToNext())
        {
            if(Curp.equals(cursor.getString(0)) || NombreUsuario.equals(cursor.getString(1)))
            {
                resultado = true;
            }
        }

        return resultado;
    }

    //==============================OBTENER CURP PARA TERAPEUTA=====================================
    public String ObtenerCurp(String NombreUsuario)
    {
        Cursor cursor = basedatos.rawQuery("SELECT CURP FROM TERAPEUTA WHERE NOMBRE_USUARIO = '"
                + NombreUsuario + "'", null);
        cursor.moveToFirst();
        return cursor.getString(0);
    }

    //==============================REGISTRAR TERAPEUTA=============================================
    public Boolean RegistrarPaciente(String [] valores)
    {
        Boolean resultadoUno = false;
        Boolean resultadoFinal = false;
        Tablas tablas = new Tablas();

        Cursor cursor = basedatos.rawQuery("SELECT CURP FROM PACIENTE", null);
        while (cursor.moveToNext())
        {
            if(valores[0].equals(cursor.getString(0)))
            {
                resultadoUno = true;
            }
        }

        if(!resultadoUno)
        {
            ContentValues values = new ContentValues();
            for(int i = 0; i < valores.length; i++)
            {
                values.put(tablas.getNombreCampoPaciente()[i], valores[i]);
            }
            basedatos.insert("PACIENTE", null, values);
            resultadoFinal = true;
        }

        return resultadoFinal;
    }
}
