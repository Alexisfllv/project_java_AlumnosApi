package edu.com.alumnosapi.service;


import edu.com.alumnosapi.dto.simple.AlumnoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleUpdateDTO;

import java.util.List;

public interface AlumnoSimpleService {

    //
    List<AlumnoSimpleResponseDTO> listado();

    //
    AlumnoSimpleResponseDTO buscarid(Integer id);

    //
    AlumnoSimpleResponseDTO registrar(AlumnoSimpleRequestDTO alumnoSimpleRequestDTO);

    //
    AlumnoSimpleResponseDTO modificar(AlumnoSimpleUpdateDTO alumnoSimpleUpdateDTO , Integer id);

    //eliminar

    void eliminar(Integer id);


}
