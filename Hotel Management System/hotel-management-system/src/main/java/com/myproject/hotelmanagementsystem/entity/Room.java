package com.myproject.hotelmanagementsystem.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "room")
public class  Room {
    
    @Id
    @Column(name = "room_no")
    private Long roomNo;

    @Column(name = "room_floor")
    private Integer roomFloor;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "is_occupied")
    private Boolean isOccupied;

    @Column(name = "price")
    private BigDecimal price;

}
