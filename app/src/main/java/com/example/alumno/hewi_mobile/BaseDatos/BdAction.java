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

    //==============================MODIFICA PACIENTE===============================================
    public Boolean ModificarPaciente(String [] Variables)
    {
        Boolean resultadoFinal = false;
        Boolean verificarExistencia = false;
        Tablas tablas = new Tablas();
        Cursor cursor = basedatos.rawQuery("SELECT CURP FROM PACIENTE", null);
        while(cursor.moveToNext())
        {
            Log.i("Curp del paciente", cursor.getString(0));
            if(Variables[0].equals(cursor.getString(0))){verificarExistencia = true;}
        }
        if(verificarExistencia)
        {
            ContentValues values = new ContentValues();
            for(int i = 0; i < Variables.length-1; i++)
            {
                values.put(tablas.getNombreCampoPaciente()[i], Variables[i]);
            }
            basedatos.update("PACIENTE", values, "CURP = '" + Variables[0] + "'", null);
            resultadoFinal = true;
        }
        Log.i("Curp del paciente2", Variables[0]);
        Log.i("verificarExistencia", verificarExistencia.toString());
        Log.i("resultadoFinal", resultadoFinal.toString());
        return resultadoFinal;
    }

    //==============================REGISTRA CITA===================================================
    public Boolean RegistraCita(String [] Valores)
    {
        Boolean verificacion = false;
        Tablas tabla = new Tablas();
        if(VerificarPaciente(Valores[0], Valores[1]))
        {

            if(VerificarFechaCita(Valores))
            {
                ContentValues values = new ContentValues();
                for (int i = 0; i < Valores.length; i++)
                {
                    values.put(tabla.getNombreCampoCita()[i], Valores[i]);
                }
                basedatos.insert("CITAS", null, values);
                ConsultarCitas(Valores[0], Valores[1]);
                verificacion = true;
            }

        }
        return verificacion;
    }

    //=============================VERIFICAR EXISTENCIA PACIENTE====================================

    public Boolean VerificarPaciente(String CurpPaciente, String CurpTerapeuta)
    {
        Cursor cursor = basedatos.rawQuery("SELECT CURP, CURP_TERAPEUTA FROM PACIENTE", null);
        Boolean verificacion = false;

        while(cursor.moveToNext())
        {
            if(cursor.getString(0).equals(CurpPaciente) && cursor.getString(1).equals(CurpTerapeuta))
                verificacion = true;
        }

        return verificacion;
    }

    public Boolean VerificarFechaCita(String [] Valores)
    {
        Boolean verificacion = true;
        Cursor cursor = basedatos.rawQuery("SELECT FECHA, HORA_INICIO, HORA_FIN FROM CITAS", null);
        while(cursor.moveToNext())
        {
            if(cursor.getString(0).equals(Valores[2])&& (cursor.getString(1).equals(Valores[3])||
            cursor.getString(2).equals(Valores[4])))
                verificacion = false;

        }

        return verificacion;
    }

    public void ConsultarCitas (String CurpPaciente, String CurpTerapeuta)
    {
        String [] citas = new String [4];
        Cursor cursor = basedatos.rawQuery("SELECT * FROM CITAS", null);

        while (cursor.moveToNext())
        {
            if(cursor.getString(0).equals(CurpPaciente) && cursor.getString(1).equals(CurpTerapeuta))
            {
                Log.i("CURP PACIENTE", cursor.getString(0));
                Log.i("CURP TERAPEUTA", cursor.getString(1));
                Log.i("FECHA", cursor.getString(2));
                Log.i("HORA", cursor.getString(3));
            }
        }
    }

}
