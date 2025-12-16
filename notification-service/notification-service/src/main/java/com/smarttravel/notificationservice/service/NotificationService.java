package com.smarttravel.notificationservice.service;
import com.smarttravel.notificationservice.dto.NotificationRequestDTO;

public interface NotificationService {
    void sendNotification(NotificationRequestDTO notificationDTO);
}
