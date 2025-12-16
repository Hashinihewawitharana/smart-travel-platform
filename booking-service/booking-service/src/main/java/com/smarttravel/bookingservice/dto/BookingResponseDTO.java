package com.smarttravel.bookingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDTO {

    private Long bookingId;
    private Long userId;
    private Long flightId;
    private Long hotelId;

    private Integer bookedSeats;
    private Integer bookedRooms;

    private Double totalAmount;
    private String status; // PENDING, CONFIRMED, FAILED
    private LocalDate travelDate;

    // Optional: extra info to show to client
    private String flightNumber;    // from flight service
    private String hotelName;       // from hotel service
    private String message;         // e.g., "Booking created successfully"
}

