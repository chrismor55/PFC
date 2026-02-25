package com.PFC.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PFC.entity.usuarioEntity;
import com.PFC.model.usuarioDTO;

import java.util.Optional;

@Repository
public interface usuarioRepo extends JpaRepository<usuarioEntity, Integer> {

	Optional<usuarioEntity> findByNombre(String nombre);
    Optional<usuarioEntity> findByUsername(String username);
    static Optional<usuarioDTO> findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	void save(usuarioDTO usuario);
}

