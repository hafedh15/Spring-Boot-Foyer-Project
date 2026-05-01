package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Etudiant;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByEcole(String ecole);

    List<Etudiant> findByNomEtAndPrenomEt(String nomEt, String prenomEt);

    long deleteByCin(Long cin);
}
