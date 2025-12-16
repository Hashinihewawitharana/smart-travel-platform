package com.smarttravel.userservice.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2-50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "request seats is required")
    @Min(value = 0, message = "request seat must be non-negative")
    private Integer requestSeats;

    @NotNull (message = "request rooms is required")
    @Min(value = 0, message = "request rooms must be non-negative")
    private Integer requestRooms;

}

