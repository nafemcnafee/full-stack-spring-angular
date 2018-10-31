package com.airbnb.fullstackspring.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity                     //Rewrite this RoomEntity.java
@Table(name = "Room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    private  Integer roomNumber;

    @NotNull
    private String price;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<ReservationEntity> reservationEntityList;

    public RoomEntity() {
        super();
    }

    public Long getId() {
        return id;
    }

    public RoomEntity(@NotNull Integer roomNumber, @NotNull String price) {
        this.roomNumber = roomNumber;
        this.price = price;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<ReservationEntity> getReservationEntityList() {
        return reservationEntityList;
    }

    public void setReservationEntityList(List<ReservationEntity> reservationEntityList) {
        this.reservationEntityList = reservationEntityList;
    }

    public void addReservationEntity(ReservationEntity reservationEntity){
        if(null == reservationEntityList){
            reservationEntityList = new ArrayList<>();
        }
        reservationEntityList.add(reservationEntity);
    }
}
