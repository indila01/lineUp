package com.example.demo.web;

import com.example.demo.dto.BatchDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Subject;
import com.example.demo.service.BatchService;
import com.example.demo.service.SubjectService;
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
@RequestMapping("/batch")
public class BatchController {

    @Autowired
    private BatchService batchService;
    @Autowired
    private SubjectService subjectService;


    @ModelAttribute("viewBatches")
    public BatchDto batchDto() {
        return new BatchDto();
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/createBatch")
    public ModelAndView createBatch(@ModelAttribute("batch") BatchDto batchDto) {
        ModelAndView model = getBatches();
        try {
            batchService.createBatch(batchDto);
             model = getBatches();
            model.addObject("success", "Successfully created a batch");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }
        return model;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(path = "/update")
    public ModelAndView updateBatch(@RequestParam String batchCode, @RequestParam long batchId, @RequestParam String course, @RequestParam String year, @RequestParam List<String> subjects) {
        ModelAndView model = getBatches();
        try {
            BatchDto batchDto = new BatchDto();
            batchDto.setBatchCode(batchCode);
            batchDto.setCourse(course);
            batchDto.setYear(year);
            batchDto.setSubjects(subjects);

            batchService.updateBatch(batchDto);

            model = getBatches();
            model.addObject("success", "Successfully created a batch");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }
        return model;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path = "/removeBatch/{id}")
    public ModelAndView removeBatch(@PathVariable("id") long id) {
        ModelAndView model = getBatches();
        try {
            batchService.deleteBatch(id);
            model = getBatches();
            model.addObject("success", "Successfully deleted batch");
        } catch (Exception | CustomException e) {
            model.addObject("error", e.getMessage());
        }
        return model;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','LECTURER')")
    @GetMapping(path = "/batches")
    public ModelAndView viewBatches(){
        return getBatches();
    }

    public ModelAndView getBatches() {

        ModelAndView model = new ModelAndView();
        List<Batch> batchList = batchService.getBatches();
        List<BatchDto> batchDtos = convertBatchDto(batchList);

        model.addObject("batch", new BatchDto());

        List<Subject> subjects = subjectService.getSubjects();
        List<SubjectDto> subjectDtos = convertSubjectDto(subjects);

        model.addObject("subjects", subjectDtos);
        model.addObject("batches", batchDtos);
        model.setViewName("viewBatches");

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
