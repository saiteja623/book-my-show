package com.example.bookMyShow.service;

import com.example.bookMyShow.dto.BookingDTO;
import com.example.bookMyShow.entity.Booking;
import com.example.bookMyShow.entity.Seat;
import com.example.bookMyShow.entity.Show;
import com.example.bookMyShow.exception.BookingFailedException;
import com.example.bookMyShow.model.BookingConfirmedEvent;
import com.example.bookMyShow.repository.BookingRepository;
import com.example.bookMyShow.repository.SeatRepository;
import com.example.bookMyShow.repository.ShowRepository;
import com.example.bookMyShow.repository.UserRepository;
import jakarta.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @Autowired
    public BookingService(SeatRepository seatRepository, BookingRepository bookingRepository, UserRepository userRepository, ShowRepository showRepository) {
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public BookingDTO bookSeats(Long userId, Long showId, List<Long> seatIds) {

        List<Seat> seats = seatRepository.findAllById(seatIds);
        Optional<Show> optionalShow = showRepository.findById(showId);

        seats.forEach(seat -> {
            if (seat.getIsBooked()) {
                System.out.println("Seat is already booked and cannot be booked for user " +  userId);
                throw new BookingFailedException("Seat " + seat.getSeatNumber() + " is already booked!");
            }
            seat.setIsBooked(true);
        });

        Booking booking = new Booking();
        booking.setUser(userRepository.findById(userId).get());
        booking.setBookingTime(LocalDateTime.now());
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setSeats(seats);

        BookingDTO bookingDTO = convertToBookingDto(booking, optionalShow.get().getMovie().getTitle(),
                optionalShow.get().getScreen().getTheater().getName(), optionalShow.get().getStartTime());

        seatRepository.saveAllAndFlush(seats);
        bookingRepository.saveAndFlush(booking);
        eventPublisher.publishEvent(new BookingConfirmedEvent(showId, seatIds, bookingDTO));
        return bookingDTO;


    }

    private BookingDTO convertToBookingDto(Booking booking, String movieName, String theaterName, LocalDateTime startTime) {
        return new BookingDTO(
                booking.getBookingId(),
                booking.getUser().getEmail(),
                booking.getUser().getUserName(),
                movieName,
                theaterName,
                booking.getSeats().stream().map(Seat::getSeatNumber).collect(Collectors.toList()),
                booking.getBookingTime(),
                startTime
        );
    }

}
