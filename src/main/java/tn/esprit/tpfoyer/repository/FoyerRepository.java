package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Foyer;

import java.util.List;

public interface FoyerRepository extends JpaRepository<Foyer, Long> {

    // Find by 1 attribute
    List<Foyer> findByNomFoyer(String nomFoyer);

    // Find by 2 attributes
    List<Foyer> findByNomFoyerAndCapaciteFoyer(String nomFoyer, Long capaciteFoyer);

    // Delete by 1 attribute
    long deleteByNomFoyer(String nomFoyer);
}
