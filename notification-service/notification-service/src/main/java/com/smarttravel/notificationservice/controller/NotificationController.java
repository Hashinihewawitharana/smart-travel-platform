package com.smarttravel.notificationservice.controller;

import com.smarttravel.notificationservice.dto.NotificationRequestDTO;
import com.smarttravel.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Void> sendNotification(@RequestBody NotificationRequestDTO notificationDTO) {
        notificationService.sendNotification(notificationDTO);
        return ResponseEntity.ok().build();
    }
}


