package com.smarttravel.hotelservice.service;

import com.smarttravel.hotelservice.dto.HotelDTO;
import java.util.List;

public interface HotelService {

    HotelDTO createHotel(HotelDTO hotelDTO);

    HotelDTO getHotelById(Long id);

    List<HotelDTO> getAllHotels();

    boolean isRoomAvailable(Long hotelId, int requestedRooms);
}

