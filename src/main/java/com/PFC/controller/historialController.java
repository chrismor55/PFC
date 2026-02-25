package com.PFC.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.PFC.entity.historialEntity;
import com.PFC.entity.usuarioEntity;
import com.PFC.model.partidaDTO;
import com.PFC.repo.historialRepo;
import com.PFC.repo.usuarioRepo;
import com.PFC.service.historialService;

@RestController
@RequestMapping("/partida")
public class historialController {

	@Autowired
	private usuarioRepo usuariorepo;

	@Autowired
	private historialRepo historialRepo;

	public void guardarBatalla(int id_usuario, int puntosGanados, int vidaFinalJugador, int vidaFinalOponente) {
	    Optional<usuarioEntity> optionalUsuario = usuariorepo.findById(id_usuario);

	    if (optionalUsuario.isPresent()) {
	        usuarioEntity usuario = optionalUsuario.get();

	        historialEntity historial = new historialEntity();
	        historial.setJugador(usuario);  // Aquí usas el objeto, no solo el ID
	        historial.setPuntosGanados(puntosGanados);
	        historial.setVidaFinalJugador(vidaFinalJugador);
	        historial.setVidaFinalOponente(vidaFinalOponente);
	        historial.setFechaJuego(LocalDateTime.now());

	        historialRepo.save(historial);
	    }
	}


}