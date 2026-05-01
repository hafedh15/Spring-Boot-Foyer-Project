package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.UniversiteDto;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.mapper.UniversiteMapper;
import tn.esprit.tpfoyer.repository.UniversiteRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;
    private final UniversiteMapper universiteMapper;

    @Override
    public List<UniversiteDto> retrieveAllUniversites() {
        List<Universite> universites = universiteRepository.findAll();

        List<UniversiteDto> dtos = new ArrayList<>();
        for (Universite u : universites) {
            dtos.add(universiteMapper.toDto(u));
        }
        return dtos;
    }

    @Override
    public UniversiteDto addUniversite(UniversiteDto u) {

        Universite entity = universiteMapper.toEntity(u);

        Universite savedEntity = universiteRepository.save(entity);

        return universiteMapper.toDto(savedEntity);
    }

    @Override
    public UniversiteDto updateUniversite(UniversiteDto u) {

        Universite entity = universiteMapper.toEntity(u);

        Universite updatedEntity = universiteRepository.save(entity);

        return universiteMapper.toDto(updatedEntity);
    }

    @Override
    public UniversiteDto retrieveUniversite(Long idUniversite) {

        Universite universite = universiteRepository.findById(idUniversite)
                .orElse(null);

        return universiteMapper.toDto(universite);
    }

    @Override
    public void removeUniversite(Long idUniversite) {

        universiteRepository.deleteById(idUniversite);
    }
}