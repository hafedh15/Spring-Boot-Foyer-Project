package tn.esprit.tpfoyer.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"bloc", "reservations"})
@RequiredArgsConstructor


public class Chambre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idChambre;
    private long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    @ManyToOne
    private Bloc bloc;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Reservation> reservations;
    
}
