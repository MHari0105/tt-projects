package com.myproject.hotelmanagementsystem.entity;

import java.math.BigDecimal;

import java.util.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "res_id")
    private Long resId;

    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guest guest;

    @Column(name = "name")
    private String name;

    @Column(name = "mail_id")
    private String mailId;

    @Column(name = "contact_no")
    private String contactNo;
    
    @Column(name = "total_members")
    private Integer totalMembers;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_in_date")
    private Date checkInDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "check_out_date")
    private Date checkOutDate;
    
    @Column(name = "total_cost")
    private BigDecimal totalCost;

}
