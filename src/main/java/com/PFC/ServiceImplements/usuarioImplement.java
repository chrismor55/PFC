package com.PFC.ServiceImplements;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.PFC.entity.usuarioEntity;
import com.PFC.model.registroDTO;
import com.PFC.model.usuarioDTO;
import com.PFC.repo.usuarioRepo;
import com.PFC.service.usuarioService;


@Service
@Transactional
public class usuarioImplement implements usuarioService {
	
	@Autowired
	private usuarioRepo UsuarioRepository;

	@Override
	public String registrarUsuario(registroDTO registroDTO) {

		if(encontrarPorNombre(registroDTO.getUsername()) != null){
			System.out.println("el username ya esta en uso");
			return "/";
		}else{

        usuarioEntity usuario = new usuarioEntity();
		usuario.setDni(registroDTO.getDni());
		usuario.setNombre(registroDTO.getNombre());
		usuario.setApellidos(registroDTO.getApellidos());
		usuario.setUsername(registroDTO.getUsername());
		usuario.setPassword(registroDTO.getPassword());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setNacimiento(registroDTO.getNacimiento());
		usuario.setPuntos(0);
		UsuarioRepository.save(usuario);

		return "OK";
	}
	}

	@Override
	public usuarioEntity encontrarPorNombre(String nombre) {
	    Optional<usuarioEntity> optional = UsuarioRepository.findByUsername(nombre);
	    return optional.orElse(null);  // Devuelve null si no lo encuentra
	}

	@Override
	public usuarioDTO validadUsuario(usuarioEntity nombre, String pasword) {
		if(nombre.getPassword().equals(pasword)) {
			usuarioDTO usuario = new usuarioDTO();
			usuario.setNombre(nombre.getNombre());
			usuario.setPassword(nombre.getPassword());
			
			return usuario;
		}else {
			return null;			
		}
	}


}
