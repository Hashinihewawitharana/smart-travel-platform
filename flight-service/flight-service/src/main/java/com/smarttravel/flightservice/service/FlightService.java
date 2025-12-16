package com.smarttravel.flightservice.service;

import com.smarttravel.flightservice.dto.FlightDTO;
import java.util.List;

public interface FlightService {

    FlightDTO createFlight(FlightDTO flightDTO);

    FlightDTO getFlightById(Long id);

    List<FlightDTO> getAllFlights();

    boolean isSeatAvailable(Long flightId, int requestedSeats);
}


