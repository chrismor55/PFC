package com.PFC.model;

public class usuarioDTO {

    private Integer id;
    private String dni;
    private String nombre;
    private String apellidos;
    private String username;
    private String email;
    private String password;
    private String nacimiento; // formato ISO 8601 (ej: "2000-01-01")
    private int puntos;
    private int nivel;
    private int experiencia;
    private int batallasGanadas; // NUEVO

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNacimiento() {
        return nacimiento;
    }
    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getBatallasGanadas() {
        return batallasGanadas;
    }
    public void setBatallasGanadas(int batallasGanadas) {
        this.batallasGanadas = batallasGanadas;
    }
}
