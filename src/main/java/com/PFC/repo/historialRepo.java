package com.PFC.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PFC.entity.historialEntity;

import java.util.List;

@Repository
public interface historialRepo extends JpaRepository<historialEntity, Integer> {

}
