package tn.esprit.tpfoyer.service;

import tn.esprit.tpfoyer.dto.BlocDto;
import java.util.List;

public interface IBlocService {

    List<BlocDto> retrieveAllBlocs();

    BlocDto addBloc(BlocDto b);

    BlocDto updateBloc(BlocDto b);

    BlocDto retrieveBloc(Long idBloc);

    void removeBloc(Long idBloc);
}