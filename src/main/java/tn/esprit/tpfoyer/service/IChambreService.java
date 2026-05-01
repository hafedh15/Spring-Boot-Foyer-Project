package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.dto.ChambreDto;

import java.util.List;

public interface IChambreService {

    List<ChambreDto> retrieveAllChambres();

    ChambreDto addChambre(ChambreDto c);

    ChambreDto updateChambre(ChambreDto c);

    ChambreDto retrieveChambre(Long idChambre);

    void removeChambre(Long idChambre);
}