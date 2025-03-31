package edu.com.alumnosapi.map;

import edu.com.alumnosapi.dto.simple.AlumnoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleUpdateDTO;
import edu.com.alumnosapi.model.Alumno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlumnoSimpleMapper {

    //dto
    AlumnoSimpleResponseDTO toAlumnoSimpleResponseDTO(Alumno alumno);
    Alumno toAlumno(AlumnoSimpleResponseDTO alumnoSimpleResponseDTO);

    AlumnoSimpleRequestDTO toAlumnoSimpleRequestDTO(Alumno alumno);
    Alumno toAlumno(AlumnoSimpleRequestDTO toAlumnoSimpleRequestDTO);

    AlumnoSimpleUpdateDTO toAlumnoSimpleUpdateDTO(Alumno alumno);
    Alumno toAlumno(AlumnoSimpleUpdateDTO toAlumnoSimpleUpdateDTO);

}
