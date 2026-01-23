package com.mx.development.reservations.controller;

import com.mx.development.reservations.controller.resource.ReservationResource;
import com.mx.development.reservations.dto.ReservationDTO;
import com.mx.development.reservations.dto.SearchReservationCriteriaDTO;
import com.mx.development.reservations.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
@Validated
public class ReservationController implements ReservationResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getReservations(SearchReservationCriteriaDTO criteria) {
        LOGGER.info("Obtain all the reservations");
        List<ReservationDTO> response = service.getReservations(criteria);
        if (response.isEmpty()) {
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        LOGGER.info("Obtain information from a reservation with {}", id);
        ReservationDTO response = service.getReservationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @PostMapping
    public ResponseEntity<ReservationDTO> save(@RequestBody ReservationDTO reservation) {
        LOGGER.info("Saving new reservation");
        ReservationDTO response = service.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> update(@PathVariable Long id, @RequestBody ReservationDTO reservation) {
        LOGGER.info("Updating a reservation with {}", id);
        ReservationDTO response = service.update(id, reservation);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Deleting a reservation with {}", id);
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
