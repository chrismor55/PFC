package com.PFC.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.PFC.entity.usuarioEntity;
import com.PFC.model.partidaDTO;
import com.PFC.model.usuarioDTO;
import com.PFC.repo.usuarioRepo;
import com.PFC.service.juegoService;

@Controller
public class juegoController {

    @Autowired
    usuarioRepo usuariorepo;

    @Autowired
    private juegoService JuegoService;

    // ====================================================
    // CARGAR PÁGINA PRINCIPAL DEL JUEGO
    // ====================================================
    @GetMapping("/juego/juego")
    public String principal(Model modelo, @RequestParam("id_usuario") int id_usuario) {

        usuarioEntity usuario = usuariorepo.findById(id_usuario).orElse(null);

        if (usuario == null) return "redirect:/error";

        modelo.addAttribute("usuario", usuario);
        modelo.addAttribute("id_usuario", id_usuario);

        return "juego/juego";
    }

    // ====================================================
    // ACTUALIZAR PUNTOS DEL USUARIO
    // ====================================================
    public void actualizarPuntosUsuario(int id_usuario, int puntosGanados) {

        Optional<usuarioEntity> optionalUsuario = usuariorepo.findById(id_usuario);

        if (optionalUsuario.isPresent()) {
            usuarioEntity usuario = optionalUsuario.get();

            usuario.setPuntos(usuario.getPuntos() + puntosGanados);
            usuariorepo.save(usuario);

            System.out.println("✔ Puntos actualizados: " + usuario.getPuntos());
        }
    }

    // ====================================================
    // FINALIZAR PARTIDA (GUARDAR PUNTOS + HISTORIAL)
    // ====================================================
    @PostMapping("/juego/finalizar")
    @ResponseBody
    public ResponseEntity<?> finalizarPartida(@RequestBody partidaDTO partida) {

        Optional<usuarioEntity> optionalUsuario = usuariorepo.findById(partida.getIdJugador());

        if (optionalUsuario.isEmpty()) {
            System.out.println("❌ Usuario no encontrado");
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        usuarioEntity usuario = optionalUsuario.get();

        System.out.println("========================================");
        System.out.println("🏁 FINALIZANDO PARTIDA");
        System.out.println("Usuario: " + usuario.getUsername());
        System.out.println("Puntos actuales: " + usuario.getPuntos());
        System.out.println("Ganados esta partida: " + partida.getPuntosGanados());

        // Actualizar puntos
        actualizarPuntosUsuario(partida.getIdJugador(), partida.getPuntosGanados());

        // Actualizar HISTORIAL
        JuegoService.guardarBatalla(partida);

        System.out.println("✔ Historial guardado correctamente");
        System.out.println("========================================");

        return ResponseEntity.ok().build();
    }

    @PostMapping("/juego/actualizarNivel")
    @ResponseBody
    public ResponseEntity<?> actualizarNivel(@RequestBody usuarioDTO datos) {
        usuarioEntity usuario = usuariorepo.findById(datos.getId()).orElse(null);
        if (usuario == null) return ResponseEntity.badRequest().build();

        usuario.setNivel(datos.getNivel());
        usuario.setExperiencia(datos.getExperiencia());
        usuario.setPuntos(datos.getPuntos());

        usuariorepo.save(usuario);
        return ResponseEntity.ok().build();
    }


    }

