package com.myproject.hotelmanagementsystem.service.impl;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.hotelmanagementsystem.entity.Guest;
import com.myproject.hotelmanagementsystem.entity.Reservation;
import com.myproject.hotelmanagementsystem.entity.Room;
import com.myproject.hotelmanagementsystem.repository.GuestRepository;
import com.myproject.hotelmanagementsystem.repository.ReservationRepository;
import com.myproject.hotelmanagementsystem.repository.RoomRepository;
import com.myproject.hotelmanagementsystem.service.ReservationService;

import lombok.NonNull;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private GuestRepository guestRepo;

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public String reserveroom(@NonNull Long guestId,@NonNull Long roomId, Integer totalMembers) {

        Optional<Guest> guestOpt = guestRepo.findById(guestId);
        Optional<Room> roomOpt = roomRepo.findById(roomId);

        if (guestOpt.isPresent() && roomOpt.isPresent()) {
            Guest guest = guestOpt.get();
            Room room = roomOpt.get();

            if (room.getIsOccupied()) 
                return "Room " + room.getRoomNo() + " alraedy occupied";

            String guestname = guest.getFirstName();
            String contactNo = guest.getPhoneNo();
            String guestMailId = guest.getMailId();

            room.setIsOccupied(true);
            roomRepo.save(room);

            Reservation reservation = new Reservation();

            reservation.setGuest(guest);
            reservation.setName(guestname);
            reservation.setContactNo(contactNo);
            reservation.setMailId(guestMailId);
            reservation.setTotalMembers(totalMembers);
            reservation.setRoom(room);
            reservation.setCheckInDate(new Date());

            reservationRepo.save(reservation);

            return "Room resreved successfully";
        }

        else return "Guest or Room not found";
    }

    public List<Reservation> getReservedRooms() {
        return reservationRepo.findAll();
    }

    @Override
    public String closeReservation(@NonNull Long resId) {
        Optional<Reservation> reservationOpt = reservationRepo.findById(resId);

        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();

            if (reservation.getCheckOutDate() == null) {
                Room room = reservation.getRoom();
                room.setIsOccupied(false);
                Date outDate = new Date();
                long durationOfHours = calculateDurationInHours(reservation.getCheckInDate(), outDate);
                double discount = calculateDiscount(reservation.getGuest().getVisited());
                BigDecimal totalCost = calculateTotalCost(room.getPrice(), durationOfHours, discount);

                reservation.setCheckOutDate(outDate);
                reservation.setTotalCost(totalCost);

                reservationRepo.save(reservation);

                return "Reservation closed. Total cost : " + totalCost; 
            }

            else return "Reservation already closed";
        }

        else return "Reservation not found";
    }

    private long calculateDurationInHours(Date inDate, Date outDate) {
        long milliseconds = outDate.getTime() - inDate.getTime();
        return milliseconds / (60 * 60 * 1000);
    }

    private double calculateDiscount(Integer visitCount) {
        if (visitCount != null) {
            if (visitCount > 10)
                return 0.15;
            else if (visitCount > 3)
                return 0.05;
        }

        return 0;
    }

    private BigDecimal calculateTotalCost(BigDecimal roomPrice, long durationOfHours, double discount) {
        BigDecimal totalCost = roomPrice.multiply(BigDecimal.valueOf(durationOfHours));
        BigDecimal discountAmt = totalCost.multiply(BigDecimal.valueOf(discount));

        return totalCost.subtract(discountAmt);
    }
    
}