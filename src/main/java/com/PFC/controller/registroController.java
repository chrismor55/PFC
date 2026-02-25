package com.PFC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.PFC.model.registroDTO;
import com.PFC.repo.usuarioRepo;
import com.PFC.service.usuarioService;

@Controller
public class registroController {

		/*Lo lleva a la ruta del formulario de registro normal*/

		@GetMapping("/registroFormulario")
		public String mostrarFormularioRegistro() {
		
		    return "sesionRegistro/registro";
		}
		@Autowired
		private usuarioService UsuarioService;
		
		@Autowired
		private usuarioRepo UsuarioRepository;

	    	/*guarda el usuario*/

		@PostMapping("/usuarios/registrar")
		public String registrarUsuario(@RequestBody registroDTO registroDTO) {
	    System.out.println("Estamos en registro");
	    System.out.println("Fecha de nacimiento recibida: " + registroDTO.getNacimiento());
	    
	    // Registrar usuario en base de datos
	    String respuesta =UsuarioService.registrarUsuario(registroDTO);
	    
	    return "sesionRegistro/index";
	}

}
