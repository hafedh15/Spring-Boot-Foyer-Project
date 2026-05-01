package tn.esprit.tpfoyer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.BlocDto;
import tn.esprit.tpfoyer.service.IBlocService;

import java.util.List;

@RestController
@RequestMapping("/bloc")
@RequiredArgsConstructor
public class BlocRestController {

    private final IBlocService blocService;

    @GetMapping("/all")
    public List<BlocDto> getBlocs() {
        return blocService.retrieveAllBlocs();
    }

    @GetMapping("/get/{id}")
    public BlocDto getBloc(@PathVariable("id") Long id) {
        return blocService.retrieveBloc(id);
    }

    @PostMapping("/add")
    public BlocDto addBloc(@RequestBody BlocDto b) {
        return blocService.addBloc(b);
    }

    @PutMapping("/update")
    public BlocDto updateBloc(@RequestBody BlocDto b) {
        return blocService.updateBloc(b);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteBloc(@PathVariable("id") Long id) {
        blocService.removeBloc(id);
    }
}