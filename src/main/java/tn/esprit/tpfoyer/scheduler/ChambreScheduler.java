package tn.esprit.tpfoyer.scheduler;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.entity.TypeChambre;
import tn.esprit.tpfoyer.repository.BlocRepository;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class ChambreScheduler {

    private final BlocRepository blocRepository;

    // ======= SERVICE 01 =======
    // triggers every minute
    @Scheduled(cron = "0 * * * * *")
    @Transactional
    public void listeChambresParBloc() {
        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc bloc : blocs) {
            log.info("Bloc => " + bloc.getNomBloc() + " ayant une capacité " + bloc.getCapaciteBloc());

            Set<Chambre> chambres = bloc.getChambres();

            if (chambres == null || chambres.isEmpty()) {
                log.info("Pas de chambre disponible dans ce bloc");
            } else {
                log.info("La liste des chambres pour ce bloc:");
                for (Chambre c : chambres) {
                    log.info("NumChambre: " + c.getNumeroChambre() + " type: " + c.getTypeC());
                }
            }
            log.info("*******************");
        }
    }

    // ======= SERVICE 03 =======
    // triggers every day at midnight
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void nbPlacesDisponibleParChambreAnneeEnCours() {
        List<Bloc> blocs = blocRepository.findAll();

        for (Bloc bloc : blocs) {
            Set<Chambre> chambres = bloc.getChambres();

            if (chambres != null) {
                for (Chambre c : chambres) {
                    long reservations = c.getReservations() == null ? 0 : c.getReservations().size();
                    long capacite = getCapaciteByType(c.getTypeC());
                    long disponible = capacite - reservations;

                    if (disponible <= 0) {
                        log.info("La chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est complete");
                    } else {
                        log.info("Le nombre de place disponible pour la chambre " + c.getTypeC() + " " + c.getNumeroChambre() + " est " + disponible);
                    }
                }
            }
        }
    }

    private long getCapaciteByType(TypeChambre type) {
        switch (type) {
            case SIMPLE: return 1;
            case DOUBLE: return 2;
            case TRIPLE: return 3;
            default: return 0;
        }
    }
}