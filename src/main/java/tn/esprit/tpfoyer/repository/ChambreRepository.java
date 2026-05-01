package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;

import java.util.List;

public interface ChambreRepository extends JpaRepository<Chambre, Long> {

    List<Chambre> findByNumeroChambre(Long numeroChambre);

    List<Chambre> findByNumeroChambreAndTypeC(Long numeroChambre, TypeChambre typeC);

    long deleteByNumeroChambre(Long numeroChambre);
}
