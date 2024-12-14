/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.LAB10.converters;

import com.example.LAB10.model.UserDao;
import com.example.LAB10.model.UserDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 *
 * @author student
 */
@Component
@Mapper
public interface UserMapper {
    UserDao mapUserDtoToDao(UserDto user);
    UserDto mapUserDaoToDto(UserDao user);
}
