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

    @Query(value = "UPDATE city  SET state_or_region = :stateOrRegion, population = :population, temp_celsius = :tempCelsius WHERE name = :name and country = :country RETURNING *", nativeQuery = true)
    Optional<City> updateCity(
            @Param("name") String name,
            @Param("country") String country,
            @Param("stateOrRegion") String stateOrRegion,
            @Param("population") int population,
            @Param("tempCelsius") double tempCelsius
    );

    @Query(value = "DELETE FROM city WHERE id = ?1 RETURNING *", nativeQuery = true)
    Optional<City> deleteByCityId(Long id);

}
