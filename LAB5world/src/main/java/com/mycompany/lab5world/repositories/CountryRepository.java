/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab5world.repositories;

import com.mycompany.lab5world.entities.Country;
import java.math.BigDecimal;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author student
 */
public interface CountryRepository extends CrudRepository<Country, String> {
    Iterable<Country> findByContinent(String continent);
    Iterable<Country> findByPopulationBetween(int min, int max);
    Iterable<Country> findByContinentAndSurfaceAreaBetween(String continent, BigDecimal min, BigDecimal max);
}
