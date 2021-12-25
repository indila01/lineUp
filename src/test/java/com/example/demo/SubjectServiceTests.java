package com.example.demo;

import com.example.demo.model.Subject;
import com.example.demo.model.User;
import com.example.demo.service.BatchService;
import com.example.demo.service.SubjectService;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class SubjectServiceTests {

    @Autowired
    public SubjectService subjectService;
    @Autowired
    public BatchService batchService;

    @Test
    public void getAllSubjects()  {
        List<Subject> subjects = subjectService.getSubjects();

        boolean isExist = subjects.size()>0;
        assertTrue(isExist);
    }

    @Test
    public void getSubjectsByBatch()  {
        List<Subject> subjects = subjectService.getSubjectsByBatch(batchService.getBatchByBatchCode("HF2131CNS"));

        boolean isExist = subjects.size()>0;
        assertTrue(isExist);
    }

    @Test
    public void getSubjectById()  {
        Subject subjects = subjectService.getSubject(1L);

        boolean isExist = false;
        if(!Objects.isNull(subjects)){
            isExist = true;
        }
        assertTrue(isExist);
    }
}
