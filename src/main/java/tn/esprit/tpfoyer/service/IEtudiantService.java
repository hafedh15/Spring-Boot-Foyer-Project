package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.dto.EtudiantDto;

import java.util.List;

public interface IEtudiantService {

    List<EtudiantDto> retrieveAllEtudiants();

    EtudiantDto addEtudiant(EtudiantDto e);

    EtudiantDto updateEtudiant(EtudiantDto e);

    EtudiantDto retrieveEtudiant(Long idEtudiant);

    void removeEtudiant(Long idEtudiant);
}