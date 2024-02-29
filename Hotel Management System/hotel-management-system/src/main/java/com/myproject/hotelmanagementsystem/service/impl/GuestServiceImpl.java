package com.myproject.hotelmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.hotelmanagementsystem.entity.Guest;
import com.myproject.hotelmanagementsystem.exception.InvalidSearchException;
import com.myproject.hotelmanagementsystem.repository.GuestRepository;
import com.myproject.hotelmanagementsystem.service.GuestService;

import lombok.NonNull;

@Service
public class GuestServiceImpl implements GuestService {
    
    @Autowired
    private GuestRepository guestRepo;

    @Override
    public String addGuest(Guest guest) {
        Optional<Guest> existing = guestRepo.findByMailId(guest.getMailId());

        if (existing.isPresent()) {
            Guest existingGuest = existing.get();
            existingGuest.setVisited(existingGuest.getVisited() + 1);
            guestRepo.save(existingGuest);

            return "Guest visited already";
        }

        else {
            guest.setVisited(1);
            guestRepo.save(guest);
            return "New Guest added with id: " + guest.getGuestId();
        }
    }

    @Override
    public Guest getGuestById(@NonNull Long guestId) {
        return guestRepo.findById(guestId).orElse(null);
    }

    @Override
    public List<Guest> getAllGuests() {
        return guestRepo.findAll();
    }

    @Override
    public String deleteGuest(@NonNull Long guestId) {
        boolean deleted = false;

        if (guestRepo.existsById(guestId)) {
            deleted = true;
            guestRepo.deleteById(guestId);
        }

        return "Guest " + guestId + " deleted: " + deleted;
    }

    @Override
    public Guest updateGuest(@NonNull Long guestId, Guest guest) throws InvalidSearchException {
        Optional<Guest> existing = guestRepo.findById(guestId);
        Guest existingGuest = null;

        if (existing.isPresent()) {
            existingGuest = existing.get();

            existingGuest.setFirstName(guest.getFirstName());
            existingGuest.setLastName(guest.getLastName());
            existingGuest.setAge(guest.getAge());
            existingGuest.setGender(guest.getGender());
            existingGuest.setMailId(guest.getMailId());
            existingGuest.setPhoneNo(guest.getPhoneNo());

            guestRepo.save(existingGuest);
        }

        if (existingGuest == null)
            throw new InvalidSearchException("Guest Invalid");

        return existingGuest;
    }
}
