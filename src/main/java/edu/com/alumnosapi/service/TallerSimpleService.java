package edu.com.alumnosapi.service;


import edu.com.alumnosapi.dto.simple.TallerSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleUpdateDTO;
import edu.com.alumnosapi.model.Taller;

import java.util.List;

public interface TallerSimpleService {

    //
    List<TallerSimpleResponseDTO> listarTallerSimple();

    TallerSimpleResponseDTO buscarTallerSimplePorId(Integer id);

    TallerSimpleResponseDTO registrarTaller(TallerSimpleRequestDTO tallerSimpleRequestDTO);

    TallerSimpleResponseDTO actualizarTaller(TallerSimpleUpdateDTO tallerSimpleUpdateDTO , Integer id);

    void eliminarTaller(Integer id);
}
