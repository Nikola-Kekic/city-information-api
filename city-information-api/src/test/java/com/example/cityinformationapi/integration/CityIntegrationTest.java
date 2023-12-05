package com.example.cityinformationapi.integration;

import com.example.cityinformationapi.client.WeatherApiClient;
import static org.junit.jupiter.api.Assertions.*;

import com.example.cityinformationapi.model.City;
import com.example.cityinformationapi.repository.CityRepository;
import com.example.cityinformationapi.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;


@SpringBootTest
public class CityIntegrationTest {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherApiClient weatherApiClient;

    @Autowired
    private CityService cityService;

    private City cityFromRepository;

    @BeforeEach
    public void setUp() {
        cityFromRepository = new City(1L, "Belgrade", "Serbia", "Belgrade", 1500000, 0);
    }

    @Test
    public void testReadOne() {
        // Arrange
        double tempCelsius = weatherApiClient.getWeatherInfo(cityFromRepository.getName());

        // Act
        Optional<City> city = cityService.readOne(cityFromRepository.getId());

        // Assert
        assertTrue(city.isPresent());
        assertEquals(cityFromRepository.getId(), city.get().getId());
        assertEquals(cityFromRepository.getName(), city.get().getName());
        assertEquals(cityFromRepository.getCountry(), city.get().getCountry());
        assertEquals(tempCelsius, city.get().getTempCelsius());

    }

}
