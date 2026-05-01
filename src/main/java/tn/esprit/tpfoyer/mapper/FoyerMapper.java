package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.FoyerDto;
import tn.esprit.tpfoyer.entity.Foyer;
import java.util.List;

@Mapper(componentModel = "spring")
public interface FoyerMapper {

    // Keep these (with the mappings)
    @Mapping(source = "capaciteFoyer", target = "capacite")
    FoyerDto toDto(Foyer foyer);

    @Mapping(source = "capacite", target = "capaciteFoyer")
    Foyer toEntity(FoyerDto dto);

    // REMOVE the duplicate toDto and toEntity methods that were here

    // List mappings are fine
    List<FoyerDto> toDtoList(List<Foyer> foyers);
    List<Foyer> toEntityList(List<FoyerDto> dtos);
}