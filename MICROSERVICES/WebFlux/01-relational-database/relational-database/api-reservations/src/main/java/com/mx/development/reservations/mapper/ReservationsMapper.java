package com.mx.development.reservations.mapper;

import com.mx.development.reservations.dto.ReservationDTO;
import com.mx.development.reservations.model.Reservation;
import org.mapstruct.Mapper;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReservationsMapper extends Converter<List<Reservation>, List<ReservationDTO>> {

    @Override
    List<ReservationDTO> convert(List<Reservation> source);

}