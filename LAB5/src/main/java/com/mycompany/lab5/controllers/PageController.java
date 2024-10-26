/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.lab5.controllers;

import com.mycompany.lab5.entities.Zadanie;
import com.mycompany.lab5.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author student
 */
@Controller
public class PageController {

    @Autowired
    public ZadanieRepository rep;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie = new Zadanie();
//korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        rep.save(zadanie);
//korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
    
    @RequestMapping("/generate")
    public RedirectView generujZadania() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie;
        double koszt = 1000;
        boolean wykonane = false;
        for (int i = 1; i <= 10; i++) {
            zadanie = new Zadanie();
            zadanie.setNazwa("zadanie " + i);
            zadanie.setOpis("Opis czynnosci do wykonania w zadaniu "+i);
            zadanie.setKoszt(koszt);
            zadanie.setWykonane(wykonane);
            wykonane = !wykonane;
            koszt += 200.5;
            rep.save(zadanie);
        }
        for(Zadanie i: rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return new RedirectView("listaZadan");
    }
    
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable long id){
        rep.deleteById(id);
    }
    
    @RequestMapping("/listaZadanWykonane/{wykonane}")
    @ResponseBody
    public String listaZadanWykonane(@PathVariable boolean wykonane){
        StringBuilder sb = new StringBuilder();
        for (Zadanie z: rep.findByWykonane(wykonane)) {
            sb.append(z).append("<br>");
        }
        return sb.toString();
    }
    @RequestMapping("/listaZadan/kosztMniejszyNiz/{koszt}")
    @ResponseBody
    public String listaZadanKosztMniejszyNiz(@PathVariable double koszt){
        StringBuilder sb = new StringBuilder();
        for (Zadanie z: rep.findByKosztLessThan(koszt)) {
            sb.append(z).append("<br>");
        }
        return sb.toString();
    }
    @RequestMapping("/listaZadan/kosztPomiedzy/{min}/{max}")
    @ResponseBody
    public String listaKosztPomiedzy(@PathVariable double min, @PathVariable double max){
        StringBuilder sb = new StringBuilder();
        for (Zadanie z: rep.findByKosztBetween(min, max)) {
            sb.append(z).append("<br>");
        }
        return sb.toString();
    }
    
    
}
