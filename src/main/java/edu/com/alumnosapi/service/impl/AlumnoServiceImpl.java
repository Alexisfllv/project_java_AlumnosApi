package edu.com.alumnosapi.service.impl;



import edu.com.alumnosapi.dto.AlumnoRequestDTO;
import edu.com.alumnosapi.dto.AlumnoResponseDTO;
import edu.com.alumnosapi.dto.AlumnoUpdateDTO;
import edu.com.alumnosapi.exception.respuestas.RecursoNoEncontradoException;
import edu.com.alumnosapi.map.AlumnoMapper;
import edu.com.alumnosapi.model.*;
import edu.com.alumnosapi.model.AlumnoCurso;
import edu.com.alumnosapi.repo.*;
import edu.com.alumnosapi.service.AlumnoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlumnoServiceImpl implements AlumnoService {


    private final AlumnoRepository alumnoRepository;
    private final CursoRepository cursoRepository;
    private final TallerRepository tallerRepository;
    private final AlumnoMapper alumnoMapper;


    @Override
    public List<AlumnoResponseDTO> listadoAlumnos() {
        List<Alumno> alumnos = alumnoRepository.findAll();
        return alumnos.stream()
                .map(alumno -> alumnoMapper.toResponse(alumno))
                .collect(Collectors.toList());
    }

    @Override
    public AlumnoResponseDTO buscarAlumnoporid(Integer id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Alumno no encontrado : " + id));

        return alumnoMapper.toResponse(alumno);
    }

    @Transactional
    @Override
    public AlumnoResponseDTO crearAlumno(AlumnoRequestDTO  alumnoRequestDTO) {

        Alumno alumno = new Alumno();
        alumno.setNombre(alumnoRequestDTO.nombre());

        //asignar cursos
        // Validar y asignar cursos
        List<Curso> cursos = alumnoRequestDTO.cursosIds().stream()
                .map(id -> cursoRepository.findById(id)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado con ID: " + id)))
                .toList();

        List<AlumnoCurso> alumnoCursos = cursos.stream()
                .map(curso -> new AlumnoCurso(null,alumno,curso,"ACTIVO"))
                .toList();
        alumno.setAlumnoCursos(alumnoCursos);

        //talleres
        List<Taller> talleres = alumnoRequestDTO.talleresIds().stream()
                .map(id -> tallerRepository.findById(id)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Taller no encontrado con ID: " + id)))
                .toList();

        alumno.setTalleres(talleres);
        alumnoRepository.save(alumno);

        return alumnoMapper.toResponse(alumno);
    }

    @Transactional
    @Override
    public AlumnoResponseDTO actualizarAlumno(Integer id, AlumnoUpdateDTO alumnoUpdateDTO) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Alumno no encontrado con ID: " + id));

        alumno.setNombre(alumnoUpdateDTO.nombre());

        // 🔹 Convertir los cursos actuales en un Set de IDs para evitar duplicados
        Set<Integer> cursosExistentes = alumno.getAlumnoCursos().stream()
                .map(ac -> ac.getCurso().getId())
                .collect(Collectors.toSet());

        // 🔹 Agregar solo los cursos que aún no están registrados
        List<AlumnoCurso> nuevosAlumnoCursos = alumnoUpdateDTO.cursosIds().stream()
                .filter(idCurso -> !cursosExistentes.contains(idCurso)) // ✅ Evita duplicados
                .map(idCurso -> {
                    Curso curso = cursoRepository.findById(idCurso)
                            .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado con ID: " + idCurso));
                    return new AlumnoCurso(null, alumno, curso, "ACTIVO");
                })
                .toList();

        alumno.getAlumnoCursos().addAll(nuevosAlumnoCursos); // ✅ Agregar nuevos sin borrar los existentes

        // 🔹 Convertir los talleres actuales en un Set de IDs
        Set<Integer> talleresExistentes = alumno.getTalleres().stream()
                .map(Taller::getId)
                .collect(Collectors.toSet());

        // 🔹 Agregar solo los talleres que no estén registrados
        List<Taller> nuevosTalleres = alumnoUpdateDTO.talleresIds().stream()
                .filter(idTaller -> !talleresExistentes.contains(idTaller)) // ✅ Evita duplicados
                .map(idTaller -> tallerRepository.findById(idTaller)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Taller no encontrado con ID: " + idTaller)))
                .toList();

        alumno.getTalleres().addAll(nuevosTalleres); // ✅ Agregar nuevos sin borrar los existentes

        alumnoRepository.save(alumno);

        return new AlumnoResponseDTO(
                alumno.getId(),
                alumno.getNombre(),
                alumno.getAlumnoCursos().stream().map(ac -> ac.getCurso().getNombre()).toList(),
                alumno.getTalleres().stream().map(Taller::getNombre).toList()
        );
    }

    @Override
    public void eliminarAlumno(Integer id) {
        if (!alumnoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Alumno no encontrado con ID: " + id);
        }
        alumnoRepository.deleteById(id);
    }


}
