package com.eltimka.awssqsdemo.repository;

import com.eltimka.awssqsdemo.entity.NotificationEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;

public interface NotificationRepository extends R2dbcRepository<NotificationEntity,String> {

    Flux<NotificationEntity> findAllByRecipientUid(String uid);
}
