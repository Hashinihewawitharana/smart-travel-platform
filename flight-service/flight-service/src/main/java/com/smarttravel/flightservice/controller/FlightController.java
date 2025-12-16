package com.smarttravel.flightservice.controller;

import com.smarttravel.flightservice.dto.FlightDTO;
import com.smarttravel.flightservice.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightDTO> createFlight(@Valid @RequestBody FlightDTO flightDTO) {
        return ResponseEntity.ok(flightService.createFlight(flightDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDTO> getFlight(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping
    public ResponseEntity<List<FlightDTO>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}/availability")
    public ResponseEntity<Map<String, Object>> checkAvailability(
            @PathVariable Long id,
            @RequestParam int requestedSeats) {

        boolean available = flightService.isSeatAvailable(id, requestedSeats);
        return ResponseEntity.ok(Map.of(
                "flightId", id,
                "requestedSeats", requestedSeats,
                "available", available
        ));
    }
}
