package com.woow.tlcoperador.model;

public class Operadores_sin_validar {
    //Campos de la base de datos
    private String CI_operador;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String Mail;
    private String Password;
    private Boolean Estado;
    private String Tipo_operador;
    private String Nombre_ubicacion;
    private String Hora_inicio_trabajo;
    private String Hora_fin_trabajo;

    //Constructor
    public Operadores_sin_validar() {
    }

    public String getCI_operador() {
        return CI_operador;
    }

    public void setCI_operador(String CI_operador) {
        this.CI_operador = CI_operador;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getEstado() {
        return Estado;
    }

    public void setEstado(Boolean estado) {
        Estado = estado;
    }

    public String getTipo_operador() {
        return Tipo_operador;
    }

    public void setTipo_operador(String tipo_operador) {
        Tipo_operador = tipo_operador;
    }

    public String getNombre_ubicacion() {
        return Nombre_ubicacion;
    }

    public void setNombre_ubicacion(String nombre_ubicacion) {
        Nombre_ubicacion = nombre_ubicacion;
    }

    public String getHora_inicio_trabajo() {
        return Hora_inicio_trabajo;
    }

    public void setHora_inicio_trabajo(String hora_inicio_trabajo) {
        Hora_inicio_trabajo = hora_inicio_trabajo;
    }

    public String getHora_fin_trabajo() {
        return Hora_fin_trabajo;
    }

    public void setHora_fin_trabajo(String hora_fin_trabajo) {
        Hora_fin_trabajo = hora_fin_trabajo;
    }

    //ToString
    @Override
    public String toString() {
        return Nombre;
    }

}
