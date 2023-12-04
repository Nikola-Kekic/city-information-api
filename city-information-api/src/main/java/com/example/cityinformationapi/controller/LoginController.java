package com.example.cityinformationapi.controller;

import com.example.cityinformationapi.dto.LoginDto;
import com.example.cityinformationapi.service.JwtService;
import com.example.cityinformationapi.validation.LoginValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("user")
public class LoginController {

    @Autowired
    private LoginValidation loginValidation;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String authenticateAndGetToken(@RequestBody LoginDto dto) {
        if(loginValidation.validate(dto)){
            throw new UsernameNotFoundException("Invalid user parameters !");
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(dto.getEmail());
        } else {
            throw new UsernameNotFoundException("Invalid user request !");
        }
    }

}
