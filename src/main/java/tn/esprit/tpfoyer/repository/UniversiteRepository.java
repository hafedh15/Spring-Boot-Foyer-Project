package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Universite;

import java.util.List;

public interface UniversiteRepository extends JpaRepository<Universite, Long> {

    List<Universite> findByNomUniversite(String nomUniversite);

    List<Universite> findByNomUniversiteAndAdresse(String nomUniversite, String adresse);

    long deleteByAdresse(String adresse);
}
