package com.myproject.hotelmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.hotelmanagementsystem.dto.ReservationRequest;
import com.myproject.hotelmanagementsystem.entity.Reservation;
import com.myproject.hotelmanagementsystem.service.ReservationService;

@RestController
@RequestMapping("/api/reserve")
public class ReservationController {
    
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/post")
    public ResponseEntity<String> reserveRoom(@RequestBody ReservationRequest reservationRequest) {
        String result = reservationService.reserveroom(
            reservationRequest.getGuestId(), reservationRequest.getRoomId(), reservationRequest.getTotalMembers()
        );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Reservation>> getReservations() {
        return new ResponseEntity<>(reservationService.getReservedRooms(), HttpStatus.OK);
    }

    @PutMapping("/close/{resId}")
    public ResponseEntity<String> closeReservation(@PathVariable Long resId) {
        return new ResponseEntity<>(reservationService.closeReservation(resId), HttpStatus.OK);
    }
}
