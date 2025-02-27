package com.example.bookMyShow.service;

import com.example.bookMyShow.dto.BookingDTO;
import com.example.bookMyShow.dto.SeatDTO;
import com.example.bookMyShow.model.BookingConfirmedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;

@Service
public class SeatBookedEventListener {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private KafkaTemplate<String, BookingDTO> kafkaTemplate;

    private final String BOOKING_TOPIC = "bookings";

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleSeatBookedEvent(BookingConfirmedEvent event){
        removeBookedSeatFromCache(event);
        sendBookingDetailsToKafka(event.getBookingDetails());

    }

    private void removeBookedSeatFromCache(BookingConfirmedEvent event){
        Long showId = event.getShowId();
        List<Long> bookedSeatIds = event.getSeatId();

            Cache cache = cacheManager.getCache("availableSeats");
            List<SeatDTO> cachedSeats = cache.get(showId, List.class);
            cachedSeats.removeIf(seat -> bookedSeatIds.contains(seat.getSeatId()));

            cache.put(showId, cachedSeats);
    }

    private void sendBookingDetailsToKafka(BookingDTO bookingDetails) {
        try {
            kafkaTemplate.send(BOOKING_TOPIC, String.valueOf(bookingDetails.getBookingId()), bookingDetails);
        } catch (Exception e) {
            System.err.println("Error sending booking details to Kafka: " + e.getMessage());
        }
    }
}
