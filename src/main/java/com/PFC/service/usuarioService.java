package com.PFC.service;

import com.PFC.entity.usuarioEntity;
import com.PFC.model.registroDTO;
import com.PFC.model.usuarioDTO;

public interface usuarioService {
	String registrarUsuario(registroDTO registroDTO);
	usuarioEntity encontrarPorNombre(String nombre);
	usuarioDTO validadUsuario (usuarioEntity nombre,String pasword);
}
