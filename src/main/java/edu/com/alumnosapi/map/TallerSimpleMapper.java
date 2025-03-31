package edu.com.alumnosapi.map;


import edu.com.alumnosapi.dto.simple.TallerSimpleRequestDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleResponseDTO;
import edu.com.alumnosapi.dto.simple.TallerSimpleUpdateDTO;
import edu.com.alumnosapi.model.Taller;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TallerSimpleMapper {

    TallerSimpleResponseDTO toTallerSimpleResponseDTO(Taller taller);
    Taller toTaller(TallerSimpleResponseDTO tallerSimpleResponseDTO);

    TallerSimpleRequestDTO toTallerSimpleRequestDTO(Taller taller);
    Taller toTaller(TallerSimpleRequestDTO tallerSimpleRequestDTO);

    TallerSimpleUpdateDTO toTallerSimpleUpdateDTO(Taller taller);
    Taller toTaller(TallerSimpleUpdateDTO tallerSimpleUpdateDTO);

}
