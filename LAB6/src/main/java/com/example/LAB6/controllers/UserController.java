/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB6.controllers;

import com.example.LAB6.dao.UserDao;
import com.example.LAB6.entity.User;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author student
 */
@Controller
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserDao dao;

    @GetMapping("/login")
    public String loginPage() {
//zwrócenie nazwy widoku logowania - login.html
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute(value = "user") User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPagePOST(@Valid User user, BindingResult binding) {
        if (dao.findByLogin(user.getLogin()) != null) {
            binding.rejectValue("login", "", "Login jest już zajęty");
        }
        if (binding.hasErrors()) {
            return "register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String profilePage(Model m, Principal principal) {
//dodanie do modelu aktualnie zalogowanego użytkownika:
        m.addAttribute("user", dao.findByLogin(principal.getName()));
//zwrócenie nazwy widoku profilu użytkownika - profile.html
        return "profile";
    }
    @GetMapping("/users")
    public String usersPage(Model m) {
        m.addAttribute("userList", dao.findAll());
        return "users";
    }
    
    @GetMapping("/account/delete")
    public String deleteAccount() {
        return "deleteaccount";
    }

    @PostMapping("/account/delete")
    public String deleteAccountPOST(Principal principal) {
        dao.delete(dao.findByLogin(principal.getName()));
        return "redirect:/logout";
    }

    @GetMapping("/account/changepassword")
    public String changePassword(@ModelAttribute(value = "password") String password) {
        return "changepassword";
    }

    @PostMapping("/account/changepassword")
    public String changePasswordPOST(@RequestParam String password, Principal principal, Model m) {
        if (password.trim().length() < 6) {
            m.addAttribute("passwordError", "Hasło musi mieć conajmniej 6 znaków");
            return "changepassword";
        }
        User user = dao.findByLogin(principal.getName());
        user.setPassword(passwordEncoder.encode(password));
        dao.save(user);
        return "redirect:/logout";
    }

    @GetMapping("/profile/edit")
    public String editProfile(Model m, Principal principal) {
        m.addAttribute(dao.findByLogin(principal.getName()));
        return "editprofile";
    }

    @PostMapping("/profile/edit")
    public String editProfilePOST(@Valid User form, BindingResult binding, Principal principal) {
        if (!form.getLogin().equals(principal.getName()) && dao.findByLogin(form.getLogin()) != null) {
            binding.rejectValue("login", "", "Login jest już zajęty");
        }
        if (binding.hasErrors()) {
            return "editprofile";
        }
        User user = dao.findByLogin(principal.getName());
        boolean logout = !user.getLogin().equals(form.getLogin());
        user.setLogin(form.getLogin());
        user.setName(form.getName());
        user.setSurname(form.getSurname());
        dao.save(user);
        return logout ? "redirect:/logout" : "profile";
    }
}
