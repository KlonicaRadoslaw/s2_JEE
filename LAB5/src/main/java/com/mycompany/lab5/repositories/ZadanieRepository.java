/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.lab5.repositories;

import com.mycompany.lab5.entities.Zadanie;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author student
 */
public interface ZadanieRepository extends CrudRepository<Zadanie, Long>{
    Iterable<Zadanie> findByWykonane(boolean wykonane);
    Iterable<Zadanie> findByKosztLessThan(double koszt);
    Iterable<Zadanie> findByKosztBetween(double min, double max);
}
