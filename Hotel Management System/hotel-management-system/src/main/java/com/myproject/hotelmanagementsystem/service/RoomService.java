package com.myproject.hotelmanagementsystem.service;

import java.util.List;

import com.myproject.hotelmanagementsystem.entity.Room;

public interface RoomService {
    
    public String createRoom(Room room);
    public List<Room> getAllRooms();
    public List<Room> getFreeRooms();
    public Room changeStatus(Long roomId, Room room);
}
