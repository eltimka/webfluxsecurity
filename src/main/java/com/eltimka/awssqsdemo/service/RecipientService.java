package com.eltimka.awssqsdemo.service;


import com.eltimka.awssqsdemo.dto.RecipientDto;
import com.eltimka.awssqsdemo.entity.NotificationEntity;
import com.eltimka.awssqsdemo.entity.RecipientEntity;
import com.eltimka.awssqsdemo.mapper.NotificationMapper;
import com.eltimka.awssqsdemo.mapper.RecipientMapper;
import com.eltimka.awssqsdemo.repository.NotificationRepository;
import com.eltimka.awssqsdemo.repository.RecipientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipientService {

    private final NotificationRepository notificationRepository;
    private final RecipientRepository recipientRepository;
    private final RecipientMapper recipientMapper;

    public Mono<RecipientDto> findRecipientWithNotificationsByUid(String uid) {
        return Mono.zip(recipientRepository.findById(uid),
                notificationRepository.findAllByRecipientUid(uid).collectList())
                .map(tuples ->{
                    RecipientEntity recipient = tuples.getT1();
                    List<NotificationEntity> notifications = tuples.getT2();
                    recipient.setNotifications(notifications);
                    return recipientMapper.map(recipient);
                });

    }
}
