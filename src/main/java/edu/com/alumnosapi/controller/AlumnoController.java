package edu.com.alumnosapi.controller;



import edu.com.alumnosapi.dto.AlumnoRequestDTO;
import edu.com.alumnosapi.dto.AlumnoResponseDTO;
import edu.com.alumnosapi.dto.AlumnoUpdateDTO;
import edu.com.alumnosapi.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos/global")
@RequiredArgsConstructor
public class AlumnoController {

    private final AlumnoService alumnoService;

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlumnoResponseDTO> buscarAlumno(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(alumnoService.buscarAlumnoporid(id));
    }


    //
    @GetMapping("/listar")
    public ResponseEntity<List<AlumnoResponseDTO>> listarAlumnos() {
        return ResponseEntity.status(200).body(alumnoService.listadoAlumnos());
    }

    @PostMapping("/registrar")
    public ResponseEntity<AlumnoResponseDTO> registrar(@RequestBody AlumnoRequestDTO alumnoRequest) {
        return ResponseEntity.status(201).body(alumnoService.crearAlumno(alumnoRequest));
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<AlumnoResponseDTO> modificar(@RequestBody AlumnoUpdateDTO alumnoUpdate, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(alumnoService.actualizarAlumno(id, alumnoUpdate));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        alumnoService.eliminarAlumno(id);
        return ResponseEntity.status(200).body(null);
    }
}
