package com.smarttravel.bookingservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class NotificationRequestDTO {
    private Long id;
    private Long userId;
    private String message;
    private LocalDateTime timestamp;
}



