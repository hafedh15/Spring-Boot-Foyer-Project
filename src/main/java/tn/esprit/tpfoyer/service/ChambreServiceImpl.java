package tn.esprit.tpfoyer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.ChambreDto;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.mapper.ChambreMapper;
import tn.esprit.tpfoyer.repository.ChambreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;
    private final ChambreMapper chambreMapper;

    @Override
    public List<ChambreDto> retrieveAllChambres() {
        List<Chambre> chambres = chambreRepository.findAll();

        List<ChambreDto> dtos = new ArrayList<>();
        for (Chambre c : chambres) {
            dtos.add(chambreMapper.toDto(c));
        }
        return dtos;
    }

    @Override
    public ChambreDto addChambre(ChambreDto c) {

        Chambre chambre = chambreMapper.toEntity(c);

        chambre = chambreRepository.save(chambre);

        return chambreMapper.toDto(chambre);
    }

    @Override
    public ChambreDto updateChambre(ChambreDto c) {

        Chambre chambre = chambreMapper.toEntity(c);

        chambre = chambreRepository.save(chambre);

        return chambreMapper.toDto(chambre);
    }

    @Override
    public ChambreDto retrieveChambre(Long idChambre) {

        Chambre chambre = chambreRepository.findById(idChambre)
                .orElse(null);

        return chambreMapper.toDto(chambre);
    }

    @Override
    public void removeChambre(Long idChambre) {

        chambreRepository.deleteById(idChambre);
    }
}