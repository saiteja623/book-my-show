package com.example.bookMyShow.model;

import com.example.bookMyShow.dto.BookingDTO;

import java.util.List;

public class BookingConfirmedEvent {

    private Long showId;
    private List<Long> seatId;
    private BookingDTO bookingDetails;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public List<Long> getSeatId() {
        return seatId;
    }

    public void setSeatId(List<Long> seatId) {
        this.seatId = seatId;
    }

    public BookingDTO getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDTO bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public BookingConfirmedEvent(Long showId, List<Long> seatId, BookingDTO bookingDetails) {
        this.showId = showId;
        this.seatId = seatId;
        this.bookingDetails = bookingDetails;
    }
}
