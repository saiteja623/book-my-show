package com.example.bookMyShow.service;

import com.example.bookMyShow.dto.BookingDTO;
import com.example.bookMyShow.exception.BookingFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingCBService {

    @Autowired
    private  BookingService bookingService;

    @Retryable(value = ObjectOptimisticLockingFailureException.class, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public BookingDTO bookSeats(Long userId, Long showId, List<Long> seatIds) {
        System.out.println("In retry method for user " + userId);
        return bookingService.bookSeats(userId,showId,seatIds);
    }

    @Recover
    public BookingDTO bookSeatsFallBackMethod(ObjectOptimisticLockingFailureException ex,Long userId, Long showId, List<Long> seatIds) {
        System.out.println("In fall back method");
        throw new BookingFailedException("Failed to book the seats , Please try again!");
    }

}
