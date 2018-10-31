package com.airbnb.fullstackspring.converter;

import com.airbnb.fullstackspring.entity.ReservationEntity;
import com.airbnb.fullstackspring.model.response.ReservationResponse;
import org.springframework.core.convert.converter.Converter;

public class ReservationEntityToReservationResponseConverter implements Converter<ReservationEntity, ReservationResponse> {
    @Override
    public ReservationResponse convert(ReservationEntity source) {
        ReservationResponse reservationResponse = new ReservationResponse();
        reservationResponse.setCheckin(source.getCheckin());
        reservationResponse.setCheckout(source.getCheckout());



        return reservationResponse;
    }
}
