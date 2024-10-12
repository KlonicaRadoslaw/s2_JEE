/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab2;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 *
 * @author student
 */
public class LoanBean implements Serializable{
    private double kwota = 1000;
    private double procent=10;
    private int lrat=10;

    public double getKwota() {
        return kwota;
    }

    public void setKwota(double kwota) {
        this.kwota = Math.abs(kwota);
    }

    public double getProcent() {
        return procent;
    }

    public void setProcent(double procent) {
        this.procent = Math.abs(procent);
    }

    public int getLrat() {
        return lrat;
    }

    public void setLrat(int lrat) {
        this.lrat = Math.abs(lrat);
    }
    
    public String getRata(){
        try{
            Double p=procent/12/100;
            Double rata = (kwota*p)/(1-(1/(Math.pow((1+p),lrat))));
            DecimalFormat df = new DecimalFormat("#.00");
            return df.format(rata);
        }
        catch(Exception e){}
        return "";
    }
    
    
}
