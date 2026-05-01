package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.dto.ReservationDto;

import java.util.List;

public interface IReservationService {

    List<ReservationDto> retrieveAllReservations();

    ReservationDto addReservation(ReservationDto r);

    ReservationDto updateReservation(ReservationDto r);

    ReservationDto retrieveReservation(String idReservation);

    void removeReservation(String    idReservation);
}