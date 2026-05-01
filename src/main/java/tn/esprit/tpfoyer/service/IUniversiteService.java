package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.dto.UniversiteDto;

import java.util.List;

public interface IUniversiteService {

    List<UniversiteDto> retrieveAllUniversites();

    UniversiteDto addUniversite(UniversiteDto u);

    UniversiteDto updateUniversite(UniversiteDto u);

    UniversiteDto retrieveUniversite(Long idUniversite);

    void removeUniversite(Long idUniversite);
}