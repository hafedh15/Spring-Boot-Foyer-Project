package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.ReservationDto;
import tn.esprit.tpfoyer.entity.Reservation;
import tn.esprit.tpfoyer.mapper.ReservationMapper;
import tn.esprit.tpfoyer.repository.ReservationRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Override
    public List<ReservationDto> retrieveAllReservations() {

        List<Reservation> reservations = reservationRepository.findAll();

        return reservationMapper.toDtoList(reservations);
    }

    @Override
    public ReservationDto addReservation(ReservationDto r) {

        Reservation entity = reservationMapper.toEntity(r);

        Reservation savedEntity = reservationRepository.save(entity);

        return reservationMapper.toDto(savedEntity);
    }

    @Override
    public ReservationDto updateReservation(ReservationDto r) {

        Reservation entity = reservationMapper.toEntity(r);

        Reservation updatedEntity = reservationRepository.save(entity);

        return reservationMapper.toDto(updatedEntity);
    }

    @Override
    public ReservationDto retrieveReservation(String idReservation) {

        Reservation reservation = reservationRepository.findById(idReservation)
                .orElse(null);

        return reservationMapper.toDto(reservation);
    }

    @Override
    public void removeReservation(String idReservation) {

        reservationRepository.deleteById(idReservation);
    }
}