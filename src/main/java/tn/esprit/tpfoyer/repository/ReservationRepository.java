package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {

    List<Reservation> findByEstValide(boolean estValide);

    List<Reservation> findByEstValideAndAnneeUniversitaire(boolean estValide, Date anneeUniversitaire);

    long deleteByEstValide(boolean estValide);
}
