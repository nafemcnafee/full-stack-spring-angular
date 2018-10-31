package com.airbnb.fullstackspring.repository;



import com.airbnb.fullstackspring.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

import javax.validation.constraints.NotNull;

import java.util.Optional;


public interface RoomRepository extends CrudRepository<RoomEntity, Long> {

    Optional<RoomEntity> findById(@NotNull Long id);


}
