package com.example.demo.web;

import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/lecturer")
public class LecturerController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/createLecturer")
    public ModelAndView createLecturerAccount(@ModelAttribute("registerUser") UserDto registrationDto) {
        ModelAndView model = getLecturers();
        try {
            userService.registerLecturer(registrationDto);
            model = getLecturers();
            model.addObject("success", "Successfully added a lecturer");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }

        return model;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/removeLecturer/{id}")
    public ModelAndView removeLecturer(@PathVariable("id") long id) {
        ModelAndView model = getLecturers();
        try {
            userService.deleteUser(id);
            model = getLecturers();
            model.addObject("success", "Successfully deleted a lecturer");
        } catch (Exception | CustomException e ) {
            model.addObject("error", e.getMessage());
        }
        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @GetMapping(path = "/viewLecturers")
    public ModelAndView viewLectuers(){
        return getLecturers();
    }


    public ModelAndView getLecturers() {
        ModelAndView model = new ModelAndView();
        model.addObject("registerUser", new UserDto());

        List<User> lecturerList = userService.getLecturers();
        List<UserDto> lecturerDtos = convertUserDto(lecturerList);
        model.addObject("lecturers", lecturerDtos);
        model.setViewName("viewLecturers");

        return model;
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
}
