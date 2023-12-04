package com.example.cityinformationapi.service.impl;

import com.example.cityinformationapi.dto.LoginDto;
import com.example.cityinformationapi.model.Account;
import com.example.cityinformationapi.repository.AccountRepository;
import com.example.cityinformationapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Optional<Account> login(LoginDto dto) {

        Optional<Account> account = accountRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if(account.isEmpty()){
            throw new DataIntegrityViolationException("Account with the email and password doesn't exist");
        }

        return account;
    }
}
