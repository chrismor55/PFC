package com.PFC.ServiceImplements;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.PFC.entity.historialEntity;
import com.PFC.entity.usuarioEntity;
import com.PFC.repo.historialRepo;
import com.PFC.repo.usuarioRepo;
import com.PFC.service.historialService;

import jakarta.transaction.Transactional;

@Service
public class historialImplement implements historialService {

	@Autowired
    historialRepo HistorialRepo;

    @Autowired
    usuarioRepo UsuarioRepo;

    @Override
    @Transactional
    public int add_historial(int id_usuario, int puntosGanados, int vidaJugador, int vidaOponente) {
        
        historialEntity registro = new historialEntity();

        usuarioEntity jugador = UsuarioRepo.findById(id_usuario).orElseThrow();

        registro.setJugador(jugador);
        registro.setPuntosGanados(puntosGanados);
        registro.setVidaFinalJugador(vidaJugador);
        registro.setVidaFinalOponente(vidaOponente);
        registro.setFechaJuego(LocalDateTime.now());

        HistorialRepo.save(registro);

        jugador.setPuntos(jugador.getPuntos() + puntosGanados);
        UsuarioRepo.save(jugador);

        return registro.getId();
    }
}