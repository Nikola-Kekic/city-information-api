package com.example.cityinformationapi.service;

import com.example.cityinformationapi.dto.CreateCityDto;
import com.example.cityinformationapi.model.City;

import java.util.Optional;

public interface CityService {
    Optional<City> save(CreateCityDto createCityDto);
}
