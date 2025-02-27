package com.example.bookMyShow.service;

import com.example.bookMyShow.dto.SeatDTO;
import com.example.bookMyShow.entity.Seat;
import com.example.bookMyShow.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatService {

    private final SeatRepository seatRepository;

    @Autowired
    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    @Cacheable(value =  "availableSeats" , key = "#showId")
    public List<SeatDTO> getAvailableSeatByShowId(Long showId){
        return seatRepository.findAvailableSeatsByShowId(showId).stream().map(SeatDTO::new).collect(Collectors.toList());
    }
}
