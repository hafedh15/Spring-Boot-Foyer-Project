package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.UniversiteDto;
import tn.esprit.tpfoyer.entity.Universite;

@Mapper(componentModel = "spring")
public interface UniversiteMapper {

    UniversiteDto toDto(Universite universite);

    @Mapping(target = "adresse", ignore = true)
    @Mapping(target = "foyer", ignore = true)
    Universite toEntity(UniversiteDto dto);
}