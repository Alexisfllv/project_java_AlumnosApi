package edu.com.alumnosapi.controller;


import edu.com.alumnosapi.dto.AlumnoRequest;
import edu.com.alumnosapi.dto.AlumnoResponse;
import edu.com.alumnosapi.dto.AlumnoUpdate;
import edu.com.alumnosapi.model.Alumno;
import edu.com.alumnosapi.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlumnoResponse> buscarAlumno(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(alumnoService.buscarAlumnoporid(id));
    }


    //
    @GetMapping("/listar")
    public ResponseEntity<List<AlumnoResponse>> listarAlumnos() {
        return ResponseEntity.status(200).body(alumnoService.listadoAlumnos());
    }

    @PostMapping("/registrar")
    public ResponseEntity<AlumnoResponse> registrar(@RequestBody AlumnoRequest alumnoRequest) {
        return ResponseEntity.status(201).body(alumnoService.crearAlumno(alumnoRequest));
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<AlumnoResponse> modificar(@RequestBody AlumnoUpdate alumnoUpdate, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(alumnoService.actualizarAlumno(id, alumnoUpdate));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        alumnoService.eliminarAlumno(id);
        return ResponseEntity.status(200).body(null);
    }
}
