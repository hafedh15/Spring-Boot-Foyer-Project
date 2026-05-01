package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.EtudiantDto;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;

@RestController
@RequestMapping("/etudiant")
@AllArgsConstructor
public class EtudiantRestController {

    private final IEtudiantService etudiantService;

    @GetMapping("/all")
    public List<EtudiantDto> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    @GetMapping("/get/{id}")
    public EtudiantDto getEtudiant(@PathVariable("id") Long id) {
        return etudiantService.retrieveEtudiant(id);
    }

    @PostMapping("/add")
    public EtudiantDto addEtudiant(@RequestBody EtudiantDto e) {
        return etudiantService.addEtudiant(e);
    }

    @PutMapping("/update")
    public EtudiantDto updateEtudiant(@RequestBody EtudiantDto e) {
        return etudiantService.updateEtudiant(e);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEtudiant(@PathVariable("id") Long id) {
        etudiantService.removeEtudiant(id);
    }
}