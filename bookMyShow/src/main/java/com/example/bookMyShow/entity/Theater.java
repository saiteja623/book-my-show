package com.example.bookMyShow.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
public class Theater {
    public Long getId() {
        return id;
    }

    public Theater() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return theaterName;
    }

    public void setName(String name) {
        this.theaterName = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Screen> getScreens() {
        return screens;
    }

    public void setScreens(List<Screen> screens) {
        this.screens = screens;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theaterName;

    private String location;

    @OneToMany(mappedBy = "theater",cascade =  CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Screen> screens;

}
