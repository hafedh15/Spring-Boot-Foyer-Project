package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.EtudiantDto;
import tn.esprit.tpfoyer.entity.Etudiant;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {

    @Mapping(source = "nomEt", target = "nomEtudiant")
    @Mapping(source = "prenomEt", target = "prenomEtudiant")
    EtudiantDto toDto(Etudiant etudiant);

    @Mapping(source = "nomEtudiant", target = "nomEt")
    @Mapping(source = "prenomEtudiant", target = "prenomEt")
    @Mapping(target = "ecole", ignore = true)
    @Mapping(target = "dateNaissance", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    Etudiant toEntity(EtudiantDto dto);
}