package com.eltimka.awssqsdemo.mapper;

import com.eltimka.awssqsdemo.dto.NotificationDto;
import com.eltimka.awssqsdemo.entity.NotificationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationDto map(NotificationEntity entity);

    @InheritInverseConfiguration

    NotificationEntity map(NotificationDto dto);
}
