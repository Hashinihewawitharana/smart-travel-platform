package com.smarttravel.notificationservice.service;

import com.smarttravel.notificationservice.dto.NotificationRequestDTO;
import com.smarttravel.notificationservice.entity.Notification;
import com.smarttravel.notificationservice.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(NotificationRequestDTO dto) {
        Notification notification = new Notification(
                null,
                dto.getUserId(),
                dto.getMessage(),
                null
        );

        notificationRepository.save(notification);
        // Optional: integrate email or SMS sending here
    }
}
