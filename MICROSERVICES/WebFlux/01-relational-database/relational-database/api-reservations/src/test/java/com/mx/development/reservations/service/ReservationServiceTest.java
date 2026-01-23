package com.mx.development.reservations.service;

import com.mx.development.reservations.connector.CatalogConnector;
import com.mx.development.reservations.connector.response.CityDTO;
import com.mx.development.reservations.dto.ItineraryDTO;
import com.mx.development.reservations.dto.PassengerDTO;
import com.mx.development.reservations.dto.ReservationDTO;
import com.mx.development.reservations.dto.SegmentDTO;
import com.mx.development.reservations.enums.APIError;
import com.mx.development.reservations.exception.EdteamException;
import com.mx.development.reservations.model.Passenger;
import com.mx.development.reservations.model.Reservation;
import com.mx.development.reservations.repository.ReservationRepository;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository repository;

    @Mock
    private ConversionService conversionService;

    @Mock
    private CatalogConnector catalogConnector;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void save_ShouldThrowExceptionWhenIdIsNotNull() {
        // Arrange
        ReservationDTO reservationDTO = createSampleReservationDTO();
        reservationDTO.setId(1L);

        // Act & Assert
        EdteamException exception = assertThrows(EdteamException.class, () -> reservationService.save(reservationDTO));
        assertNotNull(exception);
        verify(repository, never()).save(any());
    }

    @Test
    void save_ShouldThrowExceptionWhenCityNotFound() {
        // Arrange
        ReservationDTO reservationDTO = createSampleReservationDTO();
        when(catalogConnector.getCity(anyString())).thenReturn(null);

        // Act & Assert
        EdteamException exception = assertThrows(EdteamException.class, () -> reservationService.save(reservationDTO));
        assertNotNull(exception);
        verify(repository, never()).save(any());
    }

    private ReservationDTO createSampleReservationDTO() {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setCreationDate(LocalDate.now());
        reservationDTO.setPassengers(List.of(new PassengerDTO()));
        ItineraryDTO itineraryDTO = new ItineraryDTO();
        SegmentDTO segmentDTO = new SegmentDTO();
        segmentDTO.setOrigin("City1");
        segmentDTO.setDestination("City2");
        itineraryDTO.setSegment(Collections.singletonList(segmentDTO));
        reservationDTO.setItinerary(itineraryDTO);
        return reservationDTO;
    }

    private Reservation createSampleReservationEntity() {
        Reservation reservation = new Reservation();
        reservation.setCreationDate(LocalDate.now());
        return reservation;
    }
}