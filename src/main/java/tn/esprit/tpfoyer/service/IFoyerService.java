package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.dto.FoyerDto;
import java.util.List;

public interface IFoyerService {
    List<FoyerDto> retrieveAllFoyers();
    FoyerDto addFoyer(FoyerDto f);
    FoyerDto updateFoyer(FoyerDto f);
    FoyerDto retrieveFoyer(Long idFoyer);
    void removeFoyer(Long idFoyer);
}