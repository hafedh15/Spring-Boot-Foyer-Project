package tn.esprit.tpfoyer.dto;

import lombok.Data;

@Data
public class EtudiantDto {

    private Long idEtudiant;
    private String nomEtudiant;
    private String prenomEtudiant;
    private Long cin;
}