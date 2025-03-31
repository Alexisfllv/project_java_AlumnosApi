package edu.com.alumnosapi.controller;

import edu.com.alumnosapi.dto.simple.AlumnoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleUpdateDTO;
import edu.com.alumnosapi.service.AlumnoSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple/alumnos")
@RequiredArgsConstructor
public class AlumnoSimpleController {

    //service ioc
    private final AlumnoSimpleService alumnoSimpleService;

    @GetMapping("/listar")
    public ResponseEntity<List<AlumnoSimpleResponseDTO>> listar() {
        return ResponseEntity.status(200).body(alumnoSimpleService.listado());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlumnoSimpleResponseDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(alumnoSimpleService.buscarid(id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<AlumnoSimpleResponseDTO> registrar(@RequestBody AlumnoSimpleRequestDTO alumnoSimpleRequestDTO) {
        return ResponseEntity.status(201).body(alumnoSimpleService.registrar(alumnoSimpleRequestDTO));
    }

    @PutMapping("/modificar/{id}")
    public  ResponseEntity<AlumnoSimpleResponseDTO> modificar (@RequestBody AlumnoSimpleUpdateDTO alumnoSimpleUpdateDTO, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(alumnoSimpleService.modificar(alumnoSimpleUpdateDTO, id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        alumnoSimpleService.eliminar(id);
        return ResponseEntity.status(200).body(null);
    }
}
