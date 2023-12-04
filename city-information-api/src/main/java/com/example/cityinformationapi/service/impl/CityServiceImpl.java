package com.example.cityinformationapi.service.impl;

import com.example.cityinformationapi.dto.CreateCityDto;
import com.example.cityinformationapi.mapper.CityMapper;
import com.example.cityinformationapi.model.City;
import com.example.cityinformationapi.repository.CityRepository;
import com.example.cityinformationapi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Transactional
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper mapper;

    @Override
    public Optional<City> save(CreateCityDto createCityDto) {
        Optional<City> existingCity = cityRepository.findByNameAndCountry(createCityDto.getName(), createCityDto.getCountry());

        existingCity.ifPresent(city -> {
            throw new DataIntegrityViolationException("City with the same name and country already exists");
        });

        return Optional.of(cityRepository.save(mapper.toEntity(createCityDto)));

    }

}
