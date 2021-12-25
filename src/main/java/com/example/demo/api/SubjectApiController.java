package com.example.demo.api;

import com.example.demo.dto.SubjectDto;
import com.example.demo.model.Subject;
import com.example.demo.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class SubjectApiController {

    @Autowired
    private SubjectService subjectService;

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping("/subjects/all")
    public ResponseEntity<List<SubjectDto>> getSubjects() {
        return new ResponseEntity<>(convertSubjectDto(subjectService.getSubjects()), HttpStatus.OK);
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
}
