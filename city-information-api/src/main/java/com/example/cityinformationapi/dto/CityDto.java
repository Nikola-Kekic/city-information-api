package com.example.cityinformationapi.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class CityDto implements Dto{

    private String name;
    private String country;
    private String stateOrRegion;
    private int population;
    private double tempCelsius;

    public CityDto(String name, String country, String stateOrRegion, int population, double tempCelsius) {
        this.name = name;
        this.country =country;
        this.stateOrRegion = stateOrRegion;
        this.population = population;
        this.tempCelsius = tempCelsius;
    }
}
