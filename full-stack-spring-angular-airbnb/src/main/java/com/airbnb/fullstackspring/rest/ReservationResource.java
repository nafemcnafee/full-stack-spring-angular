package com.airbnb.fullstackspring.rest;



import com.airbnb.fullstackspring.converter.RoomEntityToReservableRoomResponseConverter;
import com.airbnb.fullstackspring.entity.ReservationEntity;
import com.airbnb.fullstackspring.entity.RoomEntity;
import com.airbnb.fullstackspring.model.request.ReservationRequest;
import com.airbnb.fullstackspring.model.response.ReservableRoomResponse;
import com.airbnb.fullstackspring.model.response.ReservationResponse;
import com.airbnb.fullstackspring.repository.PageableRoomRepository;
import com.airbnb.fullstackspring.repository.ReservationRepository;
import com.airbnb.fullstackspring.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping(ResourceConstants.ROOM_RESERVATION_V1)
@CrossOrigin
public class ReservationResource {

    @Autowired
    PageableRoomRepository pageableRoomRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    ConversionService conversionService;

    //Method: getting reservation info
    @RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Page<ReservableRoomResponse> getAvailableRooms(
            @RequestParam(value = "checkin")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkin,
            @RequestParam(value = "checkout")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                    LocalDate checkout, Pageable pageable){
        Page<RoomEntity> roomEntityList = pageableRoomRepository.findAll(pageable);

        return roomEntityList.map(x-> new RoomEntityToReservableRoomResponseConverter().convert(x));
        //return roomEntityList.map(new RoomEntityToReservableRoomResponseConverter());
    }

    @RequestMapping(path = "/{roomId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Object>getRoomById(@PathVariable Long roomId){

         // roomRepository.findById(roomId); //added below
        Optional<RoomEntity> roomEntity = roomRepository.findById(roomId);

        return new ResponseEntity<>(roomEntity,HttpStatus.OK);
        //return new ResponseEntity<>(roomRepository.findById(roomId),HttpStatus.OK);


    }

    //Method: creating reservation //FINISH THIS POST
    @RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservationResponse> createReservation(
            @RequestBody
                    ReservationRequest reservationRequest){

        ReservationEntity reservationEntity = conversionService.convert(reservationRequest, ReservationEntity.class );
        reservationRepository.save(reservationEntity);

        Optional<RoomEntity> roomEntity = roomRepository.findById(reservationRequest.getRoomId());
        //roomEntity.addReservationEntity(reservationEntity);
        roomEntity.ifPresent(x-> {
            x.addReservationEntity(reservationEntity);
        });
        roomEntity.ifPresent(roomRepository::save); //roomRepository.save(roomEntity);
        roomEntity.ifPresent(reservationEntity::setRoomEntity);

        ReservationResponse reservationResponse = conversionService.convert(reservationEntity, ReservationResponse.class);

        return new ResponseEntity<ReservationResponse>(reservationResponse, HttpStatus.CREATED);
    }

    //Method: updating reservation
    @RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReservableRoomResponse> updateReservation(
            @RequestBody ReservationRequest reservationRequest){
        return new ResponseEntity<>(new ReservableRoomResponse(), HttpStatus.OK);
    }

    //Method: delete reservation
    @RequestMapping(path = "/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteReservation(
            @PathVariable
            Long reservationId){
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
