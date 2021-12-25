package com.example.demo.web;

import com.example.demo.dto.BatchDto;
import com.example.demo.dto.ClassroomDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.exceptions.CustomException;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/class")
public class ClassController {

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private UserService userService;
    @Autowired
    private BatchService batchService;


    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @PostMapping(path = "/createClassroom")
    public ModelAndView createClassroom(@ModelAttribute("classroom") ClassroomDto classroomDto) {
        ModelAndView model = getAllClassrooms();
        try {
            classroomService.createClassroom(classroomDto);
            model = getAllClassrooms();
            model.addObject("success", "Successfully created a classroom");
        } catch (Exception e) {
            model.addObject("error", e.getMessage());
        }
        return model;
    }

    public ModelAndView getAllClassrooms() {
        ModelAndView model = new ModelAndView();
        List<Subject> subjects = subjectService.getSubjects();
        List<Classroom> classroomList = classroomService.getClassrooms();
        List<ClassroomDto> classroomDtos = convertClassroomDto(classroomList);
        List<SubjectDto> subjectDtos = convertSubjectDto(subjects);
        model.addObject("classrooms", classroomDtos);
        model.addObject("subjects", subjectDtos);
        model.addObject("classroom", new ClassroomDto());
        model.setViewName("viewClassrooms");
        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping(path = "/viewClassrooms/{id}")
    public ModelAndView getClassrooms(@PathVariable("id") long id) {

        ModelAndView model = new ModelAndView();
        List<Subject> subjects = subjectService.getSubjects();
        List<Classroom> classroomList = classroomService.getClassroomsBySubject(id);
        List<ClassroomDto> classroomDtos = convertClassroomDto(classroomList);
        List<SubjectDto> subjectDtos = convertSubjectDto(subjects);
        model.addObject("classrooms", classroomDtos);
        model.addObject("subjects", subjectDtos);
        model.addObject("classroom", new ClassroomDto());
        model.setViewName("viewClassrooms");
        return model;
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @GetMapping(path = "/searchClassroom")
    public ModelAndView searchTimeTable() {

        ModelAndView model = new ModelAndView();
        List<Batch> batches = batchService.getBatches();
        List<BatchDto> batchDtos = convertBatchDto(batches);

        model.addObject("batches", batchDtos);
        model.addObject("search", new SearchDto());
        model.setViewName("classroomSearch");

        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER','STUDENT')")
    @PostMapping(path = "/searchClassroom")
    public ModelAndView searchResult(@ModelAttribute("search") SearchDto searchDto) {

        ModelAndView model = new ModelAndView();

        Batch batch = batchService.getBatchByBatchCode(searchDto.getBatch());
        List<Classroom> classroomList = classroomService.getClassroomsByBatchAndDate(batch, searchDto.getFromDate());
        List<ClassroomDto> classroomDtos = convertClassroomDto(classroomList);

        model.addObject("batch", batch.getBatchCode());
        model.addObject("classrooms", classroomDtos);
        model.setViewName("viewClassroomsByBatch");

        return model;
    }


    @PreAuthorize("hasAnyAuthority('LECTURER','ADMIN')")
    @GetMapping(path = "/removeClassroom/{id}")
    public ModelAndView removeClassroom(@PathVariable("id") long id){
        ModelAndView model = getAllClassrooms();
        try {
            classroomService.deleteClassroom(id);
            model = getAllClassrooms();
            model.addObject("success", "Successfully deleted classroom");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }
        return model;
    }


    @PreAuthorize("hasAuthority('LECTURER')")
    @GetMapping(path = "/viewClassroomsByLecturer")
    public ModelAndView viewClassroomsByLectuer() {
        ModelAndView model = new ModelAndView();

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(userDetails.getUsername());
        List<Subject> subjects = subjectService.getSubjects();
        List<Classroom> classroomList = classroomService.getClassroomsByUser(user);
        List<ClassroomDto> classroomDtos = convertClassroomDto(classroomList);
        List<SubjectDto> subjectDtos = convertSubjectDto(subjects);
        model.addObject("subjects", subjectDtos);
        model.addObject("classroom", new ClassroomDto());
        model.addObject("classrooms", classroomDtos);
        model.setViewName("viewClassrooms");
        return model;
    }


    @PreAuthorize("hasAuthority('STUDENT')")
    @GetMapping(path = "/viewClassroomsByBatch")
    public ModelAndView getClassroomsByBatch() {
        ModelAndView model = new ModelAndView();


        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userService.getUser(userDetails.getUsername());
        List<Classroom> classroomList = classroomService.getClassroomsByBatch(user.getBatch());
        List<ClassroomDto> classroomDtos = convertClassroomDto(classroomList);
        model.addObject("batch", user.getBatch().getBatchCode());
        model.addObject("classrooms", classroomDtos);
        model.setViewName("viewClassroomsByBatch");
        return model;
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
