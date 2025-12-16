package com.smarttravel.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;
    private Long userId;
    private Long flightId;
    private Long hotelId;
    private Integer bookedSeats;
    private Integer bookedRooms;
    private Double totalAmount;
    private String status;
    private LocalDate travelDate;
}



