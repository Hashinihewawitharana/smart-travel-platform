package com.smarttravel.hotelservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hotels", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "city"})
})
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Hotel name is required")
    private String name;

    @NotBlank(message = "City is required")
    private String city;

    @Min(value = 1, message = "Total rooms must be at least 1")
    private Integer totalRooms;

    @Min(value = 0, message = "Available rooms must be non-negative")
    private Integer availabilityRooms;


    // Initialize availability equal to totalRooms
    @PrePersist
    public void prePersist() {
        if (availabilityRooms == 0) {
            availabilityRooms = totalRooms;
        }
    }
}
