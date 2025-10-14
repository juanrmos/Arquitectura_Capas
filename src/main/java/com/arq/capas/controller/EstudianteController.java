package com.arq.capas.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arq.capas.model.Estudiante;
import com.arq.capas.service.EstudianteService;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // GET: Obtener todos los estudiantes
    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerTodos() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodos();
        return ResponseEntity.ok(estudiantes);
    }

    // GET: Obtener un estudiante por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return estudianteService.obtenerPorId(id)
                .map(estudiante -> ResponseEntity.ok((Object) estudiante))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante no encontrado con id: " + id)));
    }

    // POST: Crear un nuevo estudiante
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteService.crear(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // PUT: Actualizar un estudiante existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Estudiante estudiante) {
        try {
            Estudiante estudianteActualizado = estudianteService.actualizar(id, estudiante);
            return ResponseEntity.ok(estudianteActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // DELETE: Eliminar un estudiante
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            estudianteService.eliminar(id);
            return ResponseEntity.ok(Map.of("mensaje", "Estudiante eliminado exitosamente"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // GET: Buscar por email
    @GetMapping("/email/{email}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String email) {
        return estudianteService.buscarPorEmail(email)
                .map(estudiante -> ResponseEntity.ok((Object) estudiante))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante no encontrado con email: " + email)));
    }

    // GET: Buscar por código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<?> buscarPorCodigo(@PathVariable String codigo) {
        return estudianteService.buscarPorCodigo(codigo)
                .map(estudiante -> ResponseEntity.ok((Object) estudiante))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Estudiante no encontrado con código: " + codigo)));
    }
}