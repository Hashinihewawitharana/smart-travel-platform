package com.smarttravel.bookingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "flight-service", url = "http://localhost:8082")
public interface FlightClient {

    @GetMapping("/api/flights/{id}/availability")
    Map<String, Object> checkAvailability(
            @PathVariable("id") Long flightId,
            @RequestParam("requestedSeats") Integer requestedSeats
    );
}



