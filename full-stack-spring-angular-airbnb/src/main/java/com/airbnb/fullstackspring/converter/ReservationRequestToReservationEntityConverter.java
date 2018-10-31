package com.airbnb.fullstackspring.converter;

import com.airbnb.fullstackspring.entity.ReservationEntity;
import com.airbnb.fullstackspring.model.request.ReservationRequest;
import org.springframework.core.convert.converter.Converter;

public class ReservationRequestToReservationEntityConverter implements Converter<ReservationRequest, ReservationEntity> {


    @Override
    public ReservationEntity convert(ReservationRequest source) {
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setCheckin(source.getCheckin());
        reservationEntity.setCheckout(source.getCheckout());

        if (source.getId() != null) {
            reservationEntity.setId(source.getId());
        }

        return reservationEntity;
    }
}
