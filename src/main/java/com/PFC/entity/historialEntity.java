package com.PFC.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "HISTORIAL_JUEGO")
public class historialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JUEGO")
    private Integer id;

    @Column(name = "FECHA_JUEGO", nullable = false)
    private LocalDateTime fechaJuego;

    @ManyToOne
    @JoinColumn(name = "ID_JUGADOR", nullable = false)
    private usuarioEntity jugador;

    @Column(name = "PUNTOS_GANADOS", nullable = false)
    private int puntosGanados;

    @Column(name = "VIDA_FINAL_JUGADOR", nullable = false)
    private int vidaFinalJugador;

    @Column(name = "VIDA_FINAL_OPONENTE", nullable = false)
    private int vidaFinalOponente;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaJuego() {
		return fechaJuego;
	}

	public void setFechaJuego(LocalDateTime fechaJuego) {
		this.fechaJuego = fechaJuego;
	}

	public usuarioEntity getJugador() {
		return jugador;
	}

	public void setJugador(usuarioEntity jugador) {
		this.jugador = jugador;
	}

	public int getPuntosGanados() {
		return puntosGanados;
	}

	public void setPuntosGanados(int puntosGanados) {
		this.puntosGanados = puntosGanados;
	}

	public int getVidaFinalJugador() {
		return vidaFinalJugador;
	}

	public void setVidaFinalJugador(int vidaFinalJugador) {
		this.vidaFinalJugador = vidaFinalJugador;
	}

	public int getVidaFinalOponente() {
		return vidaFinalOponente;
	}

	public void setVidaFinalOponente(int vidaFinalOponente) {
		this.vidaFinalOponente = vidaFinalOponente;
	}

    
}
