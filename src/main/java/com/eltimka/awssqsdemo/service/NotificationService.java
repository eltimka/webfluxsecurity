package com.eltimka.awssqsdemo.service;

import com.eltimka.awssqsdemo.dto.NotificationDto;
import com.eltimka.awssqsdemo.mapper.NotificationMapper;
import com.eltimka.awssqsdemo.repository.NotificationRepository;
import com.eltimka.awssqsdemo.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final NotificationMapper notificationMapper;

    public Mono<NotificationDto> findNotificationByUid(String uid) {
        return notificationRepository.findById(uid)
                .map(notificationMapper::map);
    }


        public Mono<NotificationDto> findNotificationWithRecipientByUid(String uid) {
            return notificationRepository.findById(uid)
                    .flatMap(notificationEntity-> recipientRepository.findById(notificationEntity.getRecipientUid())
                    .map(recipientEntity -> {
                        notificationEntity.setRecipient(recipientEntity);
                        return notificationEntity;
                    }).map(notificationMapper::map));

    }
}
