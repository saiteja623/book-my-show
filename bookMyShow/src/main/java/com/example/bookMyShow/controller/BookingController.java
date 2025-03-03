package com.example.bookMyShow.controller;

import com.example.bookMyShow.dto.BookingDTO;
import com.example.bookMyShow.model.BookingRequest;
import com.example.bookMyShow.service.BookingCBService;
import com.example.bookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingController {

    private final BookingCBService bookingCBService;

    @Autowired
    public BookingController(BookingCBService bookingCBService) {
        this.bookingCBService = bookingCBService;
    }

    @PostMapping("/book-seats")
    private ResponseEntity<BookingDTO> bookSeat(@RequestBody BookingRequest bookingRequest) throws InterruptedException {
        BookingDTO bookingDTO = bookingCBService.bookSeats(bookingRequest.getUserId(),bookingRequest.getShowId(),
                bookingRequest.getSeatIds());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDTO);
    }
}
