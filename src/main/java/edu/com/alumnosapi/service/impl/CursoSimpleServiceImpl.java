package edu.com.alumnosapi.service.impl;

import edu.com.alumnosapi.dto.simple.CursoSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.CursoSimpleUpdateDTO;
import edu.com.alumnosapi.exception.respuestas.RecursoNoEncontradoException;
import edu.com.alumnosapi.map.CursoSimpleMapper;
import edu.com.alumnosapi.model.Curso;
import edu.com.alumnosapi.repo.CursoRepository;
import edu.com.alumnosapi.service.CursoSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CursoSimpleServiceImpl implements CursoSimpleService {


    //repo
    private final CursoRepository cursoRepository;
    //mapper
    private final CursoSimpleMapper cursoSimpleMapper;


    @Override
    public List<CursoSimpleResponseDTO> listado() {
        List<Curso> cursos = cursoRepository.findAll();
        return cursos.stream()
                .map(curso -> cursoSimpleMapper.toCursoSimpleResponseDTO(curso))
                .collect(Collectors.toList());
    }

    @Override
    public CursoSimpleResponseDTO buscarid(Integer id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado : "+ id));
        return cursoSimpleMapper.toCursoSimpleResponseDTO(curso);
    }


    @Override
    public CursoSimpleResponseDTO registrar(CursoSimpleRequestDTO cursoSimpleRequestDTO) {
        Curso curso = cursoSimpleMapper.toCurso(cursoSimpleRequestDTO);

        //enviar nombre (dato);
        curso.setNombre(cursoSimpleRequestDTO.nombre());

        cursoRepository.save(curso);
        return cursoSimpleMapper.toCursoSimpleResponseDTO(curso);
    }

    @Override
    public CursoSimpleResponseDTO modificar(CursoSimpleUpdateDTO cursoSimpleUpdateDTO, Integer id) {
        Curso cursoexiste = cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado : "+ id));
        // insertar el nuevo nombre al curso recuperado
        cursoexiste.setNombre(cursoSimpleUpdateDTO.nombre());

        cursoRepository.save(cursoexiste);
        return cursoSimpleMapper.toCursoSimpleResponseDTO(cursoexiste);
    }

    @Override
    public void eliminar(Integer id) {
        cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Curso no encontrado : "+ id));

        cursoRepository.deleteById(id);
    }
}
