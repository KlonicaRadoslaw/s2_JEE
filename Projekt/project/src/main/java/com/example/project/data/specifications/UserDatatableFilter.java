/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.project.data.specifications;
import com.example.project.data.entities.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
/**
 *
 * @author student
 */

public class UserDatatableFilter implements org.springframework.data.jpa.domain.Specification<User> {

    String userQuery;

    public UserDatatableFilter(String queryString) {
        this.userQuery = queryString;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        ArrayList<Predicate> predicates = new ArrayList<>();

        if (StringUtils.hasText(userQuery)) {
            String lowerCaseQuery = userQuery.toLowerCase();  // Convert query to lowercase

            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("username")), '%' + lowerCaseQuery + '%'));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), '%' + lowerCaseQuery + '%'));
        }

        return (!predicates.isEmpty() ? criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()])) : null);
    }
}
