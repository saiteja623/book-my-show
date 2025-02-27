package com.example.bookMyShow.controller;

import com.example.bookMyShow.dto.BookingDTO;
import com.example.bookMyShow.model.BookingRequest;
import com.example.bookMyShow.service.BookingCBService;
import com.example.bookMyShow.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private BookingDTO bookSeat(@RequestBody BookingRequest bookingRequest) throws InterruptedException {
        return bookingCBService.bookSeats(bookingRequest.getUserId(),bookingRequest.getShowId(),
                bookingRequest.getSeatIds());

//
//        Thread t1 = new Thread(()->{
//            try {
//                Thread.sleep(200);
//                bookingCBService.bookSeats(1L,4L,List.of(8L));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//
//        });
//
//        Thread t2 = new Thread(()->{
//            try {
//                Thread.sleep(100);
//                bookingCBService.bookSeats(2L,4L,List.of(8L));
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        Thread t3 = new Thread(()->{
//            bookingCBService.bookSeats(2L,4L,List.of(8L));
//        });
//
//        t1.start();
//        t2.start();
//        t3.start();
//
//        t1.join();
//        t2.join();
//        t3.join();

//        return null;

    }
}
