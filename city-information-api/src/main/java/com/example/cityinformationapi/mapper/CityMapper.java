package com.example.cityinformationapi.mapper;


import com.example.cityinformationapi.dto.CityDto;
import com.example.cityinformationapi.model.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper implements GenericMapper<City, CityDto>{

    @Override
    public City toEntity(CityDto dto) {
        return new City(dto.getName(), dto.getCountry(), dto.getStateOrRegion(), dto.getPopulation(), dto.getTempCelsius());
    }

    @Override
    public CityDto toDto(City entity) {
        return new CityDto(entity.getName(), entity.getCountry(), entity.getStateOrRegion(), entity.getPopulation(), entity.getTempCelsius());
    }
}
