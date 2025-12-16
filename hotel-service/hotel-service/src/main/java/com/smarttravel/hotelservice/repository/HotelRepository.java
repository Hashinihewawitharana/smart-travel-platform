package com.smarttravel.hotelservice.repository;

import com.smarttravel.hotelservice.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
        Optional<Hotel> findByNameAndCity(String name, String city);
}

