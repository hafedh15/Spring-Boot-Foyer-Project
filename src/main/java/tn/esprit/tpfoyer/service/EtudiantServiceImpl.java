package tn.esprit.tpfoyer.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.EtudiantDto;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.mapper.EtudiantMapper;
import tn.esprit.tpfoyer.repository.EtudiantRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final EtudiantMapper etudiantMapper;

    @Override
    public List<EtudiantDto> retrieveAllEtudiants() {
        List<Etudiant> etudiants = etudiantRepository.findAll();

        List<EtudiantDto> dtos = new ArrayList<>();
        for (Etudiant e : etudiants) {
            dtos.add(etudiantMapper.toDto(e));
        }
        return dtos;
    }

    @Override
    public EtudiantDto addEtudiant(EtudiantDto e) {

        Etudiant entity = etudiantMapper.toEntity(e);

        Etudiant savedEntity = etudiantRepository.save(entity);

        return etudiantMapper.toDto(savedEntity);
    }

    @Override
    public EtudiantDto updateEtudiant(EtudiantDto e) {

        Etudiant entity = etudiantMapper.toEntity(e);

        Etudiant updatedEntity = etudiantRepository.save(entity);

        return etudiantMapper.toDto(updatedEntity);
    }

    @Override
    public EtudiantDto retrieveEtudiant(Long idEtudiant) {

        Etudiant etudiant = etudiantRepository.findById(idEtudiant)
                .orElse(null);

        return etudiantMapper.toDto(etudiant);
    }

    @Override
    public void removeEtudiant(Long idEtudiant) {

        etudiantRepository.deleteById(idEtudiant);
    }
}