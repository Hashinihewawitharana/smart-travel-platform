package com.smarttravel.flightservice.service;

import com.smarttravel.flightservice.dto.FlightDTO;
import com.smarttravel.flightservice.entity.Flight;
import com.smarttravel.flightservice.exception.FlightNotFoundException;
import com.smarttravel.flightservice.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;

    @Override
    public FlightDTO createFlight(FlightDTO flightDTO) {

        flightRepository.findByFlightNumber(flightDTO.getFlightNumber())
                .ifPresent(f -> {
                    throw new RuntimeException("Flight already exists: " + flightDTO.getFlightNumber());
                });

        Flight flight = new Flight(
                null,
                flightDTO.getFlightNumber(),
                flightDTO.getAirline(),
                flightDTO.getDepartureCity(),
                flightDTO.getArrivalCity(),
                flightDTO.getTotalSeats(),
                flightDTO.getAvailableSeats() != null ? flightDTO.getAvailableSeats() : flightDTO.getTotalSeats()

        );

        Flight savedFlight = flightRepository.save(flight);

        return new FlightDTO(
                savedFlight.getId(),
                savedFlight.getFlightNumber(),
                savedFlight.getAirline(),
                savedFlight.getDepartureCity(),
                savedFlight.getArrivalCity(),
                savedFlight.getTotalSeats(),
                savedFlight.getAvailableSeats()
        );
    }

    @Override
    public FlightDTO getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + id));

        return new FlightDTO(
                flight.getId(),
                flight.getFlightNumber(),
                flight.getAirline(),
                flight.getDepartureCity(),
                flight.getArrivalCity(),
                flight.getTotalSeats(),
                flight.getAvailableSeats()

        );
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll().stream()
                .map(f -> new FlightDTO(
                        f.getId(),
                        f.getFlightNumber(),
                        f.getAirline(),
                        f.getDepartureCity(),
                        f.getArrivalCity(),
                        f.getTotalSeats(),
                        f.getAvailableSeats()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSeatAvailable(Long flightId, int requestedSeats) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with id: " + flightId));
        return flight.getAvailableSeats() >= requestedSeats;
    }
}
