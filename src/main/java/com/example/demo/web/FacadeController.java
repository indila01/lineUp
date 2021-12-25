package com.example.demo.web;

import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class FacadeController {

    @Autowired
    private UserService userService;


    @GetMapping("/")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'ADMIN', 'LECTURER')")
    public String facadeRedirect() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = (userService.getUser(userDetails.getUsername())).getRole().getName();

        if (role.equals("ADMIN")) {
            return "redirect:/student/viewStudents";
        } else if (role.equals("LECTURER")) {
            return "redirect:/subject/viewSubjects";
        } else {
            return "redirect:/class/searchClassroom";
        }
    }

    @GetMapping("/getProfile")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'ADMIN', 'LECTURER')")
    public ModelAndView getProfile(){
        return getProfileDetails();
    }

    public ModelAndView getProfileDetails() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUser(userDetails.getUsername());

        ModelAndView model = new ModelAndView();
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        if (user.getRole().getName().equals("STUDENT")) {
            userDto.setBatch(user.getBatch().getBatchCode());
        }


        model.addObject("user", userDto);
        model.setViewName("profile");
        return model;
    }

    @PostMapping("/changePassword")
    @PreAuthorize("hasAnyAuthority('STUDENT', 'ADMIN', 'LECTURER')")
    public ModelAndView changePassword(@ModelAttribute("user") UserDto user) {
        ModelAndView model = getProfileDetails();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userService.changePassword(userDetails.getUsername(),user.getPassword());
            model = getProfileDetails();
            model.addObject("success", "Success change password");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }

        return model;
    }

    @GetMapping("/login")
    public String login(String error, Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid Credentials");
        }
        return "login";
    }


}
