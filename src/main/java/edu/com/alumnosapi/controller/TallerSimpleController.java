package edu.com.alumnosapi.controller;

import edu.com.alumnosapi.dto.simple.TallerSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleUpdateDTO;
import edu.com.alumnosapi.service.TallerSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple/talleres")
@RequiredArgsConstructor
public class TallerSimpleController {

    private final TallerSimpleService tallerSimpleService;


    //
    @GetMapping("/listar")
    ResponseEntity<List<TallerSimpleResponseDTO>> listadoTalleresSimple() {
        return ResponseEntity.status(200).body(tallerSimpleService.listarTallerSimple());
    }

    @GetMapping("/buscar/{id}")
    ResponseEntity<TallerSimpleResponseDTO> buscarTallerSimple(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(tallerSimpleService.buscarTallerSimplePorId(id));
    }

    @PostMapping("/registrar")
    ResponseEntity<TallerSimpleResponseDTO> registrar(@RequestBody TallerSimpleRequestDTO tallerSimpleRequestDTO) {
        return ResponseEntity.status(201).body(tallerSimpleService.registrarTaller(tallerSimpleRequestDTO));
    }

    @PutMapping("/modificar/{id}")
    ResponseEntity<TallerSimpleResponseDTO> modificar(@RequestBody TallerSimpleUpdateDTO tallerSimpleUpdateDTO, @PathVariable Integer id) {
        return ResponseEntity.status(200).body(tallerSimpleService.actualizarTaller(tallerSimpleUpdateDTO,id));
    }

    @DeleteMapping("/eliminar/{id}")
    ResponseEntity<TallerSimpleResponseDTO> eliminar(@PathVariable Integer id) {
        tallerSimpleService.eliminarTaller(id);
        return ResponseEntity.status(200).build();
    }
}
