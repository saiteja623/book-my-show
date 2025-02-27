package com.example.bookMyShow.repository;

import com.example.bookMyShow.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show,Long> {

    @Query("SELECT s FROM Show s " +
            "JOIN s.movie m " +
            "JOIN s.screen sc " +
            "JOIN sc.theater t " +
            "WHERE m.title = :movieTitle AND t.location = :location")
    List<Show> findShowsByMovieTitleAndTheaterLocation(@Param("movieTitle") String movieTitle,
                                                       @Param("location") String location);
}
