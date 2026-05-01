package tn.esprit.tpfoyer.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.dto.ReservationDto;
import tn.esprit.tpfoyer.service.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationRestController {

    private final IReservationService reservationService;

    @GetMapping("/all")
    public List<ReservationDto> getReservations() {
        return reservationService.retrieveAllReservations();
    }

    @GetMapping("/get/{id}")
    public ReservationDto getReservation(@PathVariable("id") String id) {
        return reservationService.retrieveReservation(id);
    }

    @PostMapping("/add")
    public ReservationDto addReservation(@RequestBody ReservationDto r) {
        return reservationService.addReservation(r);
    }

    @PutMapping("/update")
    public ReservationDto updateReservation(@RequestBody ReservationDto r) {
        return reservationService.updateReservation(r);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteReservation(@PathVariable("id") String id) {
        reservationService.removeReservation(id);
    }
}
