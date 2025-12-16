package com.smarttravel.bookingservice.service;

import com.smarttravel.bookingservice.dto.BookingDTO;

import java.util.List;


import com.smarttravel.bookingservice.dto.BookingResponseDTO;


public interface BookingService {

    BookingResponseDTO createBooking(BookingDTO bookingDTO);

    BookingResponseDTO getBookingById(Long bookingId);

    List<BookingResponseDTO> getAllBookings();
}
