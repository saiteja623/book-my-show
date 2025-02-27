package com.example.bookMyShow.dto;

import com.example.bookMyShow.entity.Show;

import java.io.Serializable;
import java.time.LocalDateTime;


public class ShowDTO implements Serializable {

    private Long  showId;

    public Long getShowId() {
        return showId;
    }

    public void setShowId(Long showId) {
        this.showId = showId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieName ;

    private String screenName;
    private String theaterName;
    public  ShowDTO(Show  show){
        this.showId=show.getId();
        this.movieName=show.getMovie().getTitle();
        this.startTime = show.getStartTime();
        this.endTime = show.getEndTime();
        this.screenName= show.getScreen().getName();
        this.theaterName = show.getScreen().getTheater().getName();
    }



}
