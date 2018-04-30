package com.example.alumno.hewi_mobile.Entidades;

import java.io.Serializable;

/**
 * Created by alex5 on 29/04/2018.
 */

public class paciente implements Serializable {

    private String curp;
    private String nombre_paciente;
    private String app_paciente;
    private String apm_paciente;
    private String nombre_tutor;
    private String app_tutor;
    private String apm_tutor;
    private String correo_tutor;
    private String celular_tutor;

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre_paciente() {
        return nombre_paciente;
    }

    public void setNombre_paciente(String nombre_paciente) {
        this.nombre_paciente = nombre_paciente;
    }

    public String getApp_paciente() {
        return app_paciente;
    }

    public void setApp_paciente(String app_paciente) {
        this.app_paciente = app_paciente;
    }

    public String getApm_paciente() {
        return apm_paciente;
    }

    public void setApm_paciente(String apm_paciente) {
        this.apm_paciente = apm_paciente;
    }

    public String getNombre_tutor() {
        return nombre_tutor;
    }

    public void setNombre_tutor(String nombre_tutor) {
        this.nombre_tutor = nombre_tutor;
    }

    public String getApp_tutor() {
        return app_tutor;
    }

    public void setApp_tutor(String app_tutor) {
        this.app_tutor = app_tutor;
    }

    public String getApm_tutor() {
        return apm_tutor;
    }

    public void setApm_tutor(String apm_tutor) {
        this.apm_tutor = apm_tutor;
    }

    public String getCorreo_tutor() {
        return correo_tutor;
    }

    public void setCorreo_tutor(String correo_tutor) {
        this.correo_tutor = correo_tutor;
    }

    public String getCelular_tutor() {
        return celular_tutor;
    }

    public void setCelular_tutor(String celular_tutor) {
        this.celular_tutor = celular_tutor;
    }
}
