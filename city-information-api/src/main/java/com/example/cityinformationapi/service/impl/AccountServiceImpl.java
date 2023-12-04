package com.example.cityinformationapi.service.impl;

import com.example.cityinformationapi.dto.LoginDto;
import com.example.cityinformationapi.model.Account;
import com.example.cityinformationapi.model.UserInfoDetails;
import com.example.cityinformationapi.repository.AccountRepository;
import com.example.cityinformationapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AccountServiceImpl implements AccountService, UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public Optional<Account> login(LoginDto dto) {

        Optional<Account> account = accountRepository.findByEmail(dto.getEmail());

        if(account.isEmpty()){
            throw new DataIntegrityViolationException("Account with the email and password doesn't exist");
        }

        return account;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Account> account = accountRepository.findByEmail(email);

        // Converting userDetail to UserDetails
        return account.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + email));
    }
}
