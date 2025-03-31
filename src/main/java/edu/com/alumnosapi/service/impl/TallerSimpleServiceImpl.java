package edu.com.alumnosapi.service.impl;

import edu.com.alumnosapi.dto.simple.TallerSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleUpdateDTO;
import edu.com.alumnosapi.exception.respuestas.RecursoNoEncontradoException;
import edu.com.alumnosapi.map.TallerSimpleMapper;
import edu.com.alumnosapi.model.Taller;
import edu.com.alumnosapi.repo.TallerRepository;
import edu.com.alumnosapi.service.TallerSimpleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TallerSimpleServiceImpl implements TallerSimpleService {

    //ioc
    private final TallerSimpleMapper tallerSimpleMapper;
    private final TallerRepository tallerRepository;


    @Override
    public List<TallerSimpleResponseDTO> listarTallerSimple() {
        List<Taller> listadoTalleres = tallerRepository.findAll();

        return listadoTalleres.stream()
                .map(taller -> tallerSimpleMapper.toTallerSimpleResponseDTO(taller))
                .collect(Collectors.toList());

    }

    @Override
    public TallerSimpleResponseDTO buscarTallerSimplePorId(Integer id) {
        Taller taller = tallerRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("El taller no existe : " + id));

        return tallerSimpleMapper.toTallerSimpleResponseDTO(taller);
    }

    @Override
    public TallerSimpleResponseDTO registrarTaller(TallerSimpleRequestDTO tallerSimpleRequestDTO) {
        Taller taller = tallerSimpleMapper.toTaller(tallerSimpleRequestDTO);

        //enviar datos?
        taller.setNombre(taller.getNombre());

        tallerRepository.save(taller);

        return tallerSimpleMapper.toTallerSimpleResponseDTO(taller);
    }

    @Override
    public TallerSimpleResponseDTO actualizarTaller(TallerSimpleUpdateDTO tallerSimpleUpdateDTO, Integer id) {
        Taller tallerExiste = tallerRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("El taller no existe: " + id));

        //enviar nombre de la update
        tallerExiste.setNombre(tallerSimpleUpdateDTO.nombre());
        tallerRepository.save(tallerExiste);

        return tallerSimpleMapper.toTallerSimpleResponseDTO(tallerExiste);
    }

    @Override
    public void eliminarTaller(Integer id) {
        Taller tallerExiste = tallerRepository.findById(id)
                .orElseThrow(()-> new RecursoNoEncontradoException("El taller no existe: " + id));
        tallerRepository.delete(tallerExiste);
    }
}
