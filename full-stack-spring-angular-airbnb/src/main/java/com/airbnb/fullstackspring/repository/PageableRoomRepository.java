package com.airbnb.fullstackspring.repository;

import com.airbnb.fullstackspring.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;



public interface PageableRoomRepository extends PagingAndSortingRepository<RoomEntity, Long> {
    Page<RoomEntity> findById(Long id, Pageable page);

}
