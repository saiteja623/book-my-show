package com.example.bookMyShow.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BookingRequest {

    private Long userId;
    private Long showId;

    public List<Long> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
    }

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @JsonProperty("seats")
    private List<Long> seatIds;
}
