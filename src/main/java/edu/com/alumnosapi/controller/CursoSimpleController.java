package edu.com.alumnosapi.controller;


import edu.com.alumnosapi.dto.simple.AlumnoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleUpdateDTO;
import edu.com.alumnosapi.service.CursoSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/simple/cursos")
public class CursoSimpleController {

    private final CursoSimpleService cursoSimpleService;

    @GetMapping("/listar")
    public ResponseEntity<List<CursoSimpleResponseDTO>> listar() {
        return ResponseEntity.status(200).body(cursoSimpleService.listado());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<CursoSimpleResponseDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(cursoSimpleService.buscarid(id));
    }

    @PostMapping("/registrar")
    public ResponseEntity<CursoSimpleResponseDTO> registrar(@RequestBody CursoSimpleRequestDTO cursoSimpleRequestDTO) {
        return ResponseEntity.status(201).body(cursoSimpleService.registrar(cursoSimpleRequestDTO));
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<CursoSimpleResponseDTO> modificar(@RequestBody CursoSimpleUpdateDTO cursoSimpleUpdateDTO, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(cursoSimpleService.modificar(cursoSimpleUpdateDTO,id));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<CursoSimpleResponseDTO> eliminar(@PathVariable Integer id) {
        cursoSimpleService.eliminar(id);
        return ResponseEntity.status(200).build();
    }

}
