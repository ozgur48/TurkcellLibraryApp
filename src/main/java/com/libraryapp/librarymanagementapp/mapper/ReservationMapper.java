package com.libraryapp.librarymanagementapp.mapper;

import com.libraryapp.librarymanagementapp.dto.reservation.CreateReservationRequest;
import com.libraryapp.librarymanagementapp.dto.reservation.ReservationResponse;
import com.libraryapp.librarymanagementapp.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    Reservation createReservationRequestToReservation(CreateReservationRequest createReservationRequest);
    ReservationResponse reservationtoReservationResponse (Reservation reservation);
}
