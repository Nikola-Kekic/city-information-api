package com.example.cityinformationapi.dto;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class CreateCityDto implements Dto{

    private String name;
    private String country;
    private String stateOrRegion;
    private int population;
    private int tempCelsius;

    public CreateCityDto(String name, String country, String stateOrRegion, int population, int tempCelsius) {
        this.name = name;
        this.country =country;
        this.stateOrRegion = stateOrRegion;
        this.population = population;
        this.tempCelsius = tempCelsius;
    }
}
