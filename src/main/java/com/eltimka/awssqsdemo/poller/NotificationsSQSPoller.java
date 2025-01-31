package com.eltimka.awssqsdemo.poller;

import com.eltimka.awssqsdemo.dto.NotificationDto;
import com.eltimka.awssqsdemo.entity.NotificationEntity;
import com.eltimka.awssqsdemo.mapper.NotificationMapper;
import com.eltimka.awssqsdemo.repository.NotificationRepository;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.messaging.handler.annotation.Payload;
@Slf4j
@Component
@RequiredArgsConstructor

public class NotificationsSQSPoller {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    @SqsListener(value= "${sqs.notifications.queue.name}")
    public void receiveMessage(@Payload NotificationDto message){
        log.info("received notification: {}", message);
        NotificationEntity entity = notificationMapper.map(message);
        notificationRepository.save(entity).subscribe();
    }
}
