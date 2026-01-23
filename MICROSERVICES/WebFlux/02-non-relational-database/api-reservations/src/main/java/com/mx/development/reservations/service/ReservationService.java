package com.mx.development.reservations.service;

import com.mx.development.reservations.connector.CatalogConnector;
import com.mx.development.reservations.connector.response.CityDTO;
import com.mx.development.reservations.dto.SearchReservationCriteriaDTO;
import com.mx.development.reservations.dto.SegmentDTO;
import com.mx.development.reservations.enums.APIError;
import com.mx.development.reservations.exception.EdteamException;
import com.mx.development.reservations.dto.ReservationDTO;
import com.mx.development.reservations.model.Reservation;
import com.mx.development.reservations.repository.ReservationRepository;
import com.mx.development.reservations.repository.query.ReservationQuery;
import jakarta.validation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class ReservationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservationService.class);

    private ReservationRepository reservationRepository;

    private ConversionService conversionService;

    private CatalogConnector catalogConnector;

    @Autowired
    public ReservationService(ReservationRepository repository, ConversionService conversionService,
            CatalogConnector catalogConnector) {
        this.reservationRepository = repository;
        this.conversionService = conversionService;
        this.catalogConnector = catalogConnector;
    }

    public Flux<ReservationDTO> getReservations(SearchReservationCriteriaDTO criteria) {
        Pageable pageable = PageRequest.of(criteria.getPageActual(), criteria.getPageSize());

        List<Reservation> reservations = reservationRepository
                .findAll(ReservationQuery.exampleWithSearchCriteria(criteria), pageable).toList();

        return Flux.fromIterable(reservations)
                .mapNotNull(reservation -> conversionService.convert(reservation, ReservationDTO.class))
                // .zipWith(Flux.interval(Duration.ofSeconds(1)), (reservation, interval) -> reservation);
                .concatMap(reservation -> Mono.just(conversionService.convert(reservation, ReservationDTO.class))
                        .delayElement(Duration.ofMillis(2500))); // Retardo de 1500 ms por cada elemento
        // .delayElements(Duration.ofSeconds(2));
    }

    public Mono<ReservationDTO> getReservationById(String id) {
        Optional<Reservation> result = reservationRepository.findById(id);
        if (result.isEmpty()) {
            LOGGER.debug("Not exist reservation with the id {}", id);
            throw new EdteamException(APIError.RESERVATION_NOT_FOUND);
        }
        return Mono.justOrEmpty(conversionService.convert(result.get(), ReservationDTO.class));
    }

    public Mono<ReservationDTO> save(ReservationDTO reservation) {
        if (Objects.nonNull(reservation.getId())) {
            throw new EdteamException(APIError.RESERVATION_WITH_SAME_ID);
        }
        checkCity(reservation);

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        validateEntity(transformed);

        Reservation result = reservationRepository.save(Objects.requireNonNull(transformed));
        return Mono.justOrEmpty(conversionService.convert(result, ReservationDTO.class));
    }

    public Mono<ReservationDTO> update(String id, ReservationDTO reservation) {
        if (!reservationRepository.existsById(id)) {
            LOGGER.debug("Not exist reservation with the id {}", id);
            throw new EdteamException(APIError.RESERVATION_NOT_FOUND);
        }
        checkCity(reservation);

        Reservation transformed = conversionService.convert(reservation, Reservation.class);
        validateEntity(transformed);
        Reservation result = reservationRepository.save(Objects.requireNonNull(transformed));

        return Mono.justOrEmpty(conversionService.convert(result, ReservationDTO.class));
    }

    public Mono<Void> delete(String id) {
        if (!reservationRepository.existsById(id)) {
            LOGGER.debug("Not exist reservation with the id {}", id);
            throw new EdteamException(APIError.RESERVATION_NOT_FOUND);
        }

        reservationRepository.deleteById(id);

        return Mono.empty();
    }

    private void checkCity(ReservationDTO reservationDTO) {
        for (SegmentDTO segmentDTO : reservationDTO.getItinerary().getSegment()) {
            Mono<CityDTO> origin = catalogConnector.getCity(segmentDTO.getOrigin());
            Mono<CityDTO> destination = catalogConnector.getCity(segmentDTO.getDestination());

            if (origin.block() == null || destination.block() == null) {
                throw new EdteamException(APIError.VALIDATION_ERROR);
            }
        }
    }

    private void validateEntity(Reservation transformed) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Reservation>> violations = validator.validate(transformed);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
