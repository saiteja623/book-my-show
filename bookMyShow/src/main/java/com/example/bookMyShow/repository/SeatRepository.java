package com.example.bookMyShow.repository;

import com.example.bookMyShow.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {

    @Query("select s from Seat s WHERE s.show.id = :showId AND s.isBooked = false")
    List<Seat> findAvailableSeatsByShowId(@Param("showId") Long showId);

}
