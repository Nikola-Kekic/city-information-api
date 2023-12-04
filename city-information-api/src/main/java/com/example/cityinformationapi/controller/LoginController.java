package com.example.cityinformationapi.controller;

import com.example.cityinformationapi.dto.LoginDto;
import com.example.cityinformationapi.service.AccountService;
import com.example.cityinformationapi.validation.LoginValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("user")
public class LoginController {

    @Autowired
    private AccountService userService;
    @Autowired
    private LoginValidation loginValidation;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto dto) {
        if (loginValidation.Validation(dto)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Incorrect input values!");
        }
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.login(dto));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
