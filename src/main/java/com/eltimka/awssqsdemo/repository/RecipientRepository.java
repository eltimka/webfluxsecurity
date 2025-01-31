package com.eltimka.awssqsdemo.repository;

import com.eltimka.awssqsdemo.entity.RecipientEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface RecipientRepository extends R2dbcRepository<RecipientEntity,String> {
}
