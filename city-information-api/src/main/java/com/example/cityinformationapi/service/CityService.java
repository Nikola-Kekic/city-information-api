package com.example.cityinformationapi.service;

import com.example.cityinformationapi.dto.CityDto;
import com.example.cityinformationapi.model.City;

import java.util.Optional;

public interface CityService {
    Optional<City> save(CityDto cityDto);
    Optional<City> update(CityDto cityDto);
}
