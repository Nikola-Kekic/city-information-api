package com.example.cityinformationapi.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginDto implements Dto{
    private String email;
    private String password;

}
