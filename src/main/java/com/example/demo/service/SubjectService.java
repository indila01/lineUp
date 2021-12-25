package com.example.demo.service;

import com.example.demo.exceptions.CustomException;
import com.example.demo.model.Batch;
import com.example.demo.model.Subject;
import com.example.demo.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    Subject createSubject(SubjectDto subjectDto) throws CustomException;
    List<Subject> getSubjects();
    List<Subject> getSubjectsByBatch(Batch batch);
    Subject getSubject(long id);

    void deleteSubject(long id) throws CustomException;
}

