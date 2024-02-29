package com.myproject.hotelmanagementsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myproject.hotelmanagementsystem.entity.Room;
import com.myproject.hotelmanagementsystem.repository.RoomRepository;
import com.myproject.hotelmanagementsystem.service.RoomService;

import lombok.NonNull;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepo;

    @Override
    public String createRoom(@NonNull Room room) {
        Long roomId = room.getRoomNo();
        Optional<Room> existing = roomRepo.findById(roomId);

        if (existing.isPresent()) 
            return "Room " + room.getRoomNo() + " already exists";
        
        else {
            roomRepo.save(room);
            return "Room " + room.getRoomNo() + " saved";
        }
    }
    
    @Override
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }

    @Override
    public List<Room> getFreeRooms() {
        return roomRepo.findByIsOccupiedFalse();
    }
    
    public Room changeStatus(@NonNull Long roomId, Room room) {
        Optional<Room> existing = roomRepo.findById(roomId);
        Room updateRoom = null;

        if (existing.isPresent()) {
            updateRoom = existing.get();

            updateRoom.setRoomFloor(room.getRoomFloor());
            updateRoom.setRoomType(room.getRoomType());
            updateRoom.setIsOccupied(room.getIsOccupied());
            updateRoom.setPrice(room.getPrice());

            roomRepo.save(updateRoom);
        }

        return updateRoom;
    
        // return roomOptional.map(room -> {
        //     room.setIsOccupied(!room.getIsOccupied());
        //     return roomRepo.save(room);
        // });
    }
}
