package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.ChambreDto;
import tn.esprit.tpfoyer.entity.Chambre;

@Mapper(componentModel = "spring")
public interface ChambreMapper {

    ChambreDto toDto(Chambre chambre);

    @Mapping(target = "bloc", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Chambre toEntity(ChambreDto dto);
}