package tn.esprit.tpfoyer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.tpfoyer.entity.Bloc;

import java.util.List;

public interface BlocRepository extends JpaRepository<Bloc, Long> {

    List<Bloc> findByNomBloc(String nomBloc);

    List<Bloc> findByNomBlocAndCapaciteBloc(String nomBloc, Long capaciteBloc);

    long deleteByNomBloc(String nomBloc);
}
