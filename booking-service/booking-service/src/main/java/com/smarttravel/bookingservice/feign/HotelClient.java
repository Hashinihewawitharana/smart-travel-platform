package com.smarttravel.bookingservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@FeignClient(name = "hotel-service", url = "http://localhost:8083")
public interface HotelClient {

    @GetMapping("/api/hotels/{id}/availability")
    Map<String, Object> checkAvailability(
            @PathVariable("id") Long hotelId,
            @RequestParam Integer requestedRooms
    );
}

