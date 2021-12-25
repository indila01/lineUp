package com.example.demo.web;

import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.UserDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.BatchService;
import com.example.demo.service.SubjectService;
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
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping(path = "/viewSubjects")
    public ModelAndView viewAllSubjects(){
        return getSubjects();
    }

    public ModelAndView getSubjects() {
        ModelAndView model = new ModelAndView();
        List<Subject> subjectList = subjectService.getSubjects();
        List<SubjectDto> subjectDtos = convertSubjectDto(subjectList);
        List<User> lecturers = userService.getLecturers();
        List<UserDto> lecturersDto = convertUserDto(lecturers);

        model.addObject("lecturers", lecturersDto);
        model.addObject("subject", new SubjectDto());
        model.addObject("subjects", subjectDtos);

        model.setViewName("viewSubjects");

        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @PostMapping(path = "/createSubject")
    public ModelAndView createSubject(@ModelAttribute("registerSubject") SubjectDto subjectDto ) {
        ModelAndView model = getSubjects();
        try {
            subjectService.createSubject(subjectDto);
            model = getSubjects();
            model.addObject("success", "Successfully created a subject");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }


        return model;
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @GetMapping(path = "/viewSubjectsByBatch/{id}")
    public ModelAndView viewSubjectByBatch(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView();
        List<Subject> subjects = subjectService.getSubjectsByBatch(batchService.getBatchById(id));
        List<SubjectDto> subjectDtos = convertSubjectDto(subjects);


        String batchCode = (batchService.getBatchById(id)).getBatchCode();

        model.addObject("batch", batchCode);
        model.addObject("subjects", subjectDtos);

        model.setViewName("viewSubjectsByBatch");

        return model;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/removeSubject/{id}")
    public ModelAndView removeSubject(@PathVariable("id") long id) {
        ModelAndView model = getSubjects();
        try {
            subjectService.deleteSubject(id);
            model = getSubjects();
            model.addObject("success", "Successfully created a subject");
        }catch (Exception | CustomException e){
            model.addObject("error",e.getMessage());
        }

        return model;
    }

    public List<SubjectDto> convertSubjectDto(List<Subject> list) {
        List<SubjectDto> Dto = new ArrayList<>();
        for (Subject subject : list) {
            SubjectDto subjectDto = new SubjectDto();
            subjectDto.setId(subject.getId());
            subjectDto.setName(subject.getName());
            subjectDto.setUser(subject.getUser().getUsername());

            Dto.add(subjectDto);
        }
        return Dto;
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
