package com.example.bookMyShow.service;

import com.example.bookMyShow.dto.ShowDTO;
import com.example.bookMyShow.entity.Show;
import com.example.bookMyShow.repository.ShowRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowService {

    private final ShowRepository showRepository;


    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @Cacheable(value = "showsBYMovieAndLocation", key = "#movieTitle + ':' + #location")
    public List<ShowDTO> findAllMovieShows(String movieTitle, String location) {
        return showRepository.findShowsByMovieTitleAndTheaterLocation(movieTitle,location).stream().map(ShowDTO::new).collect(Collectors.toList());
    }
}
