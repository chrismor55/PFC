package com.PFC.model;

import java.time.LocalDate;

public class registroDTO {
	 private String dni;
	    private String nombre;
	    private String apellidos;
	    private String username;
	    private String password;
	    private String email;
	    private LocalDate nacimiento; // Formato: "YYYY-MM-DD"

	    

	    // Getters y setters

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

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        this.password = password;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public LocalDate getNacimiento() {
	        return nacimiento;
	    }

	    public void setNacimiento(LocalDate nacimiento) {
	        this.nacimiento = nacimiento;
	    }
}
