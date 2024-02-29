package com.myproject.hotelmanagementsystem.service;


import java.util.List;

import com.myproject.hotelmanagementsystem.entity.Guest;
import com.myproject.hotelmanagementsystem.exception.InvalidSearchException;

public interface GuestService {
    
    public String addGuest(Guest guest);
    public Guest getGuestById(Long guestId);
    public List<Guest> getAllGuests();
    public Guest updateGuest(Long guestId, Guest guest) throws InvalidSearchException;
    public String deleteGuest(Long guestId);
}
