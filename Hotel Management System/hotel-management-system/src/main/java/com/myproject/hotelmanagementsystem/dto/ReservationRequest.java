package com.myproject.hotelmanagementsystem.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationRequest {

    @Id
    private Long resId;
    private Long guestId;
    private Integer totalMembers;
    private Long roomId;

}