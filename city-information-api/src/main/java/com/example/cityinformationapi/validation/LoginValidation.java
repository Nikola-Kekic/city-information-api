package com.example.cityinformationapi.validation;

import com.example.cityinformationapi.dto.LoginDto;
import org.springframework.stereotype.Component;


@Component
public class LoginValidation {
    public boolean validate(LoginDto dto) {
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$";

        return !dto.getEmail().matches(emailRegex) || dto.getPassword().isEmpty();
    }
}


