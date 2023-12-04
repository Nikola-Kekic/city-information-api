package com.example.cityinformationapi.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@jakarta.persistence.Entity
@Table( name = "city")
public class City implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "stateOrRegion", nullable = false)
    private String stateOrRegion;

    @Column(name = "population", nullable = false)
    private int population;

    @Column(name = "tempCelsius", nullable = false)
    private int tempCelsius;


    public City(){
    }

    public City(String name, String country, String stateOrRegion, int population, int tempCelsius) {
        this.name = name;
        this.country =country;
        this.stateOrRegion = stateOrRegion;
        this.population = population;
        this.tempCelsius = tempCelsius;
    }
}
