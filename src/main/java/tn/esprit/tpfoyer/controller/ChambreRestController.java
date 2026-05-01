package tn.esprit.tpfoyer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.ChambreDto;
import tn.esprit.tpfoyer.service.IChambreService;

import java.util.List;

@RestController
@RequestMapping("/chambre")
@RequiredArgsConstructor
public class ChambreRestController {

    private final IChambreService chambreService;

    @GetMapping("/all")
    public List<ChambreDto> getChambres() {
        return chambreService.retrieveAllChambres();
    }

    @GetMapping("/get/{id}")
    public ChambreDto getChambre(@PathVariable("id") Long id) {
        return chambreService.retrieveChambre(id);
    }

    @PostMapping("/add")
    public ChambreDto addChambre(@RequestBody ChambreDto c) {
        return chambreService.addChambre(c);
    }

    @PutMapping("/update")
    public ChambreDto updateChambre(@RequestBody ChambreDto c) {
        return chambreService.updateChambre(c);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteChambre(@PathVariable("id") Long id) {
        chambreService.removeChambre(id);
    }
}