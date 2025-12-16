package com.smarttravel.hotelservice.service;

import com.smarttravel.hotelservice.dto.HotelDTO;
import com.smarttravel.hotelservice.entity.Hotel;
import com.smarttravel.hotelservice.exception.HotelNotFoundException;
import com.smarttravel.hotelservice.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO createHotel(HotelDTO hotelDTO) {

        hotelRepository.findByNameAndCity(hotelDTO.getName(), hotelDTO.getCity())
                .ifPresent(h -> {
                    throw new RuntimeException(
                            "Hotel already exists in " + hotelDTO.getCity()
                    );
                });

        int rooms = hotelDTO.getTotalRooms();

        Hotel hotel = new Hotel(
                null,
                hotelDTO.getName(),
                hotelDTO.getCity(),
                hotelDTO.getTotalRooms(),
                hotelDTO.getAvailabilityRooms() != null ? hotelDTO.getAvailabilityRooms() : hotelDTO.getTotalRooms()

        );

        Hotel savedHotel = hotelRepository.save(hotel);

        return new HotelDTO(
                savedHotel.getId(),
                savedHotel.getName(),
                savedHotel.getCity(),
                savedHotel.getTotalRooms(),
                savedHotel.getAvailabilityRooms()

        );
    }

    @Override
    public HotelDTO getHotelById(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() ->
                        new HotelNotFoundException("Hotel not found with id: " + id)
                );

        return new HotelDTO(
                hotel.getId(),
                hotel.getName(),
                hotel.getCity(),
                hotel.getTotalRooms(),
                hotel.getAvailabilityRooms()

        );
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        return hotelRepository.findAll().stream()
                .map(h -> new HotelDTO(
                        h.getId(),
                        h.getName(),
                        h.getCity(),
                        h.getTotalRooms(),
                        h.getAvailabilityRooms()

                ))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isRoomAvailable(Long hotelId, int requestedRooms) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(() ->
                        new HotelNotFoundException("Hotel not found with id: " + hotelId)
                );
        return hotel.getAvailabilityRooms() >= requestedRooms;
    }
}
