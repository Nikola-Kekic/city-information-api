package com.example.cityinformationapi.service;

import com.example.cityinformationapi.dto.LoginDto;
import com.example.cityinformationapi.model.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface AccountService {
    Optional<Account> login(LoginDto dto);
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
}
