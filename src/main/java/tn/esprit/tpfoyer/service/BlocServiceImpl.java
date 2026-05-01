package tn.esprit.tpfoyer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.tpfoyer.dto.BlocDto;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.mapper.BlocMapper;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlocServiceImpl implements IBlocService {

    private final BlocRepository blocRepository;
    private final BlocMapper blocMapper;

    @Override
    public List<BlocDto> retrieveAllBlocs() {

        List<Bloc> blocs = blocRepository.findAll();

        return blocMapper.toDtoList(blocs);
    }

    @Override
    public BlocDto addBloc(BlocDto b) {

        Bloc bloc = blocMapper.toEntity(b);

        bloc = blocRepository.save(bloc);

        return blocMapper.toDto(bloc);
    }

    @Override
    public BlocDto updateBloc(BlocDto b) {

        Bloc bloc = blocMapper.toEntity(b);

        bloc = blocRepository.save(bloc);

        return blocMapper.toDto(bloc);
    }

    @Override
    public BlocDto retrieveBloc(Long idBloc) {

        Bloc bloc = blocRepository.findById(idBloc)
                .orElse(null);

        return blocMapper.toDto(bloc);
    }

    @Override
    public void removeBloc(Long idBloc) {

        blocRepository.deleteById(idBloc);
    }
}