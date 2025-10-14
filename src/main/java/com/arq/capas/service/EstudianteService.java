package com.arq.capas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arq.capas.model.Estudiante;
import com.arq.capas.repository.EstudianteRepository;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    // Obtener un estudiante por ID
    public Optional<Estudiante> obtenerPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    // Crear un nuevo estudiante
    public Estudiante crear(Estudiante estudiante) {
        // Validar que el email no exista
        if (estudianteRepository.existsByEmail(estudiante.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        // Validar que el código no exista
        if (estudianteRepository.existsByCodigo(estudiante.getCodigo())) {
            throw new RuntimeException("El código ya está registrado");
        }
        
        return estudianteRepository.save(estudiante);
    }

    // Actualizar un estudiante existente
    public Estudiante actualizar(Long id, Estudiante estudiante) {
        return estudianteRepository.findById(id)
                .map(estudianteExistente -> {
                    // Validar email si cambió
                    if (!estudianteExistente.getEmail().equals(estudiante.getEmail()) 
                            && estudianteRepository.existsByEmail(estudiante.getEmail())) {
                        throw new RuntimeException("El email ya está registrado");
                    }
                    
                    // Validar código si cambió
                    if (!estudianteExistente.getCodigo().equals(estudiante.getCodigo()) 
                            && estudianteRepository.existsByCodigo(estudiante.getCodigo())) {
                        throw new RuntimeException("El código ya está registrado");
                    }
                    
                    estudianteExistente.setNombre(estudiante.getNombre());
                    estudianteExistente.setApellido(estudiante.getApellido());
                    estudianteExistente.setEmail(estudiante.getEmail());
                    estudianteExistente.setCodigo(estudiante.getCodigo());
                    estudianteExistente.setCarrera(estudiante.getCarrera());
                    estudianteExistente.setSemestre(estudiante.getSemestre());
                    return estudianteRepository.save(estudianteExistente);
                })
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con id: " + id));
    }

    // Eliminar un estudiante
    public void eliminar(Long id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con id: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    // Buscar por email
    public Optional<Estudiante> buscarPorEmail(String email) {
        return estudianteRepository.findByEmail(email);
    }

    // Buscar por código
    public Optional<Estudiante> buscarPorCodigo(String codigo) {
        return estudianteRepository.findByCodigo(codigo);
    }
}