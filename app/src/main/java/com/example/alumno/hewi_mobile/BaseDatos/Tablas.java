package com.example.alumno.hewi_mobile.BaseDatos;

/**
 * Created by alex5 on 29/04/2018.
 */

public class Tablas {

    //===========================CREACIÓN TABLA TERAPEUTA===========================================
    private String CamposTerapetua = "CURP TEXT, NOMBRE TEXT, APP TEXT, APM TEXT, NOMBRE_USUARIO TEXT, PASS TEXT";

    private String TablaTerapeuta =
            "CREATE TABLE TERAPEUTA (" + CamposTerapetua + ")";

    public String getTablaTerapeuta() {
        return TablaTerapeuta;
    }
    //===========================INSERTAR TERAPEUTA DE EJEMPLO======================================

    private String InfoTera = "'LOEA000516HDFPSNA2','ANDRES','LOPEZ','ESQUIVEL','andres12','12'";
    private String CamposTera = "CURP, NOMBRE, APP, APM, NOMBRE_USUARIO , PASS";

    private String InsertarTera = "INSERT INTO TERAPEUTA (" +CamposTera+ ") VALUES (" + InfoTera + ")";

    public String getInsertarTera() {
        return InsertarTera;
    }

    //===========================CREACIÓN TABLA PACIENTE============================================
    private String CamposPaciente = "CURP TEXT, NOMBRE TEXT, APP TEXT, APM TEXT, NOMBRE_TUTOR TEXT, APP_TUTOR TEXT," +
            "APM_TUTOR TEXT, CORREO_TUTOR TEXT, CELULAR_TUTOR TEXT, CURP_TERAPEUTA TEXT";

    private String [] NombreCampoPaciente = {"CURP", "NOMBRE", "APP", "APM", "NOMBRE_TUTOR", "APP_TUTOR",
    "APM_TUTOR", "CORREO_TUTOR", "CELULAR_TUTOR", "CURP_TERAPEUTA"};

    private String TablaPaciente = "CREATE TABLE PACIENTE (" + CamposPaciente + ")";

    public String getTablaPaciente() {
        return TablaPaciente;
    }

    public String[] getNombreCampoPaciente() {
        return NombreCampoPaciente;
    }
}

