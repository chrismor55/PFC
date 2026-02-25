package com.PFC.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class usuarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer id;

    @Column(name = "DNI", nullable = false, length = 9)
    private String dni;

    @Column(name = "NOMBRE", nullable = false, length = 20)
    private String nombre;

    @Column(name = "APELLIDOS", nullable = false, length = 50)
    private String apellidos;

    @Column(name = "USERNAME", nullable = false, length = 50)
    private String username;

    @Column(name = "PSW", nullable = false, length = 50)
    private String password;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "NACIMIENTO", nullable = false)
    private LocalDate nacimiento;

    @Column(name = "PUNTOS", nullable = false)
    private int puntos = 0;

    // 🔥 Nuevo sistema de progresión
    @Column(name = "NIVEL", nullable = false)
    private int nivel = 1;

    @Column(name = "EXPERIENCIA", nullable = false)
    private int experiencia = 0;

    // 🔥 Es necesario para saber cuándo subir de nivel
    @Column(name = "BATALLAS_GANADAS", nullable = false)
    private int batallasGanadas = 0;

    // Getters y Setters...

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getNacimiento() { return nacimiento; }
    public void setNacimiento(LocalDate nacimiento) { this.nacimiento = nacimiento; }

    public int getPuntos() { return puntos; }
    public void setPuntos(int puntos) { this.puntos = puntos; }

    public int getNivel() { return nivel; }
    public void setNivel(int nivel) { this.nivel = nivel; }

    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

    public int getBatallasGanadas() { return batallasGanadas; }
    public void setBatallasGanadas(int batallasGanadas) { this.batallasGanadas = batallasGanadas; }
}
