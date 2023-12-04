package com.example.cityinformationapi.service;

import com.example.cityinformationapi.dto.LoginDto;
import com.example.cityinformationapi.model.Account;

import java.util.Optional;

public interface AccountService {
    Optional<Account> login(LoginDto dto);
}
