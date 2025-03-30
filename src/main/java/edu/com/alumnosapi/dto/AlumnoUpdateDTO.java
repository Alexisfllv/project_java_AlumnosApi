package edu.com.alumnosapi.dto;

import java.util.List;

public record AlumnoUpdateDTO(
        String nombre,
        List<Integer> cursosIds,
        List<Integer> talleresIds
) {}
