package com.example.demo.api;

import com.example.demo.dto.BatchDto;
import com.example.demo.dto.ClassroomDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.BatchService;
import com.example.demo.service.ClassroomService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class StudentApiController {


    @Autowired
    private UserService userService;


    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @GetMapping("/students/all")
    public ResponseEntity<List<UserDto>> getStudents() {
        return new ResponseEntity<>(convertUserDto(userService.getStudents()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDetails.getUsername());
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        if (user.getBatch() != null) {
            userDto.setBatch(user.getBatch().getBatchCode());
        }
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole().getName());


        return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
    }


    public List<UserDto> convertUserDto(List<User> list) {
        List<UserDto> Dto = new ArrayList<>();
        for (User user : list) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole().getName());

            if (user.getBatch() != null) {
                userDto.setBatch(user.getBatch().getBatchCode());
            }

            Dto.add(userDto);
        }
        return Dto;
    }
//    @PreAuthorize("hasAnyAuthority('ADMIN','STUDENT')")


}

