package com.example.demo.api;

import com.example.demo.dto.ClassroomDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.service.AuthService;
import com.example.demo.service.BatchService;
import com.example.demo.dto.BatchDto;
import com.example.demo.dto.LoginRequest;
import com.example.demo.service.ClassroomService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class auth {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;



    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        try {
            return new ResponseEntity<>(authService.login(request), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @PutMapping("/password")
    public ResponseEntity<Object> updatePassword(@RequestBody LoginRequest request) {
        try {
            userService.changePassword(request.getUsername(),request.getPassword());
            return new ResponseEntity<>("success", HttpStatus.OK);

        } catch (Exception | CustomException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
