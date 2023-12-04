package com.example.cityinformationapi.repository;

import com.example.cityinformationapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    @Query(value = "SELECT * FROM city c WHERE c.name = ?1 and c.country = ?2 ", nativeQuery = true)
    Optional<City> findByNameAndCountry(String name, String country);
}
