package com.example.cityinformationapi.mapper;


import com.example.cityinformationapi.dto.CreateCityDto;
import com.example.cityinformationapi.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements GenericMapper<City, CreateCityDto>{

    @Override
    public City toEntity(CreateCityDto dto) {
        return new City(dto.getName(), dto.getCountry(), dto.getStateOrRegion(), dto.getPopulation(), dto.getTempCelsius());
    }

    @Override
    public CreateCityDto toDto(City entity) {
        return new CreateCityDto(entity.getName(), entity.getCountry(), entity.getStateOrRegion(), entity.getPopulation(), entity.getTempCelsius());
    }
}
