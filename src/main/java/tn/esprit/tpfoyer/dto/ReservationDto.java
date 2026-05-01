package tn.esprit.tpfoyer.dto;

import lombok.Data;

@Data
public class ReservationDto {

    private Long idReservation;
    private String anneUniversitaire;
    private Boolean estValide;
}