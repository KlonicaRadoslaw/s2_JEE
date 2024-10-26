/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab5world.controllers;

import com.mycompany.lab5world.entities.Country;
import com.mycompany.lab5world.repositories.CountryRepository;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author student
 */
@Controller
public class PageController {

    @Autowired
    public CountryRepository repository;

    @RequestMapping("/country/{continent}")
    @ResponseBody
    public String getCountriesByContinent(@PathVariable String continent){
        StringBuilder sb = new StringBuilder();
        for (Country c: repository.findByContinent(continent)) {
            sb.append(c).append("<br>");
        }
        return sb.toString();
    }
    @RequestMapping("/country/population/{min}/{max}")
    @ResponseBody
    public String getCountriesByPopulation(@PathVariable int min, @PathVariable int max){
        StringBuilder sb = new StringBuilder();
        for (Country c: repository.findByPopulationBetween(min, max)) {
            sb.append(c).append("<br>");
        }
        return sb.toString();
    }
    @RequestMapping("/country/{continent}/surfacearea/{min}/{max}")
    @ResponseBody
    public String getCountriesByContinentAndPopulation(@PathVariable String continent, @PathVariable BigDecimal min, @PathVariable BigDecimal max){
        StringBuilder sb = new StringBuilder();
        for (Country c: repository.findByContinentAndSurfaceAreaBetween(continent, min, max)) {
            sb.append(c).append("<br>");
        }
        return sb.toString();
    }
    
}
