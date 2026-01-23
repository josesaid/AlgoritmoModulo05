package com.mx.development.reservations.repository;

import com.mx.development.reservations.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservationRepository extends MongoRepository<Reservation, String> {

}