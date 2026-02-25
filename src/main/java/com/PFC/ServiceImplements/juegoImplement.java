package com.PFC.ServiceImplements;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PFC.entity.historialEntity;
import com.PFC.entity.usuarioEntity;
import com.PFC.model.partidaDTO;
import com.PFC.repo.historialRepo;
import com.PFC.repo.usuarioRepo;
import com.PFC.service.juegoService;
import com.PFC.service.usuarioService;

@Service
public class juegoImplement implements juegoService {

	@Autowired
	private usuarioRepo UsuarioRepository;
	
	@Autowired
	private historialRepo historialRepository;
	
	public void guardarBatalla(partidaDTO partida) {
	    Optional<usuarioEntity> optionalUsuario = UsuarioRepository.findById(partida.getIdJugador());

	    if (optionalUsuario.isPresent()) {
	        usuarioEntity usuario = optionalUsuario.get();

	        // Actualizar puntos del usuario
	        int nuevosPuntos = usuario.getPuntos() + partida.getPuntosGanados();
	        usuario.setPuntos(nuevosPuntos);
	        UsuarioRepository.save(usuario);

	        // Guardar historial
	        historialEntity historial = new historialEntity();
	        historial.setJugador(usuario);
	        historial.setPuntosGanados(partida.getPuntosGanados());
	        historial.setVidaFinalJugador(partida.getVidaFinalJugador());
	        historial.setVidaFinalOponente(partida.getVidaFinalOponente());
	        historial.setFechaJuego(LocalDateTime.now());

	        historialRepository.save(historial);
	    }
	}

}
