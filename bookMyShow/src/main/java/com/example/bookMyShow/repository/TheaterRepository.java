package com.example.bookMyShow.repository;

import com.example.bookMyShow.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater,Long> {

    List<Theater> findByLocation(String location);
}
