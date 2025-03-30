package edu.com.alumnosapi.service;


import edu.com.alumnosapi.dto.AlumnoRequestDTO;

import edu.com.alumnosapi.dto.AlumnoResponseDTO;
import edu.com.alumnosapi.dto.AlumnoUpdateDTO;

import java.util.List;


public interface AlumnoService {




    //listas
    List<AlumnoResponseDTO> listadoAlumnos();

    //buscar id
    AlumnoResponseDTO buscarAlumnoporid(Integer id);

    //registrado
    AlumnoResponseDTO crearAlumno(AlumnoRequestDTO alumnoRequestDTO);

    //modificar
    AlumnoResponseDTO actualizarAlumno(Integer id, AlumnoUpdateDTO alumnoUpdateDTO);

    //eliminar

    void eliminarAlumno(Integer id);
}
