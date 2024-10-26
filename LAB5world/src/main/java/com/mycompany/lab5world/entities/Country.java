/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab5world.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 *
 * @author student
 */
@Entity
@Table(name = "country")
public class Country {

    @Id
    private String code;
    private String name;
    private String continent;
    private int population;

    private BigDecimal surfaceArea;

    @Override
    public String toString() {
        return "Kod: " + code +
                "\nNazwa: " + name +
                "\nKontynent: " + continent +
                "\nLiczba ludności: " + population +
                "\nPowierzchnia: " + surfaceArea;
    }
}
