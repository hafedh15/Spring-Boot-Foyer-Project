package tn.esprit.tpfoyer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tn.esprit.tpfoyer.dto.ReservationDto;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source = "anneeUniversitaire", target = "anneUniversitaire", dateFormat = "yyyy-MM-dd")
    ReservationDto toDto(Reservation reservation);

    @Mapping(source = "anneUniversitaire", target = "anneeUniversitaire", dateFormat = "yyyy-MM-dd")
    Reservation toEntity(ReservationDto dto);

    List<ReservationDto> toDtoList(List<Reservation> reservations);
}