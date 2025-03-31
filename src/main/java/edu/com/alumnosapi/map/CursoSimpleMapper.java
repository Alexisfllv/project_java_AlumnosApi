package edu.com.alumnosapi.map;

import edu.com.alumnosapi.dto.simple.*;
import edu.com.alumnosapi.model.Alumno;
import edu.com.alumnosapi.model.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoSimpleMapper {

    //dto
    CursoSimpleResponseDTO toCursoSimpleResponseDTO(Curso curso);
    Curso toCurso(CursoSimpleResponseDTO cursoSimpleResponseDTO);

    CursoSimpleRequestDTO toCursoSimpleRequestDTO(Curso curso);
    Curso toCurso(CursoSimpleRequestDTO cursoSimpleRequestDTO);

    CursoSimpleUpdateDTO toCursoSimpleUpdateDTO(Curso curso);
    Curso toCurso(CursoSimpleUpdateDTO toCursoSimpleUpdateDTO);

}
