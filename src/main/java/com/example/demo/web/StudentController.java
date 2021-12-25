package com.example.demo.web;

import com.example.demo.dto.BatchDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.User;
import com.example.demo.service.BatchService;
import com.example.demo.service.EmailService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private UserService userService;
    @Autowired
    private BatchService batchService;

    @Autowired
    private EmailService emailService;


    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/createStudent")
    public ModelAndView createStudentAccount(@Valid @ModelAttribute("registerUser") UserDto registrationDto) {
        ModelAndView model = getUsers();
        try {

            userService.registerStudent(registrationDto);
            emailService.sendSampleMessage(registrationDto.getEmail(), "Your account is created. Please login with your username as the password and change the password");
            model = getUsers();
            model.addObject("success", "Successfully added a student");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }

        return model;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/update")
    public ModelAndView updateStudentAccount(@RequestParam String username, @RequestParam long studentId, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String email, @RequestParam String batch) {
        ModelAndView model = getUsers();
        try {

            UserDto student = new UserDto();
            student.setEmail(email);
            student.setUsername(username);
            student.setId(studentId);
            student.setFirstName(first_name);
            student.setLastName(last_name);
            student.setBatch(batch);

            userService.updateStudent(student);
            model = getUsers();
            model.addObject("success", "Successfully updated student");

        } catch (Exception| CustomException e) {
            model.addObject("error", e.getMessage());
        }

        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @GetMapping(path = "/viewStudents")
    public ModelAndView viewStudents() {
        return getUsers();
    }

    public ModelAndView getUsers() {
        ModelAndView model = new ModelAndView();
        List<User> studentList = userService.getStudents();
        List<UserDto> studentDtos = convertUserDto(studentList);

        model.addObject("registerUser", new UserDto());
        List<Batch> batches = batchService.getBatches();
        List<BatchDto> batchDtos = convertBatchDto(batches);

//       UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addObject("batches", batchDtos);
        model.addObject("students", studentDtos);
        model.setViewName("viewStudents");
        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @GetMapping(path = "/viewStudentsByBatch/{id}")
    public ModelAndView getStudentsBybatch(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView();
        List<User> studentList = userService.getStudentsByBatch(id);
        List<UserDto> studentDtos = convertUserDto(studentList);

        model.addObject("registerUser", new UserDto());
        List<Batch> batches = batchService.getBatches();
        List<BatchDto> batchDtos = convertBatchDto(batches);

//        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addObject("batches", batchDtos);
        model.addObject("students", studentDtos);
        model.setViewName("viewStudents");
        return model;
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/removeStudent/{id}")
    public ModelAndView removeUser(@PathVariable("id") long id) {
        ModelAndView model = getUsers();
        try {
            userService.deleteUser(id);
            model = getUsers();
            model.addObject("success", "Successfully removed a student");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }

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

    public List<BatchDto> convertBatchDto(List<Batch> list) {
        List<BatchDto> Dto = new ArrayList<>();
        for (Batch batch : list) {
            BatchDto batchDto = new BatchDto();
            batchDto.setId(batch.getId());
            batchDto.setBatchCode(batch.getBatchCode());
            batchDto.setCourse(batch.getCourse());
            batchDto.setYear(batch.getYear());

            Dto.add(batchDto);
        }
        return Dto;
    }
}
