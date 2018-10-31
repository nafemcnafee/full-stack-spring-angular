package com.airbnb.fullstackspring.converter;

import com.airbnb.fullstackspring.entity.RoomEntity;
import com.airbnb.fullstackspring.model.Links;
import com.airbnb.fullstackspring.model.Self;
import com.airbnb.fullstackspring.model.response.ReservableRoomResponse;
import com.airbnb.fullstackspring.rest.ResourceConstants;
import org.springframework.core.convert.converter.Converter;




public class RoomEntityToReservableRoomResponseConverter implements Converter<RoomEntity, ReservableRoomResponse>{

    @Override
    public ReservableRoomResponse convert(RoomEntity source) {

        ReservableRoomResponse reservableRoomResponse = new ReservableRoomResponse();
        reservableRoomResponse.setRoomNumber(source.getRoomNumber());
        reservableRoomResponse.setPrice(Integer.valueOf(source.getPrice()));

        Links links = new Links();
        Self self = new Self();
        self.setRef(ResourceConstants.ROOM_RESERVATION_V1 + "/" + source.getId());
        links.setSelf(self);
        reservableRoomResponse.setLinks(links);

        return reservableRoomResponse;
    }
}