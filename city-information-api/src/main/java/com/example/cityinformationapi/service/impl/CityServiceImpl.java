package com.example.cityinformationapi.service.impl;

import com.example.cityinformationapi.client.WeatherApiClient;
import com.example.cityinformationapi.dto.CityDto;
import com.example.cityinformationapi.mapper.CityMapper;
import com.example.cityinformationapi.model.City;
import com.example.cityinformationapi.repository.CityRepository;
import com.example.cityinformationapi.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CityMapper mapper;
    @Autowired
    private WeatherApiClient weatherApiClient;

    @Override
    public Optional<City> save(CityDto cityDto) {
        Optional<City> existingCity = cityRepository.findByNameAndCountry(cityDto.getName(), cityDto.getCountry());

        existingCity.ifPresent(city -> {
            throw new DataIntegrityViolationException("City with the same name and country already exists");
        });

        return Optional.of(cityRepository.save(mapper.toEntity(cityDto)));
    }

    @Override
    public Optional<City> update(CityDto cityDto) {
        Optional<City> existingCity = cityRepository.findByNameAndCountry(cityDto.getName(), cityDto.getCountry());
        City city = mapper.toEntity(cityDto);

        if(existingCity.isEmpty()) {
            throw new DataIntegrityViolationException("City with that name and that country doesn't exist");
        }
        return cityRepository.updateCity(city.getName(), city.getCountry(), city.getStateOrRegion(), city.getPopulation(),city.getTempCelsius());
    }

    @Override
    public Optional<City> delete(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if(city.isEmpty()) {
            throw new DataIntegrityViolationException("City with that id doesn't exist");
        }
        return cityRepository.deleteByCityId(id);
    }

    @Override
    public List<City> readAll() {
        List<City> cities = cityRepository.findAll();

        if(cities.isEmpty()) {
            throw new DataIntegrityViolationException("There aren't any city in database");
        }
        return cities;
    }

    @Override
    public Optional<City> readOne(Long id) {
        Optional<City> city = cityRepository.findById(id);

        if(city.isEmpty()) {
            throw new DataIntegrityViolationException("City with that id doesn't exists");
        }
        city.get().setTempCelsius(this.weatherApiClient.getWeatherInfo(city.get().getName()));

        return city;
    }
}
