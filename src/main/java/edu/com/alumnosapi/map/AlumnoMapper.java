package edu.com.alumnosapi.map;


import edu.com.alumnosapi.dto.AlumnoRequestDTO;

import edu.com.alumnosapi.dto.AlumnoResponseDTO;
import edu.com.alumnosapi.model.Alumno;
import edu.com.alumnosapi.model.AlumnoCurso;
import edu.com.alumnosapi.model.Taller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AlumnoMapper {

    Alumno toEntity(AlumnoRequestDTO request);

    @Mapping(target = "cursos", source = "alumnoCursos", qualifiedByName = "mapCursos")
    @Mapping(target = "talleres", source = "talleres", qualifiedByName = "mapTalleres")
    AlumnoResponseDTO toResponse(Alumno alumno);

    @Named("mapCursos")
    default List<String> mapCursos(List<AlumnoCurso> alumnoCursos) {
        return alumnoCursos.stream().map(ac -> ac.getCurso().getNombre()).toList();
    }

    @Named("mapTalleres")
    default List<String> mapTalleres(List<Taller> talleres) {
        return talleres.stream().map(Taller::getNombre).toList();
    }
}