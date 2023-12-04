package com.example.cityinformationapi.controller;

import com.example.cityinformationapi.dto.CreateCityDto;
import com.example.cityinformationapi.model.City;
import com.example.cityinformationapi.service.CityService;
import com.example.cityinformationapi.validation.CityValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityValidation cityValidation;

    @PostMapping()
    public ResponseEntity<?> createCity(@Valid @RequestBody CreateCityDto createCityDto) {

        if (!cityValidation.Validation(createCityDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect input values!");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.save(createCityDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
