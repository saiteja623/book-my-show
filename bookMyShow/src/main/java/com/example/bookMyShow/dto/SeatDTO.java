package com.example.bookMyShow.dto;

import com.example.bookMyShow.entity.Seat;
import com.example.bookMyShow.model.SeatType;

public class SeatDTO {

    public Long getSeatId() {
        return seatId;
    }

    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }

    private Long seatId;
    private String seatNumber;
    private SeatType seatType;


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

    public SeatDTO(Seat seat){
        this.seatNumber =  seat.getSeatNumber();
        this.seatType = seat.getSeatType();
        this.seatId =  seat.getId();
    }
}
