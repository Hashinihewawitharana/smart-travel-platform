package com.smarttravel.bookingservice.service;

import com.smarttravel.bookingservice.dto.*;
import com.smarttravel.bookingservice.entity.Booking;
import com.smarttravel.bookingservice.exception.BookingNotFoundException;
import com.smarttravel.bookingservice.feign.FlightClient;
import com.smarttravel.bookingservice.feign.HotelClient;
import com.smarttravel.bookingservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.smarttravel.bookingservice.dto.HotelDTO;
import com.smarttravel.bookingservice.dto.FlightDTO;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FlightClient flightClient;
    private final HotelClient hotelClient;
    private final WebClient.Builder webClientBuilder;

    @Override
    public BookingResponseDTO createBooking(BookingDTO bookingDTO) {

        if (bookingDTO.getUserId() == null || bookingDTO.getFlightId() == null || bookingDTO.getHotelId() == null) {
            throw new IllegalArgumentException("UserId, FlightId, and HotelId are required");
        }

        // 1️⃣ Validate User via User Service
        UserDTO userDTO = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/api/users/{id}", bookingDTO.getUserId())
                .retrieve()
                .bodyToMono(UserDTO.class)
                .block();

        if (userDTO == null) {
            throw new IllegalArgumentException("User not found with id: " + bookingDTO.getUserId());
        }

        // 2️⃣ Check Flight Availability
        Boolean flightAvailable = (Boolean) flightClient.checkAvailability(
                bookingDTO.getFlightId(),
                userDTO.getRequestSeats()
        ).get("available");

        if (!flightAvailable) {
            throw new IllegalArgumentException("Flight does not have enough seats");
        }

        // 3️⃣ Check Hotel Availability
        Boolean hotelAvailable = (Boolean) hotelClient.checkAvailability(
                bookingDTO.getHotelId(),
                userDTO.getRequestRooms()
        ).get("available");

        if (!hotelAvailable) {
            throw new IllegalArgumentException("Hotel does not have enough rooms");
        }

        // 4️⃣ Calculate total cost
        double flightCostPerSeat = 5000.0;
        double hotelCostPerRoom = 10000.0;

        int seats = userDTO.getRequestSeats();
        int rooms = userDTO.getRequestRooms();

        double totalAmount = seats * flightCostPerSeat + rooms * hotelCostPerRoom;

        // 5️⃣ Save Booking as PENDING
        Booking booking = new Booking(
                null,
                bookingDTO.getUserId(),
                bookingDTO.getFlightId(),
                bookingDTO.getHotelId(),
                seats,
                rooms,
                totalAmount,
                "PENDING",
                bookingDTO.getTravelDate()
        );

        Booking savedBooking = bookingRepository.save(booking);

        // 6️⃣ Call Payment Service
        try {
            webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8085/api/payments")
                    .bodyValue(new PaymentRequestDTO(
                            null,
                            bookingDTO.getUserId(),
                            savedBooking.getId(),
                            totalAmount,
                            "CREDIT_CARD",
                            null,
                            "PENDING"
                    ))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            // Update booking status after payment call
            savedBooking.setStatus("CONFIRMED");
            bookingRepository.save(savedBooking);

        } catch (Exception ex) {
            savedBooking.setStatus("FAILED");
            bookingRepository.save(savedBooking);

            // Notify failure
            webClientBuilder.build()
                    .post()
                    .uri("http://localhost:8086/api/notifications")
                    .bodyValue(new NotificationRequestDTO(
                            null,
                            bookingDTO.getUserId(),
                            "Payment failed for booking id: " + savedBooking.getId(),
                            null
                    ))
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();

            throw new RuntimeException("Payment failed: " + ex.getMessage());
        }

        // 7️⃣ Call Notification Service
        webClientBuilder.build()
                .post()
                .uri("http://localhost:8086/api/notifications")
                .bodyValue(new NotificationRequestDTO(
                        null,
                        bookingDTO.getUserId(),
                        "Booking confirmed successfully! Booking id: " + savedBooking.getId(),
                        null
                ))
                .retrieve()
                .bodyToMono(Void.class)
                .block();

        // 8️⃣ Return Response DTO with flightNumber, hotelName
        return mapToResponseDTO(savedBooking);
    }

    @Override
    public BookingResponseDTO getBookingById(Long id) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new BookingNotFoundException("Booking not found with id: " + id));

        return mapToResponseDTO(booking);
    }

    @Override
    public List<BookingResponseDTO> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    private BookingResponseDTO mapToResponseDTO(Booking booking) {

        // Flight details
        FlightDTO flightDTO = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/api/flights/{id}", booking.getFlightId())
                .retrieve()
                .bodyToMono(FlightDTO.class)
                .block();

        // Hotel details
        HotelDTO hotelDTO = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/api/hotels/{id}", booking.getHotelId())
                .retrieve()
                .bodyToMono(HotelDTO.class)
                .block();

        return new BookingResponseDTO(
                booking.getId(),
                booking.getUserId(),
                booking.getFlightId(),
                booking.getHotelId(),
                booking.getBookedSeats(),
                booking.getBookedRooms(),
                booking.getTotalAmount(),
                booking.getStatus(),
                booking.getTravelDate(),
                flightDTO != null ? flightDTO.getFlightNumber() : null,
                hotelDTO != null ? hotelDTO.getName() : null,
                booking.getStatus().equals("CONFIRMED") ? "Booking confirmed successfully" : "Booking is " + booking.getStatus()
        );
    }

}
