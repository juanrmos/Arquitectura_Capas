package com.arq.capas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arq.capas.model.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    
    // Método personalizado para buscar por email
    Optional<Estudiante> findByEmail(String email);
    
    // Método personalizado para buscar por código
    Optional<Estudiante> findByCodigo(String codigo);
    
    // Método personalizado para verificar si existe un email
    boolean existsByEmail(String email);
    
    // Método personalizado para verificar si existe un código
    boolean existsByCodigo(String codigo);
}