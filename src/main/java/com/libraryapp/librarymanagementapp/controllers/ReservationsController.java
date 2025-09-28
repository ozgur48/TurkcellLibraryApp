package com.libraryapp.librarymanagementapp.controllers;

import com.libraryapp.librarymanagementapp.dto.reservation.CreateReservationRequest;
import com.libraryapp.librarymanagementapp.dto.reservation.ReservationResponse;
import com.libraryapp.librarymanagementapp.dto.reservation.StockIncreaseRequest;
import com.libraryapp.librarymanagementapp.mapper.ReservationMapper;
import com.libraryapp.librarymanagementapp.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/library/reservations")
@RestController
@Validated
public class ReservationsController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;

    public ReservationsController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping
    public ReservationResponse create(@Valid @RequestBody CreateReservationRequest req) {
        return reservationMapper.reservationtoReservationResponse(reservationService.createReservation(req.memberId(), req.bookId()));
    }

    @PostMapping("/stock-increase")
    public ResponseEntity<Void> stockIncrease(@Valid @RequestBody StockIncreaseRequest req) {
        reservationService.onStockIncrease(req.bookId(), req.delta());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/fulfill")
    public ResponseEntity<Void> fulfill(@PathVariable Integer id) {
        reservationService.fulfillReservation(id);
        return ResponseEntity.noContent().build();
    }
}
