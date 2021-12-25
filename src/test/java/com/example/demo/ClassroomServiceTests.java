package com.example.demo;

import com.example.demo.model.Classroom;
import com.example.demo.model.Subject;
import com.example.demo.service.BatchService;
import com.example.demo.service.ClassroomService;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class ClassroomServiceTests {

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private UserService userService;

    @Test
    public void getAllClassrooms()  {
        List<Classroom> classrooms = classroomService.getClassrooms();

        boolean isExist = classrooms.size()>0;
        assertTrue(isExist);
    }

    @Test
    public void getAllClassroomsByBatch()  {
        List<Classroom> classrooms = classroomService.getClassroomsByBatch(batchService.getBatchByBatchCode("HF2131CNS"));

        boolean isExist = classrooms.size()>0;
        assertTrue(isExist);
    }

    @Test
    public void getAllClassroomsBySubject()  {
        List<Classroom> classrooms = classroomService.getClassroomsBySubject(1L);

        boolean isExist = classrooms.size()>0;
        assertTrue(isExist);
    }

    @Test
    public void getClassroomsByUser()  {
        List<Classroom> classrooms = classroomService.getClassroomsByUser(userService.getUser("sajani"));

        boolean isExist = classrooms.size()>0;
        assertTrue(isExist);
    }

    @Test
    public void getClassroomsByBatchAndDate()  {
        List<Classroom> classrooms = classroomService.getClassroomsByBatchAndDate(batchService.getBatchByBatchCode("HF2131CNS"),"2021-09-16");

        boolean isExist = classrooms.size()>0;
        assertTrue(isExist);
    }
}
