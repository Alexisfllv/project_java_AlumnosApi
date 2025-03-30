package edu.com.alumnosapi.dto;

import java.util.List;

public record AlumnoResponseDTO(
        Integer id,
        String nombre,
        List<String> cursos,
        List<String> talleres
) {}
