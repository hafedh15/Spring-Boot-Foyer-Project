package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.FoyerDto;
import tn.esprit.tpfoyer.entity.Foyer;
import tn.esprit.tpfoyer.mapper.FoyerMapper;
import tn.esprit.tpfoyer.repository.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;
    private final FoyerMapper foyerMapper;

    @Override
    public List<FoyerDto> retrieveAllFoyers() {
        List<Foyer> foyers = foyerRepository.findAll();
        return foyerMapper.toDtoList(foyers);
    }

    @Override
    public FoyerDto addFoyer(FoyerDto f) {
        Foyer entity = foyerMapper.toEntity(f);
        Foyer savedEntity = foyerRepository.save(entity);
        return foyerMapper.toDto(savedEntity);
    }

    @Override
    public FoyerDto updateFoyer(FoyerDto f) {
        Foyer entity = foyerMapper.toEntity(f);
        Foyer updatedEntity = foyerRepository.save(entity);
        return foyerMapper.toDto(updatedEntity);
    }

    @Override
    public FoyerDto retrieveFoyer(Long idFoyer) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        return foyerMapper.toDto(foyer);
    }

    @Override
    public void removeFoyer(Long idFoyer) {
        foyerRepository.deleteById(idFoyer);
    }
}