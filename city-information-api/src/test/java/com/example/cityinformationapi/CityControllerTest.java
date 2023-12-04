package com.example.cityinformationapi;

import com.example.cityinformationapi.controller.CityController;
import com.example.cityinformationapi.dto.CityDto;
import com.example.cityinformationapi.model.City;
import com.example.cityinformationapi.service.impl.CityServiceImpl;
import com.example.cityinformationapi.validation.CityValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerTest {

    @Mock
    private CityServiceImpl cityService;
    @Mock
    private CityValidation cityValidation;
    @InjectMocks
    private CityController cityController;

    private final CityDto sentDto = new CityDto();
    private final Optional<City> receivedModel =  Optional.of(new City());


    @Test
    public void testCreateCity_Success() throws Exception {
        when(cityValidation.Validation(sentDto)).thenReturn(true);
        when(cityService.save(sentDto)).thenReturn(receivedModel);

        ResponseEntity<?> responseEntity = cityController.createCity(sentDto);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals( receivedModel, responseEntity.getBody());
        verify(cityService, times(1)).save(sentDto);
    }

    @Test
    public void testCreateCity_Fail() throws Exception {
        when(cityValidation.Validation(sentDto)).thenReturn(false);
        when(cityService.save(sentDto)).thenReturn(receivedModel);

        ResponseEntity<?> responseEntity = cityController.createCity(sentDto);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals( "Incorrect input values!", responseEntity.getBody());
        verify(cityService, times(0)).save(sentDto);
    }

}



