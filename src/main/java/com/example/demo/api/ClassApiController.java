package com.example.demo.api;

import com.example.demo.dto.ClassroomDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Classroom;
import com.example.demo.service.BatchService;
import com.example.demo.service.ClassroomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class ClassApiController {

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private BatchService batchService;


    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping(path = "/classrooms/bybatch/all/{id}")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByBatch(@PathVariable("id") long id) {

        Batch batch = batchService.getBatchById(id);
        List<Classroom> classroomList = classroomService.getClassroomsByBatch(batch);
        return new ResponseEntity<>(convertClassroomDto(classroomList), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @PostMapping(path = "/classrooms/create")
    public ResponseEntity<Object> createClassroom(@RequestBody ClassroomDto classroomDto) {
        try {
            classroomService.createClassroom(classroomDto);
            return new ResponseEntity<>("success", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping("/classrooms/all")
    public ResponseEntity<List<ClassroomDto>> getClassrooms() {
        return new ResponseEntity<>(convertClassroomDto(classroomService.getClassrooms()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @PostMapping( "/classrooms/search")
    public ResponseEntity<List<ClassroomDto>> searchResult(@RequestBody SearchDto searchDto) {

            Batch batch = batchService.getBatchByBatchCode(searchDto.getBatch());
            List<Classroom> classroomList = classroomService.getClassroomsByBatchAndDate(batch, searchDto.getFromDate());
            List<ClassroomDto> classroomDtos = convertClassroomDto(classroomList);

            return new ResponseEntity<>(classroomDtos, HttpStatus.OK);

    }

    public List<ClassroomDto> convertClassroomDto(List<Classroom> list) {
        List<ClassroomDto> Dto = new ArrayList<>();
        for (Classroom classroom : list) {
            ClassroomDto classroomDto = new ClassroomDto();
            classroomDto.setId(classroom.getId());
            classroomDto.setClassName(classroom.getClassName());
            classroomDto.setStartTime(classroom.getStartTime());
            classroomDto.setEndTime(classroom.getEndTime());
            classroomDto.setSubject(classroom.getSubject().getName());
            classroomDto.setDate(classroom.getDate());
            classroomDto.setLecturer(classroom.getSubject().getUser().getUsername());

            Dto.add(classroomDto);
        }
        return Dto;
    }
}
