package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.UniversiteDto;
import tn.esprit.tpfoyer.service.IUniversiteService;

import java.util.List;

@RestController
@RequestMapping("/universite")
@AllArgsConstructor
public class UniversiteRestController {

    private final IUniversiteService universiteService;

    @GetMapping("/all")
    public List<UniversiteDto> getUniversites() {
        return universiteService.retrieveAllUniversites();
    }

    @GetMapping("/get/{id}")
    public UniversiteDto getUniversite(@PathVariable("id") Long id) {
        return universiteService.retrieveUniversite(id);
    }

    @PostMapping("/add")
    public UniversiteDto addUniversite(@RequestBody UniversiteDto u) {
        return universiteService.addUniversite(u);
    }

    @PutMapping("/update")
    public UniversiteDto updateUniversite(@RequestBody UniversiteDto u) {
        return universiteService.updateUniversite(u);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUniversite(@PathVariable("id") Long id) {
        universiteService.removeUniversite(id);
    }
}