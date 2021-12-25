package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.User;
import com.example.demo.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService {


    User registerStudent(UserDto registrationDto) throws CustomException;

    User registerLecturer(UserDto registrationDto) throws CustomException;


    List<User> getUsers();

    List<User> getStudents();

    List<User> getLecturers();

    User getUser(String username);

    void deleteUser(Long id) throws CustomException;
    void updateStudent(UserDto student) throws CustomException;

    void changePassword(String username, String password) throws CustomException;

    List<User> getStudentsByBatch(long batchId);
}
