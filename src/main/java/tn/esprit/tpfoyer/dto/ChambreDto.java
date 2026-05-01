package tn.esprit.tpfoyer.dto;

import lombok.Data;
import tn.esprit.tpfoyer.entity.TypeChambre;

@Data
public class ChambreDto {

    private Long idChambre;
    private Long numeroChambre;
    private TypeChambre typeC;
}