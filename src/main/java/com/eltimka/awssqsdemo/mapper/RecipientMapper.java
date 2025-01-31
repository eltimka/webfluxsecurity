package com.eltimka.awssqsdemo.mapper;

import com.eltimka.awssqsdemo.dto.NotificationDto;
import com.eltimka.awssqsdemo.dto.RecipientDto;
import com.eltimka.awssqsdemo.entity.NotificationEntity;
import com.eltimka.awssqsdemo.entity.RecipientEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipientMapper {

    RecipientDto map(RecipientEntity entity);

    @InheritInverseConfiguration

    RecipientEntity map(RecipientDto dto);
}
