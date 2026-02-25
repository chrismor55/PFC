package com.PFC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.PFC.entity.usuarioEntity;
import com.PFC.service.usuarioService;



@RestController
@RequestMapping("/usuarios")
public class usuarioController {
	@Autowired
    private usuarioService Usuarioservice;

    @GetMapping("/login")
    public ResponseEntity<String> iniciarsesionForm(@RequestParam("nombre") String nombre, @RequestParam("password") String password) {
        // Buscar el usuario por su nombre
        usuarioEntity usuario = Usuarioservice.encontrarPorNombre(nombre);

        // Si el usuario no existe
        if (usuario == null) {
            return ResponseEntity.ok("NO OK");
        }

        // Verificar si la contraseña es correcta
        boolean esContraseñaCorrecta = verificarContraseña(usuario, password);

        if (!esContraseñaCorrecta) {
            return ResponseEntity.ok("NO OK");
        }

        return ResponseEntity.ok(String.valueOf(usuario.getId())); // Respuesta esperada por AJAX
    }

    private boolean verificarContraseña(usuarioEntity usuario, String password) {
        // Aquí debes comparar la contraseña, por ejemplo, si está encriptada
        return usuario.getPassword().equals(password);
    }
}