package edu.com.alumnosapi.service;


import edu.com.alumnosapi.dto.simple.CursoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleUpdateDTO;

import java.util.List;

public interface CursoSimpleService {

    //
    List<CursoSimpleResponseDTO> listado();

    //
    CursoSimpleResponseDTO buscarid(Integer id);

    //
    CursoSimpleResponseDTO registrar(CursoSimpleRequestDTO cursoSimpleRequestDTO);

    //
    CursoSimpleResponseDTO modificar(CursoSimpleUpdateDTO cursoSimpleUpdateDTO , Integer id);

    //eliminar

    void eliminar(Integer id);


}
