package com.example.cityinformationapi.controller;

import com.example.cityinformationapi.dto.CityDto;
import com.example.cityinformationapi.service.CityService;
import com.example.cityinformationapi.validation.CityValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("city")
public class CityController {

    @Autowired
    private CityService cityService;
    @Autowired
    private CityValidation cityValidation;

    @PostMapping("/create")
    public ResponseEntity<?> createCity(@Valid @RequestBody CityDto cityDto) {

        if (!cityValidation.Validation(cityDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect input values!");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.save(cityDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateCity(@Valid @RequestBody CityDto cityDto) {

        if (!cityValidation.Validation(cityDto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect input values!");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.update(cityDto));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable Long id) {

        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.delete(id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> readAllCity() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.readAll());
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readCity(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cityService.readOne(id));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
