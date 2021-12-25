package com.example.demo;

import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.User;
import com.example.demo.service.BatchService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserServiceImplement;
import javafx.application.Application;
import lombok.AllArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class UserServiceTests {

 @Autowired
    private UserServiceImplement userService;

    @Test
    public void testGetStudents()  {
       List<User> students = userService.getStudents();

       boolean isExist = students.size()>0;
       assertTrue(isExist);
    }

    @Test
    public void testGetLecturer()  {
        List<User> lecturer = userService.getLecturers();

        boolean isExist = lecturer.size()>0;
        assertTrue(isExist);
    }
    @Test
    public void testGetUsers()  {
        List<User> users = userService.getUsers();

        boolean isExist = users.size()>0;
        assertTrue(isExist);
    }

}
