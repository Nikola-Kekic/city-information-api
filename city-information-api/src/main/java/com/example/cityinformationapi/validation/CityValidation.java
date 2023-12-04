package com.example.cityinformationapi.validation;

import com.example.cityinformationapi.dto.CreateCityDto;
import com.example.cityinformationapi.util.CountryYamlReader;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CityValidation {

    public CountryYamlReader countryYamlReader;

    private CityValidation(CountryYamlReader countryYamlReader) {
        this.countryYamlReader = countryYamlReader;
    }
    public boolean Validation(CreateCityDto dto) {
        Map<String,Object> countries = countryYamlReader.readCountries();

        return !dto.getName().isEmpty() && countries.containsValue(dto.getCountry()) &&
                !dto.getCountry().isEmpty() && !dto.getStateOrRegion().isEmpty() && dto.getPopulation() > 0 &&
                !(dto.getTempCelsius() > 80 || dto.getTempCelsius() < -80);
    }

}
