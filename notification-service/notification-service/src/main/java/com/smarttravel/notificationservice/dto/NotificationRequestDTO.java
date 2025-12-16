package com.smarttravel.notificationservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequestDTO {

    private Long id;
    private Long userId;
    private String message;
    private String status; // Optional
}

