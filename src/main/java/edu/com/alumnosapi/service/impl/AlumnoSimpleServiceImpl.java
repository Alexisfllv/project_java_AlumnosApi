package edu.com.alumnosapi.service.impl;


import edu.com.alumnosapi.dto.simple.AlumnoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.AlumnoSimpleUpdateDTO;
import edu.com.alumnosapi.exception.respuestas.RecursoNoEncontradoException;
import edu.com.alumnosapi.map.AlumnoSimpleMapper;
import edu.com.alumnosapi.model.Alumno;
import edu.com.alumnosapi.repo.AlumnoRepository;
import edu.com.alumnosapi.service.AlumnoSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoSimpleServiceImpl implements AlumnoSimpleService {

    //ioc
    private final AlumnoRepository alumnoRepository;
    private final AlumnoSimpleMapper alumnoSimpleMapper;


    @Override
    public List<AlumnoSimpleResponseDTO> listado() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream()
                        //alumno -> alumnoSimpleMapper.toAlumnoSimpleResponseDTO(alumno)
                .map(alumnoSimpleMapper::toAlumnoSimpleResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoSimpleResponseDTO buscarid(Integer id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El recurso no existe : " +id));
        return alumnoSimpleMapper.toAlumnoSimpleResponseDTO(alumno);

    }

    @Override
    public AlumnoSimpleResponseDTO registrar(AlumnoSimpleRequestDTO alumnoSimpleRequestDTO) {
        Alumno alumno =  alumnoRepository.save(alumnoSimpleMapper.toAlumno(alumnoSimpleRequestDTO));

        //dato por defecto
        alumno.setNombre(alumno.getNombre());

        return alumnoSimpleMapper.toAlumnoSimpleResponseDTO(alumno);
    }

    @Override
    public AlumnoSimpleResponseDTO modificar(AlumnoSimpleUpdateDTO alumnoSimpleUpdateDTO, Integer id) {

        Alumno alumnoExistente = alumnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El recurso no existe : " +id));

        //enviar nombre (nombre recuperado)
        alumnoExistente.setNombre(alumnoSimpleUpdateDTO.nombre());

        alumnoExistente = alumnoRepository.save(alumnoExistente);
        return alumnoSimpleMapper.toAlumnoSimpleResponseDTO(alumnoExistente);


    }

    @Override
    public void eliminar(Integer id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("El recurso no existe : " +id));
        alumnoRepository.delete(alumno);
    }

}
