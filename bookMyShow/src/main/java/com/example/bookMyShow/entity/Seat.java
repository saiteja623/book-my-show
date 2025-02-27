package com.example.bookMyShow.entity;

import com.example.bookMyShow.model.SeatType;
import jakarta.persistence.*;
import lombok.*;

@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private Boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @Version
    private Long version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean booked) {
        isBooked = booked;
    }

    public Show getShow() {
        return show;
    }

    public Seat() {
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }




}
