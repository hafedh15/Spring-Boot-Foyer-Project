package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.FoyerDto;
import tn.esprit.tpfoyer.service.IFoyerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
public class FoyerRestController {

    private final IFoyerService foyerService;

    @GetMapping("/all")
    public List<FoyerDto> getFoyers() {
        return foyerService.retrieveAllFoyers();
    }

    @GetMapping("/get/{id}")
    public FoyerDto getFoyer(@PathVariable("id") Long id) {
        return foyerService.retrieveFoyer(id);
    }

    @PostMapping("/add")
    public FoyerDto addFoyer(@RequestBody FoyerDto f) {
        return foyerService.addFoyer(f);
    }

    @PutMapping("/update")
    public FoyerDto updateFoyer(@RequestBody FoyerDto f) {
        return foyerService.updateFoyer(f);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFoyer(@PathVariable("id") Long id) {
        foyerService.removeFoyer(id);
    }
}