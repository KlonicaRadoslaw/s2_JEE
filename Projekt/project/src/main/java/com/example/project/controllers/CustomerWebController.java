/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.controllers;
import com.example.project.data.entities.Customer;
import com.example.project.data.repositories.CustomerRepository;
import com.example.project.services.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author student
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerWebController {

    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final ObjectMapper objectMapper;

    @GetMapping
    public String index() {
        return "/customer/index";
    }

    @GetMapping(value = "/data_for_datatable", produces = "application/json")
    @ResponseBody
    public String getDataForDatatable(@RequestParam Map<String, Object> params) {
        int draw = params.containsKey("draw") ? Integer.parseInt(params.get("draw").toString()) : 1;
        int length = params.containsKey("length") ? Integer.parseInt(params.get("length").toString()) : 30;
        int start = params.containsKey("start") ? Integer.parseInt(params.get("start").toString()) : 30;
        int currentPage = start / length;

        String sortName = "id";
        String dataTableOrderColumnIdx = params.get("order[0][column]").toString();
        String dataTableOrderColumnName = "columns[" + dataTableOrderColumnIdx + "][data]";
        if (params.containsKey(dataTableOrderColumnName))
            sortName = params.get(dataTableOrderColumnName).toString();
        String sortDir = params.containsKey("order[0][dir]") ? params.get("order[0][dir]").toString() : "asc";

        Sort.Order sortOrder = new Sort.Order((sortDir.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC), sortName);
        Sort sort = Sort.by(sortOrder);

        Pageable pageRequest = PageRequest.of(currentPage,
            length,
            sort);

        String queryString = (String) (params.get("search[value]"));

        Page<Customer> customers = customerService.getCustomersForDatatable(queryString, pageRequest);

        long totalRecords = customers.getTotalElements();

        List<Map<String, Object>> cells = new ArrayList<>();
        customers.forEach(customer -> {
            Map<String, Object> cellData = new HashMap<>();
            cellData.put("id", customer.getId());
            cellData.put("firstName", customer.getFirstName());
            cellData.put("lastName", customer.getLastName());
            cellData.put("emailAddress", customer.getEmailAddress());
            cellData.put("city", customer.getCity());
            cellData.put("country", customer.getCountry());
            cellData.put("phoneNumber", customer.getPhoneNumber());
            cells.add(cellData);
        });

        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("draw", draw);
        jsonMap.put("recordsTotal", totalRecords);
        jsonMap.put("recordsFiltered", totalRecords);
        jsonMap.put("data", cells);

        String json = null;
        try {
            json = objectMapper.writeValueAsString(jsonMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return json;
    }

    @GetMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        Customer customerInstance = customerRepository.findById(Long.valueOf(id)).get();

        model.addAttribute("customerInstance", customerInstance);

        return "/customer/show";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Customer customerInstance = customerRepository.findById(Long.valueOf(id)).get();

        model.addAttribute("customerInstance", customerInstance);

        return "/customer/edit";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("customerInstance") Customer customerInstance,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes atts) {
        if (bindingResult.hasErrors()) {
            return "/customer/edit";
        } else {
            if (customerRepository.save(customerInstance) != null)
                atts.addFlashAttribute("message", "Aktualizacja klienta przebiegła pomyślnie");
            else
                atts.addFlashAttribute("message", "Aktualizacja klienta nie powiodła się.");

            return "redirect:/customer";
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("customerInstance", new Customer());
        return "/customer/create";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("customerInstance") Customer customerInstance,
                       BindingResult bindingResult,
                       Model model,
                       RedirectAttributes atts) {
        if (bindingResult.hasErrors()) {
            return "/customer/create";
        } else {
            if (customerRepository.save(customerInstance) != null)
                atts.addFlashAttribute("message", "Klient został pomyślnie utworzony");
            else
                atts.addFlashAttribute("message", "Tworzenie klienta nie powiodło się.");

            return "redirect:/customer";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, RedirectAttributes atts) {
        Customer customerInstance = customerRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Klient nie został odnaleziony:" + id));

        customerRepository.delete(customerInstance);

        atts.addFlashAttribute("message", "Usunięto klienta.");

        return "redirect:/customer";
    }

}
