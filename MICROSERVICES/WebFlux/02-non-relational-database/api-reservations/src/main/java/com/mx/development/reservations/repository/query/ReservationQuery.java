package com.mx.development.reservations.repository.query;

import com.mx.development.reservations.dto.SearchReservationCriteriaDTO;
import com.mx.development.reservations.model.Passenger;
import com.mx.development.reservations.model.Reservation;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationQuery {

    public static Example<Reservation> exampleWithSearchCriteria(SearchReservationCriteriaDTO criteria) {

        ExampleMatcher matcher = ExampleMatcher.matching().withIncludeNullValues();

        Reservation entity = new Reservation();

        if (criteria.getReservationDate() != null) {
            entity.setCreationDate(criteria.getReservationDate());
        }

        return Example.of(entity, matcher);
    }

    private static void createPassengers(Reservation entity) {
        if (Objects.isNull(entity.getPassengers())) {
            List<Passenger> passengers = new ArrayList<>();
            passengers.add(new Passenger());
            entity.setPassengers(passengers);
        }
    }
}