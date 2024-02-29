package com.myproject.hotelmanagementsystem.service;

import java.util.List;

import com.myproject.hotelmanagementsystem.entity.Reservation;

public interface ReservationService {
    
    public String reserveroom(Long guestId, Long roomId, Integer totalMembers);
    public List<Reservation> getReservedRooms();
    public String closeReservation(Long resId);
}
