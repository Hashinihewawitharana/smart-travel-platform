package com.smarttravel.bookingservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long flightId;
    private Long hotelId;

    private Integer bookedSeats;
    private Integer bookedRooms;

    private Double totalAmount;

    private String status; // PENDING, PAID, CONFIRMED, FAILED
    private LocalDate travelDate;
}
