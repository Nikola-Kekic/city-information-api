package com.example.cityinformationapi.repository;

import com.example.cityinformationapi.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM account a WHERE a.email = ?1 and a.password = ?2 ", nativeQuery = true)
    Optional<Account> findByEmailAndPassword(String email, String password);
}
