package com.example.cityinformationapi.unit;

import com.example.cityinformationapi.dto.CityDto;
import com.example.cityinformationapi.model.City;
import com.example.cityinformationapi.repository.CityRepository;
import com.example.cityinformationapi.service.impl.CityServiceImpl;
import com.example.cityinformationapi.mapper.CityMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CityServiceTest {

    @Mock
    private CityMapper mapper;
    @Mock
    private CityRepository cityRepository;
    @InjectMocks
    private CityServiceImpl cityService;
    private CityDto cityDto;
    private City cityModel;

    @BeforeEach
    public void setUp() {
        cityDto = new CityDto( "Vienna", "Austria",  "Austria", 1897000,  6.2);
        cityModel = new City(1L, cityDto.getName(), cityDto.getCountry(), cityDto.getStateOrRegion(), cityDto.getPopulation(),  cityDto.getTempCelsius());
    }

    @Test
    public void testSaveCity_Success() {
        // Arrange
        when(cityRepository.findByNameAndCountry(cityDto.getName(), cityDto.getCountry())).thenReturn(Optional.empty());
        when(mapper.toEntity(cityDto)).thenReturn(cityModel);
        when(cityRepository.save(cityModel)).thenReturn(cityModel);

        // Act
        Optional<City> savedCity = cityService.save(cityDto);

        // Assert
        assertTrue(savedCity.isPresent());
        assertEquals(cityDto.getName(), savedCity.get().getName());
        assertEquals(cityDto.getCountry(), savedCity.get().getCountry());
        assertEquals(cityDto.getStateOrRegion(), savedCity.get().getStateOrRegion());
        verify(cityRepository, times(1)).findByNameAndCountry(anyString(), anyString());
        verify(cityRepository, times(1)).save(cityModel);
    }

    @Test
    public void testSaveCity_DuplicateCity() {
        // Arrange
        when(cityRepository.findByNameAndCountry(cityDto.getName(), cityDto.getCountry())).thenReturn(Optional.of(cityModel));

        // Act

        // Assert
        assertThrows(DataIntegrityViolationException.class, () -> cityService.save(cityDto));
        verify(cityRepository, times(1)).findByNameAndCountry(anyString(), anyString());
        verify(cityRepository, times(0)).save(any());
    }


}
